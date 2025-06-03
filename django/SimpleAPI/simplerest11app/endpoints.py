from django.http import JsonResponse

from SimpleAPI.simplerest11app.models import Ghost


def health_check(request):
    http_response = {"is_up": True}
    return JsonResponse(http_response)

def set_enemies():
    ghost = Ghost("Queimado", 100, 70, 80, 70, 70, 40)
    ghost.save()
    ghost = Ghost("Queimado de calvario", 250, 150, 150, 85, 80, 100)
    ghost.save()
    ghost = Ghost("Queimado crítico", 150, 500, 10, 10, 10, 250)
    ghost.save()

    return JsonResponse({"Enemies set successfully"}, status = 200)

