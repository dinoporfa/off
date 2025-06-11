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

    def to_json(self):
        return {
            "name": self.name,
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
    pts = models.IntegerField(default=0)
    hp = models.IntegerField(default=500)
    atk = models.IntegerField(default=50)

    def to_json(self):
        return{
            "pts": User.pts
        }