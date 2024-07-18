package com.example.fruits;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FruitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fruits);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final String EXTRA_FRUITID = "fruitId";

        Intent intent = getIntent();
        int fruitId = (Integer)getIntent().getExtras().get(EXTRA_FRUITID);

        //int fruitId = (Integer)intent.getExtras().getInt("id");
        //Fruits fruit = Fruits.fruit[id]; //to know what drink we pressed on


        SQLiteOpenHelper helper = new FruitsDatabaseHelper(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("FRUITS", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"}, "_id = ?",
                    new String[] {Integer.toString(fruitId)},
                     null, null, null);
            //SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                    //new String[]{"name"}, new int[]{android.R.id.text1}, 0);


            if(cursor.moveToFirst()){
                    //get the fruit details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);


                //now displaying items

                //populate the drink name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                 //populate the drink description
                TextView description = (TextView)findViewById(R.id.description);
               description.setText(descriptionText);

                //populate the drink image
               ImageView photo = (ImageView) findViewById(R.id.photo);
               photo.setImageResource(photoId);
               photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }




    }
}