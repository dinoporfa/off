extends Control


signal textbox_closed

# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	set_health($Player/ProgressBar, State.hp, State.max_hp)
	$TextPanel.hide()
	display_text("Un fantasma apareceu!")
	
	await (textbox_closed)

func _input(event):
	if Input.is_action_just_pressed("ui_accept"):
		$TextPanel.hide()
		emit_signal("textbox_closed")

func display_text(text):
	$TextPanel.show()
	$TextPanel/Label.text = text


func set_health(progress_bar, hp, max_hp):
	progress_bar.value=hp
	progress_bar.max_value=max_hp
	progress_bar.get_node("Label").text="%d/%d" % [hp/max_hp]


func _on_atk_pressed() -> void:
	pass # Replace with function body.


func _on_scp_pressed() -> void:
	display_text("Escapaches!")
	await (textbox_closed)
	get_tree().change_scene_to_file("res://scenes/game.tscn")
