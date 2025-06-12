package com.example.off;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GhostActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ghost);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Ghost> ghostList = new ArrayList<>();
        queue = Volley.newRequestQueue(this);
        JSONArray a = new JSONArray();
        JsonArrayRequest r = new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:8000/enemies", a, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                int i;
                for (i = 0; i < response.length(); i++){
                    try {
                        JSONObject e = response.getJSONObject(i);
                        ghostList.add(new Ghost(e.getString("name"), e.getString("img"), e.getInt("pts"), e.getInt("hp"), e.getInt("atk"), e.getInt("def"), e.getInt("spc"), e.getInt("agl")));
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(GhostActivity.this));
                GhostAdapter adapter = new GhostAdapter(ghostList, GhostActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GhostActivity.this, "Non se puideron añadir os enimigos", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(r);

        Button user = (Button)findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GhostActivity.this, BatterActivity.class));
            }
        });
    }
}