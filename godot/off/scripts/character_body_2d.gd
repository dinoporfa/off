extends CharacterBody2D

@onready var _animated_sprite = $AnimatedSprite2D

@export var speed = 100


func _physics_process(delta):
	var input_direction = Input.get_vector("left", "right", "up", "down")
	velocity = input_direction * speed
	move_and_slide()


func _process(delta: float) -> void:
	if Input.is_action_pressed("ui_right"):
		_animated_sprite.play("right")
	else:
		_animated_sprite.stop()
		
	if Input.is_action_pressed("ui_left"):
		_animated_sprite.play("left")
	else:
		_animated_sprite.stop()
	if Input.is_action_pressed("ui_up"):
		_animated_sprite.play("up")
	else:
		_animated_sprite.stop()
	if Input.is_action_pressed("ui_down"):
		_animated_sprite.play("down")
