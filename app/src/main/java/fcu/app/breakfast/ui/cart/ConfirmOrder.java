package fcu.app.breakfast.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import fcu.app.breakfast.R;

public class ConfirmOrder extends AppCompatActivity { // 確認訂單介面

    private CartDatabase cartdata;
    private TextView orderPrice;
    private TextView orderTime;
    private ListView orderlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        cartdata = new CartDatabase(this);
        cartdata.open();

        orderPrice = findViewById(R.id.tv_order_price);
        orderTime = findViewById(R.id.tv_order_time);
        orderlist = findViewById(R.id.lt_orderlist);

        // 投放購物車列表
        // 獲得購物車資料，不只一筆
        Cursor cursor = cartdata.getALLCart();
        SimpleCursorAdapter adaptor = new SimpleCursorAdapter(this, R.layout.meal_items_in_cart,
                cursor, new String[] {"name", "customized", "remarke", "unitprice", "quantity"},
                new int[] {R.id.cart_meal_name, R.id.cart_meal_custo, R.id.cart_meal_remark, R.id.cart_meal_price, R.id.cart_meal_quan}, 0);

        orderlist.setAdapter(adaptor);

        // 時間
        String selectedMealTime = getIntent().getStringExtra("time");
        orderTime.setText("time to get meal: " + selectedMealTime);

        // 總金額
        int price_of_all = cartdata.getAllprice();
        orderPrice.setText("total: " + Integer.toString(price_of_all));
    }
}