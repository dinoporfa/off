extends Control

signal textbox_closed

var current_player_hp = 0
var current_enemy_hp = 0
var crit_rate = 1

@export var enemy: enemy = null:
	set(value):
		enemy = Manager.get_enemy()

func _ready() -> void:
	set_health($Player/player_bar, State.hp, State.max_hp)
	set_enemy_health($enemy_bar, enemy.hp, enemy.hp)
	$"enemy_sprite".texture = enemy.texture
	current_enemy_hp = enemy.hp
	current_player_hp = State.hp
	
	var http_request = HTTPRequest.new()
	add_child(http_request)
	http_request.request_completed.connect(self._http_request_completed)
	var error = http_request.request("http://127.0.0.1:8000/luck")
	if error != OK:
		crit_rate = 0
	
	$TextPanel.hide()
	display_text("Un %s apareceu!" %enemy.name)
	
	await (textbox_closed)


func _http_request_completed(result, response_code, headers, body):
	var json = JSON.new()
	json.parse(body.get_string_from_utf8())
	crit_rate = json.get_data()
	


func _input(event):
	if Input.is_action_just_pressed("ui_accept"):
		$TextPanel.hide()
		emit_signal("textbox_closed")

func display_text(text):
	$TextPanel.show()
	$TextPanel/Label.text = text


func set_health(progress_bar, hp, max_hp):
	progress_bar.max_value=max_hp
	progress_bar.value=hp
	progress_bar.get_node("Label").text="%s / %s" % [hp,max_hp]

func set_enemy_health(progress_bar, hp, max_hp):
	progress_bar.value=hp
	progress_bar.max_value=max_hp


func _on_atk_pressed() -> void:
	var crit
	if randf_range(0.0, 1.0) < crit_rate:
		crit = true
	else:
		crit = false
	
	display_text("Atacaches!")
	await (textbox_closed)
	
	if crit:
		current_enemy_hp = max(0, current_enemy_hp - State.atk*2)
	else:
		current_enemy_hp = max(0, current_enemy_hp - State.atk)
	set_enemy_health($enemy_bar, current_enemy_hp, enemy.hp)
	
	
	$AnimationPlayer.play("enemy")
	if crit:
		display_text("Fixeches %d puntos de dano!" % (State.atk*2))
	else:
		display_text("Fixeches %d puntos de dano!" % State.atk)
	await (textbox_closed)
	
	
	if current_enemy_hp<=0:
		display_text("%s enimigo purificado!" % enemy.name)
		await (textbox_closed)
		State.hp = current_player_hp
		State.pts += enemy.pts
		get_tree().change_scene_to_file("res://scenes/game.tscn")
	
	enemy_turn()

func enemy_turn() -> void:
	display_text("O %s ataca!" % enemy.name)
	await (textbox_closed)
	
	current_player_hp = max(0, current_player_hp - enemy.atk)
	set_health($Player/player_bar, current_player_hp, State.max_hp)
	
	$AnimationPlayer.play("player_hit")
	
	display_text("Recibiches %d puntos de dano!" % enemy.atk)
	await (textbox_closed)
	
	if current_player_hp<=0:
		get_tree().change_scene_to_file("res://scenes/game_over.tscn")


func _on_scp_pressed() -> void:
	display_text("Escapaches!")
	await (textbox_closed)
	get_tree().change_scene_to_file("res://scenes/game.tscn")
