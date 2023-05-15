package fcu.app.breakfast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class MenuDatabase {
    private AppCompatActivity activity;

    private static final String DATABASE_NAME = "menu.db";

    private static final String CREATE_MEAL_TABLE = "CREATE TABLE Meals(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "classification TEXT, " +
            "name TEXT NOT NULL, " +
            "description TEXT, " +
            "price Integer, " +
            "filename TEXT)";

    private SQLiteDatabase db;

    public MenuDatabase(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open(){
        db = activity.openOrCreateDatabase(DATABASE_NAME, 0, null);
        db.execSQL(CREATE_MEAL_TABLE);
    }

    public void addMeal(String classification, String name, String description, int price, String filename) {
        ContentValues values = new ContentValues();
        values.put("classification", classification);
        name = name.replace('-', ' ');
        values.put("name", name);
        description = description.replace('-', ' ');
        values.put("description", description);
        values.put("price", price);
        values.put("filename", filename);
        db.insert("Meals", null, values);
    }

    public Cursor getClassMeal(){
        Cursor cursor = db.rawQuery("SELECT * FROM Meals", null);
        /*while (cursor.moveToNext()){
            String mealName = cursor.getString(1);
            Toast.makeText(activity, "Add Meal" + mealName, Toast.LENGTH_SHORT).show();
        }*/
        return cursor;
    }

    public Cursor getMeal(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM Meals where _id = " + id, null);
        /*while (cursor.moveToNext()){
            String mealName = cursor.getString(1);
            Toast.makeText(activity, "Add Meal" + mealName, Toast.LENGTH_SHORT).show();
        }*/
        return cursor;
    }
}
