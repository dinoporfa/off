package com.example.off;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {
    TextView name, pts, hp, atk, def, esp, agl;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img = findViewById(R.id.dimg);
        name = findViewById(R.id.dname);
        pts = findViewById(R.id.dpts);
        hp = findViewById(R.id.dhp);
        atk = findViewById(R.id.datk);
        def = findViewById(R.id.ddef);
        esp = findViewById(R.id.desp);
        agl = findViewById(R.id.dagl);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            img.setImageResource(bundle.getInt("img"));
            name.setText(bundle.getString("name"));
            pts.setText("Pts: " + bundle.getInt("pts"));
            hp.setText("Hp: " + bundle.getInt("hp"));
            atk.setText("Atk: " + bundle.getInt("atk"));
            def.setText("Def: " + bundle.getInt("def"));
            esp.setText("Esp: " + bundle.getInt("esp"));
            agl.setText("Agl: " + bundle.getInt("agl"));
        }

    }
}