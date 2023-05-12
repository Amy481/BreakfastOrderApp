package fcu.app.breakfast;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class CartDatabase {
    private AppCompatActivity activity;
    private String storeName;
    private String getMealTime;

    private static final String DATABASE_NAME = "cart.db";

    private static final String CREATE_CART_TABLE = "CREATE TABLE Shopping(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "unitprice Integer, " +
            "quantity Integer, " +
            "customized TEXT, " +
            "remarke TEXT)";

    private SQLiteDatabase db;

    public CartDatabase(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open(){
        db = activity.openOrCreateDatabase(DATABASE_NAME, 0, null);
        db.execSQL(CREATE_CART_TABLE);
    }

    public void addMeal(String name, int unitprice, int quantity, String customized, String remarke) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("unitprice", unitprice);
        values.put("quantity", quantity);
        values.put("customized", customized);
        values.put("remarke", remarke);
        db.insert("Shopping", null, values);
    }

    public Cursor getALLCart(){
        Cursor cursor = db.rawQuery("SELECT * FROM Shopping", null);
        /*while (cursor.moveToNext()){
            String mealName = cursor.getString(1);
            Toast.makeText(activity, "Add Meal" + mealName, Toast.LENGTH_SHORT).show();
        }*/
        return cursor;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGetMealTime() {
        return getMealTime;
    }

    public void setGetMealTime(String getMealTime) {
        this.getMealTime = getMealTime;
    }
}
