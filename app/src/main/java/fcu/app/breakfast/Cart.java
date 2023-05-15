package fcu.app.breakfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Cart extends AppCompatActivity { // 購物車界面

  private EditText ctRemark;
  private Button backToMenu;
  private Button sendCart;
  private ListView cartlist;
  private TextView allprice;
  private Spinner spcartTime;
  private CartDatabase cartdata; // 購物車資料庫

  private String[] time = {};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    // 開啟購物車資料庫
    cartdata = new CartDatabase(this);
    cartdata.open();

    ctRemark = findViewById(R.id.et_ct_remark);
    backToMenu = findViewById(R.id.btn_backtomenu);
    sendCart = findViewById(R.id.btn_ct_send);
    cartlist = findViewById(R.id.lt_orderlist);
    spcartTime = findViewById(R.id.sp_ct_time);
    allprice = findViewById(R.id.tv_order_price);

    // 投放購物車列表
    // 獲得購物車資料，不只一筆
    Cursor cursor = cartdata.getALLCart();
    SimpleCursorAdapter adaptor = new SimpleCursorAdapter(this, R.layout.meal_items_in_cart,
            cursor, new String[] {"name", "customized", "remarke", "unitprice", "quantity"},
            new int[] {R.id.cart_meal_name, R.id.cart_meal_custo, R.id.cart_meal_remark, R.id.cart_meal_price, R.id.cart_meal_quan}, 0);

    cartlist.setAdapter(adaptor);

    // 訂單金額投放
    int price_of_all = cartdata.getAllprice();
    allprice.setText("total: " + Integer.toString(price_of_all));

    // 投放時間列表和抓取
    this.getTime();
    ArrayAdapter<String> spAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
    spAdaptor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    spcartTime.setAdapter(spAdaptor);
    AdapterView.OnItemSelectedListener splistener = new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String carttime = adapterView.getSelectedItem().toString();
        cartdata.setGetMealTime(carttime);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    };
    spcartTime.setOnItemSelectedListener(splistener);

    // 返回監聽
    View.OnClickListener backlistener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(Cart.this, Menu.class);
        startActivity(intent);
      }
    };

    backToMenu.setOnClickListener(backlistener);

    // 送出監聽
    View.OnClickListener sendlistener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        // 加一個防呆: 要有選擇時間
        if(cartdata.getGetMealTime() == null){
          Toast.makeText(Cart.this,"please choose the time", Toast.LENGTH_SHORT).show();
        } else {
          Intent intent = new Intent(Cart.this, ConfirmOrder.class);
          startActivity(intent);
        }

      }
    };

    sendCart.setOnClickListener(sendlistener);
  }

  public void getTime(){

  }
}