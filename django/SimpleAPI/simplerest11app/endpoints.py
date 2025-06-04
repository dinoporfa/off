import json

from django.http import JsonResponse

from .models import Ghost, User
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
        return JsonResponse({}, status= 201)
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

def set_enemies(request):
    if request.method == 'POST':
        ghost = Ghost("Queimado", 100, 70, 80, 70, 70, 40)
        ghost.save()
        ghost = Ghost("Queimado de calvario", 250, 150, 150, 85, 80, 100)
        ghost.save()
        ghost = Ghost("Queimado crítico", 150, 500, 10, 10, 10, 250)
        ghost.save()


    return JsonResponse({"Enemies set successfully"}, status = 200)

