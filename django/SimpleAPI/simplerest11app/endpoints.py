import json

from django.http import JsonResponse
from .models import Ghost, User, UserPoints, FinalGhost
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
        user = User.objects.filter(userName=userName, userPassword=userPassword).first()
        if user is None:
            return JsonResponse({}, status = 400)
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
            if newpts > user.pts:
                user.pts = newpts
                user.save()
                return JsonResponse({'pts': 'updated'}, status=200)
            else:
                return JsonResponse({}, status=200)
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
        most_recent_points = UserPoints.objects.filter(user=user).first() or 0
        return JsonResponse({"pts": 0})
    else:
        return JsonResponse({}, status=405)


def get_pts_history(request):
    if request.method == 'GET':
        token = request.headers.get('token')
        user = User.objects.filter(tokenSession=token).first()
        if user is None:
            return JsonResponse({}, status=401)
        response = []
        for point in UserPoints.objects.filter(user=user):
            response.append({
                "pts": point.pts,
                "date": point.date
            })
        return JsonResponse(response, safe=False)
    else:
        return JsonResponse({}, status=405)

def get_finalGhost(request):
    if request.method == 'GET':
        finalGhosts = FinalGhost.objects.all()
        response = []
        for ghost in finalGhosts:
            response.append(ghost.to_json())
        return JsonResponse(response, safe = False)
    else:
        return JsonResponse({}, status = 405)

def user_status(request, id):
    if request.method == 'GET':
        user = User.objects.filter(id = id).first()
        if user == None:
            return JsonResponse({}, status = 404)
        response = []
        response.append({
            "userName": user.userName,
            "pts": UserPoints.objects.filter(user = user).first().pts,
            "hp": user.hp,
            "atk": user.atk,
            "isLoggedIn": isLoggedIn(user)
        })
        return JsonResponse(response, safe = False)
    else:
        return JsonResponse({}, status = 405)

def isLoggedIn(user):
    if user.tokenSession == None:
        return False
    else:
        return True