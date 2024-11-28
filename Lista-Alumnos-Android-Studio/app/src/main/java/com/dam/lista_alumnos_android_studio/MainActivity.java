package com.dam.lista_alumnos_android_studio;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String names[] = {"Antonio", "Jose", "Violeta", "Juan", "Sara", "Paco", "Maria", "Rodrigo"};
    private String notas[] = {"8", "5", "9", "4", "3", "7","2", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    tv1 = findViewById(R.id.tv1);
    lv1 = findViewById(R.id.lv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilo_lista,names);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("La nota de "+ lv1.getItemAtPosition(i)+" es "+notas[i]);
            }
        });
    }

    public void mensajeboton(View view){
        Toast.makeText(this,"Hola que Tal", Toast.LENGTH_SHORT).show();
    }
}