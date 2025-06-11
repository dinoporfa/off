package com.example.off;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, password, password2;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.rname);
        email = findViewById(R.id.remail);
        password = findViewById(R.id.rpassword);
        password2 = findViewById(R.id.rpassword2);

        Button reg = (Button)findViewById(R.id.rregister);
        Button login = (Button)findViewById(R.id.rlogin);

        queue = Volley.newRequestQueue(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() == 0 || email.getText().length() == 0 || password.getText().length() == 0 || password.getText() != password2.getText())
                    Toast.makeText(RegisterActivity.this, "Porfavor introduce datos válidos", Toast.LENGTH_SHORT).show();
                else{
                    JSONObject o = new JSONObject();
                    try {
                        o.put("username", name.getText());
                        o.put("password", password.getText());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    JsonObjectRequest r = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8000/users", o, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(RegisterActivity.this, "Usuario rexistrado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MpageActivity.class));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "Non se puido rexistrar o usuario", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(r);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

}