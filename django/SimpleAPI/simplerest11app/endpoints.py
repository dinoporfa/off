import json

from django.http import JsonResponse
from .models import Ghost, User, Points, IntentosLoginFallidos
from django.views.decorators.csrf import csrf_exempt
import secrets

def health_check(request):
    http_response = {"is_up": True}
    return JsonResponse(http_response)

@csrf_exempt
def register(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        userName = body.get('username')
        userPassword = body.get('password')
        user = User(userName = userName, userPassword = userPassword)
        user.save()
        return JsonResponse({"Success": True}, status= 201)
    else:
        return JsonResponse({}, status= 405)

@csrf_exempt
def login(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        userName = body.get('username')
        userPassword = body.get('password')
        user = User.objects.filter(userName=userName).first()
        if user is None:
            return JsonResponse({}, status = 400)
        loginFailed = IntentosLoginFallidos.objects.filter(user=user).first()
        if loginFailed.tries >= 10:
            return JsonResponse({}, status=401)
        if userPassword != user.userPassword:
            loginFailed.tries = loginFailed.tries + 1
            return JsonResponse({}, status=401)
        loginFailed.tries =0
        token = secrets.token_hex(32)
        user.tokenSession = token
        user.save()
        return JsonResponse({'token': token}, status = 201)
    else:
        return JsonResponse({}, status= 405)

@csrf_exempt
def upload_pts(request):
    t = request.headers.get('token')
    if t != None:
        user = User.objects.filter(tokenSession=t).first()
        if user != None:
            body = json.loads(request.body)
            newpts = body.get('pts')
            points = Points(pts = newpts, user = user)
            points.save()
            return JsonResponse({'pts': 'updated'}, status=200)
        else:
            return JsonResponse({}, status=401)
    else:
        return JsonResponse({}, status=401)

def get_enemies(request):
    if request.method == 'GET':
        ghosts = Ghost.objects.all()
        response= []
        for ghost in ghosts:
            response.append(ghost.to_json())
        return JsonResponse(response, safe=False)
    else:
        return JsonResponse({}, status=405)

def get_pts(request):
    if request.method == 'GET':
        token = request.headers.get('token')
        user = User.objects.filter(tokenSession=token).first()
        if user is None:
            return JsonResponse({}, status=401)
        points = Points.objects.filter(user = user)
        response = []
        for row in points:
            response.append(row.to_json())
        return JsonResponse(response, safe=False)
    else:
        return JsonResponse({}, status=405)