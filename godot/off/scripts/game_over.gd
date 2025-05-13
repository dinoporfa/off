extends Control


func _on_button_pressed() -> void:
	get_tree().change_scene_to_file("res://scenes/main_menu.tscn")

func _ready() -> void:
	$HBoxContainer/VBoxContainer/Label.text = "Conseguiches %d puntos!" % State.pts
	State.hp = 500
	State.pts = 0
