from django.db import models

# Create your models here.

class Ghost(models.Model):
    name = models.CharField(max_length=20, unique=True)
    pts = models.IntegerField(default=0)
    hp = models.IntegerField(default=0)
    atk = models.IntegerField(default=0)
    df = models.IntegerField(default=0)
    spc = models.IntegerField(default=0)
    agl = models.IntegerField(default=0)

    def _to_json(self):
        return{
            "Name": Ghost.name,
            "Pts": Ghost.pts,
            "Hp": Ghost.hp,
            "Atk": Ghost.atk,
            "Def": Ghost.df,
            "Spc": Ghost.spc,
            "Agl": Ghost.agl
        }

class Player(models.Model):
    name = models.CharField(max_length=20, unique=True)
    pts = models.IntegerField(default=0)
    hp = models.IntegerField(default=0)
    atk = models.IntegerField(default=0)

    def _to_json(self):
        return{
            "Name": Player.name,
            "Pts": Player.pts,
            "Hp": Player.hp,
            "Atk": Player.atk
        }

class User(models.Model):
    userName = models.CharField(max_length=50, unique=True)
    userPassword = models.CharField(max_length=50)
    tokenSession = models.CharField(max_length=50, null=True)