package com.example.fruits;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FruitsCategoryActivity extends AppCompatActivity {
   private SQLiteDatabase db;
   private Cursor cursor;
  private   ListView listView;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fruits_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        SQLiteOpenHelper fruitsDatabaseHelper = new FruitsDatabaseHelper(this);
        try {
             db = fruitsDatabaseHelper.getReadableDatabase();

             cursor = db.query("FRUITS", new String[]{"_id", "NAME", "DESCRIPTION"},
                    null, null, null, null, null);
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);

             listView  = findViewById(R.id.listView);
            listView.setAdapter(cursorAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }




        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FruitsCategoryActivity.this, FruitsActivity.class);

                //extra data: the id
                intent.putExtra("fruitId", (int)id); //was a long, casting it to int
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(itemClickListener);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}

