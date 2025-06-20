from tkinter.constants import CASCADE

from django.db import models

# Create your models here.

class Ghost(models.Model):
    name = models.CharField(max_length=20, unique=True)
    img = models.CharField(max_length=80, null=True)
    pts = models.IntegerField(default=0)
    hp = models.IntegerField(default=0)
    atk = models.IntegerField(default=0)
    df = models.IntegerField(default=0)
    spc = models.IntegerField(default=0)
    agl = models.IntegerField(default=0)

    def to_json(self):
        return {
            "name": self.name,
            "img": self.img,
            "pts": self.pts,
            "hp": self.hp,
            "atk": self.atk,
            "def": self.df,
            "spc": self.spc,
            "agl": self.agl
        }

class User(models.Model):
    userName = models.CharField(max_length=50, unique=True)
    userPassword = models.CharField(max_length=50)
    tokenSession = models.CharField(max_length=50, null=True)
    hp = models.IntegerField(default=500)
    atk = models.IntegerField(default=50)


class Points(models.Model):
    pts = models.IntegerField()
    date = models.DateTimeField(auto_now_add=True)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    def to_json(self):
        return{
            "pts": self.pts,
            "date": self.date
        }

class IntentosLoginFallidos(models.Model):
    tries = models.IntegerField(default = 0)
    user = models.ForeignKey(User, on_delete=models.CASCADE)