from django.db import models

# Create your models here.

class Monster(models.Model):
    name = models.CharField(max_length=20, unique=True)
    hp = models.IntegerField(default=0)
    atk = models.IntegerField(default=0)
    df = models.IntegerField(default=0)
    spc = models.IntegerField(default=0)
    agl = models.IntegerField(default=0)

class Player(models.Model):
    name = models.CharField(max_length=20, unique=True)
    pts = models.IntegerField(default=0)
    hp = models.IntegerField(default=0)
    atk = models.IntegerField(default=0)
    df = models.IntegerField(default=0)
    spc = models.IntegerField(default=0)
    agl = models.IntegerField(default=0)