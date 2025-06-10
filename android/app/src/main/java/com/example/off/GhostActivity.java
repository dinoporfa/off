package com.example.off;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GhostActivity extends AppCompatActivity {

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
        ghostList.add(new Ghost("Queimado","https://static.wikia.nocookie.net/offgame/images/6/6a/Burnt.png",100,70,80,70,70,40));
        ghostList.add(new Ghost("Queimado de calvario","https://static.wikia.nocookie.net/offgame/images/5/53/Calvary-burnt.png",250,150,150,85,80,100));
        ghostList.add(new Ghost("Queimado crítico","https://static.wikia.nocookie.net/offgame/images/4/4f/Critic-burnt.png",150,500,10,10,10,250));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GhostAdapter adapter = new GhostAdapter(ghostList, this);
        recyclerView.setAdapter(adapter);

        Button user = (Button)findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GhostActivity.this, BatterActivity.class));
            }
        });
    }
}