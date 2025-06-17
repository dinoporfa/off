package com.example.off;

import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuntuacionesActivity extends AppCompatActivity {
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_puntuaciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Puntuaciones> ptsList = new ArrayList<>();
        queue = Volley.newRequestQueue(this);
        JSONArray a = new JSONArray();
        JsonArrayRequest r = new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:8000/pts/history", a, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                int i;
                for (i = 0; i < response.length(); i++){
                    try {
                        JSONObject e = response.getJSONObject(i);
                        ptsList.add(new Puntuaciones(e.getInt("pts"), e.getString("date")));
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(PuntuacionesActivity.this));
                PuntuacionesAdapter adapter = new PuntuacionesAdapter(ptsList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PuntuacionesActivity.this, "Non se puideron cargar os puntos", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("token", Session.token);
                return headers;
            }
        };
        queue.add(r);
    }
}