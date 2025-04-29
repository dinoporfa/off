extends CharacterBody2D

@onready var _animated_sprite = $AnimatedSprite2D

@export var speed = 100


func _physics_process(delta):
	var input_direction = Input.get_vector("left", "right", "up", "down")
	velocity = input_direction * speed
	move_and_slide()


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
