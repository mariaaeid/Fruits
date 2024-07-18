package com.example.fruits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private Button fruitsButton;


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


        fruitsButton = findViewById(R.id.mainButton);

        fruitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent (MainActivity.this, FruitsCategoryActivity.class);
               startActivity(intent);
            }
        });



//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //if position 0, go to second activity (new intent)
//                if (position == 0) {
//                    Intent intent = new Intent(MainActivity.this, FruitsCategoryActivity.class);
//                    startActivity(intent);
//
//                }
//            }
//        };

        //listview
       // ListView listView = findViewById(R.id.listView);
        //listView.setOnItemClickListener(onItemClickListener);
    }
}