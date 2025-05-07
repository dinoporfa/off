extends Control


signal textbox_closed

@export var enemy : Resource

var current_player_hp = 0
var current_enemy_hp = 0

func _ready() -> void:
	set_health($Player/player_bar, State.hp, State.max_hp)
	#set_enemy_health($enemy_bar, enemy.hp, enemy.hp)
	#$"enemy_sprite".texture = enemy.texture
	#current_enemy_hp = enemy.hp
	current_player_hp = State.hp
	
	$TextPanel.hide()
	#display_text("Un %s apareceu!" %enemy.name)
	
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

func set_enemy_health(progress_bar, hp, max_hp):
	progress_bar.value=hp
	progress_bar.max_value=max_hp


func _on_atk_pressed() -> void:
	display_text("Atacaches!")
	await (textbox_closed)
	
	#current_enemy_hp = max(0, current_enemy_hp - State.atk)
	#set_enemy_health($enemy_bar, current_enemy_hp, enemy.hp)
	
	display_text("Fixeches %d puntos de dano!" % State.atk)
	await (textbox_closed)
	
	$AnimationPlayer.play("enemy")
	enemy_turn()

func enemy_turn() -> void:
	#display_text("O %s ataca!" % enemy.name)
	#await (textbox_closed)
	
	#current_player_hp = max(0, current_player_hp - enemy.atk)
	set_health($Player/player_bar, current_player_hp, State.max_hp)
	$AnimationPlayer.play("player_hit")
	#display_text("Recibiches %d puntos de dano!" % enemy.atk)
	#await (textbox_closed)
	
	if current_player_hp<=0:
		get_tree().change_scene("res://scripts/game_over.gd")


func _on_scp_pressed() -> void:
	display_text("Escapaches!")
	await (textbox_closed)
	get_tree().change_scene_to_file("res://scenes/game.tscn")
