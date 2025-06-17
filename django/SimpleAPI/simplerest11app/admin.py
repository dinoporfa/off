from django.contrib import admin
from .models import User, Ghost, UserPoints
# Register your models here.
admin.site.register(User)
admin.site.register(Ghost)
admin.site.register(UserPoints)