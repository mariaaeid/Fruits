package com.example.fruits;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FruitsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fruitsdb"; ///the name of our database
    private static final int DB_VERSION = 1; // the version of the database;

    FruitsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
updateMyDatabase(db,oldVersion,newVersion);
    }

    public static void insertFruit(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues fruitValues = new ContentValues();
        fruitValues.put("NAME", name);
        fruitValues.put("DESCRIPTION", description);
        fruitValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("FRUITS", null, fruitValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE FRUITS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, " + "DESCRIPTION TEXT," + "IMAGE_RESOURCE_ID INTEGER);");
            insertFruit(db, "Strawberry", "cute", R.drawable.strawberry);
            insertFruit(db, "Apple", "pretty", R.drawable.apple);
            insertFruit(db, "Banana", "nice", R.drawable.banana);
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }
    }
}
