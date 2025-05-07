extends Node2D

var encounter_number : int = 100:
	set(value):
		encounter_number = value

func _ready():
	randomize()
	encounter_number = randi_range(25,50)
