extends Control


func _on_button_pressed() -> void:
	get_tree().change_scene_to_file("res://scenes/main_menu.tscn")

func _ready() -> void:
	State.hp = 500
	State.pts = 0
	$HBoxContainer/VBoxContainer/Label.text = "Conseguiches %d puntos!" % State.pts
	var request = HTTPRequest.new()
	add_child(request)
	request.request_completed.connect(self._http_request_completed)
	
	var fields = {"pts" : State.pts}
	var headers = ["token:" + str(1234)]
	var error = request.request("http://127.0.0.1:8000/gameover", headers, HTTPClient.METHOD_POST, JSON.stringify(fields))

	if error != OK:
		push_error("An error occurred in the HTTP request.")
	
func _http_request_completed(result, response_code, headers, body):
	var json = JSON.new()
	json.parse(body.get_string_from_utf8())
	var response = json.get_data()
	print(response_code)
