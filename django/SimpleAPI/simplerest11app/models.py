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

    def to_json(self):
        return{
            "userName": self.userName
        }

class UserPoints(models.Model):
    pts = models.IntegerField()
    date = models.DateTimeField(auto_now_add=True)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

class FinalGhost (models.Model):
    name = models.CharField(max_length = 50)
    difficulty = models.IntegerField()

    def to_json(self):
        return {
            "nome": self.name,
            "dificultade": self.difficulty
        }