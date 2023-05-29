package fcu.app.breakfast.ui.menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class MenuDatabase { // 菜單資料庫
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

        // 檢查資料表是否已存在
        if (!isTableExists(db, "Meals")) {
            db.execSQL(CREATE_MEAL_TABLE);
        }
    }
    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean exists = (cursor != null) && (cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        return exists;
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

    public Cursor getClassMeal(String classification){
        Cursor cursor = db.rawQuery("SELECT * FROM Meals where classification = '"+classification+"'", null);
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

    public void close() {
    }

    public void deleteAll(){
        db.execSQL("delete from Meals");
    }
}
