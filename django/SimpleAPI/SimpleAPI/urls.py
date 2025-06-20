"""
URL configuration for SimpleAPI project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from simplerest11app import endpoints

urlpatterns = [
    path('admin/', admin.site.urls),
    path('health', endpoints.health_check),
    path('users', endpoints.register),
    path('sessions', endpoints.login),
    path('gameover', endpoints.upload_pts),
    path('enemies', endpoints.get_enemies),
    path('pts', endpoints.get_pts)
]
