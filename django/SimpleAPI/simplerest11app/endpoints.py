from django.http import JsonResponse

def health_check(request):
    http_response = {"is_up": True}
    return JsonResponse(http_response)

