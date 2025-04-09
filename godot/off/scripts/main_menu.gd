extends Control


func _on_begin_pressed() -> void:
	get_tree().change_scene()


func _on_quit_pressed() -> void:
	get_tree().quit()
