extends Control


func _on_begin_pressed() -> void:
	get_tree().change_scene("res://scenes/game.tscn")


func _on_quit_pressed() -> void:
	get_tree().quit()
