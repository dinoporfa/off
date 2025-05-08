extends CharacterBody2D

@onready var _animated_sprite = $AnimatedSprite2D

@export var speed = 100

var step_size : int = 10

var distance_in_pixel : float = 0.0:
	set(value):
		distance_in_pixel = value
		var step = distance_in_pixel / step_size
		
		if step >= Manager.encounter_number:
			set_physics_process(false)
			Manager.save_player_data(self)
			Manager.change_scene()
			

func _ready() -> void:
	position = Manager.last_position

func _physics_process(delta):
	var initial_position = position
	
	var input_direction = Input.get_vector("left", "right", "up", "down")
	velocity = input_direction * speed
	move_and_slide()
	
	distance_in_pixel += position.distance_to(initial_position)

func _process(delta: float) -> void:
	if Input.is_action_pressed("ui_down"):
		_animated_sprite.play("down")
	elif Input.is_action_just_released("ui_down"):
		_animated_sprite.play("d_down")
	
	if Input.is_action_pressed("ui_up"):
		_animated_sprite.play("up")
	elif Input.is_action_just_released("ui_up"):
		_animated_sprite.play("d_up")
		
	if Input.is_action_pressed("ui_left"):
		_animated_sprite.play("left")
	elif Input.is_action_just_released("ui_left"):
		_animated_sprite.play("d_left")
		
	if Input.is_action_pressed("ui_right"):
		_animated_sprite.play("right")
	elif Input.is_action_just_released("ui_right"):
		_animated_sprite.play("d_right")
	
	
