extends Node

var last_position =Vector2(100,100)

@export_dir var enemy_folder

var encounter_number : int = 100:
	set(value):
		encounter_number = value

func change_scene():
	get_tree().change_scene_to_file("res://scenes/combat.tscn")
	encounter_number = randi_range(15,30)

func save_player_data(player):
	last_position = player.position

func get_enemy() ->enemy:
	var chosen_enemy 
	
	return chosen_enemy

func _ready() -> void:
	randomize()
	encounter_number = randi_range(15,30)
