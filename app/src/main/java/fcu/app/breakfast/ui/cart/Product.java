package fcu.app.breakfast.ui.cart;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import fcu.app.breakfast.R;
import fcu.app.breakfast.ui.menu.Menu;
import fcu.app.breakfast.ui.menu.MenuDatabase;

public class Product extends AppCompatActivity { // 商品介面

  //private int id; // 假設從菜單那裡得到的id
  private MenuDatabase menudata; // 菜單資料庫
  private CartDatabase cartdata; // 購物車資料庫

  // 抓介面
  private TextView tvmeal_name;
  private ImageView imgmeal_img;
  private TextView tvmeal_discrip;
  private TextView tvmeal_price;
  private CheckBox ckboption1;
  private CheckBox ckboption2;
  private CheckBox ckboption3;
  private EditText etmeal_remark;
  private Button btnadd_meal;
  private Spinner spmealQuan;

  private int quantity;
  private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);

    // 獲取傳遞的產品 ID
    Intent intent = getIntent();
    int id = intent.getIntExtra("productId", -1); // 0 是預設值，可以根據需要修改
    Toast.makeText(Product.this,"跳轉"+id, Toast.LENGTH_SHORT).show();

    // 開菜單資料庫抓特定資料
    menudata = new MenuDatabase(this);
    menudata.open();
    cartdata = new CartDatabase(this);
    cartdata.open();

    // 抓介面id
    tvmeal_name = findViewById(R.id.tv_pd_meal_name);
    tvmeal_discrip = findViewById(R.id.tv_pd_meal_descrip);
    tvmeal_price = findViewById(R.id.tv_pd_meal_price);
    ckboption1 = findViewById(R.id.ckb_first);
    ckboption2 = findViewById(R.id.ckb_second);
    ckboption3 = findViewById(R.id.ckb_third);
    etmeal_remark = findViewById(R.id.et_pd_remark);
    btnadd_meal = findViewById(R.id.btn_add_meal);
    spmealQuan = findViewById(R.id.sp_pd_quantity);

    // 獲得特定商品資料
    Cursor cursor = menudata.getMeal(id); // 只會有一筆
    Toast.makeText(Product.this,"getMeal "+id, Toast.LENGTH_SHORT).show();
    if (cursor != null && cursor.moveToFirst()) {
      // 繼續提取資料的程式碼


    // 得到的所有商品資料
    String classification = cursor.getString(1); // 類別 用來識別客製化要放的
    String maelName = cursor.getString(2); // 商品名稱
    String mealdiscrip = cursor.getString(3); // 商品描述
    int mealprice = cursor.getInt(4); // 商品價格
    String img_file = cursor.getString(5); // 商品圖片名稱

    // 商品文字資料投放到介面上
    tvmeal_name.setText(maelName);
    tvmeal_discrip.setText(mealdiscrip);
    String mealPrice="一份 "+String.valueOf(mealprice)+" 元";
    tvmeal_price.setText(mealPrice);
    String option1="";
    String option2="";
    String option3="";
    // 客製化選項投放
    if(classification.equals("burger")){  //可以自由新增
      option1 = "more sauce";
      option2 = "more veg";
      option3 = "less sauce";
    }
    ckboption1.setText(option1);
    ckboption2.setText(option2);
    ckboption3.setText(option3);

    // 投放圖片
    int resID = getResources().getIdentifier(img_file , "drawable", getPackageName());

    ImageView iv = findViewById(R.id.img_pd_meal);
    iv.setImageResource(resID);
    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
    iv.setAdjustViewBounds(true);
    iv.setMaxWidth(300);
    iv.setMaxHeight(300);

/*
    try {
      FileInputStream mealImgFis = openFileInput(img_file);
      Bitmap bitmap = BitmapFactory.decodeStream(mealImgFis);
      mealImgFis.close();
      imgmeal_img.setImageBitmap(bitmap);
    } catch (FileNotFoundException e){
      throw new RuntimeException(e);
    } catch (IOException e){
      throw new RuntimeException(e);
    }
 */
    // 時間選項

    ArrayAdapter<String> spAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
    spAdaptor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    spmealQuan.setAdapter(spAdaptor);
    AdapterView.OnItemSelectedListener splistener = new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        quantity = i+1;
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    };
    spmealQuan.setOnItemSelectedListener(splistener);

    // 獲得客製化內容
    final String[] cbMessage = {","};
    String finalOption1 = option1;
    String finalOption2 = option2;
    String finalOption3 = option3;
    CompoundButton.OnCheckedChangeListener cblistener = new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(ckboption1.isChecked()) {
          cbMessage[0] = cbMessage[0] + finalOption1 + ",";
        }
        if(ckboption2.isChecked()) {
          cbMessage[0] = cbMessage[0] + finalOption2 + ",";
        }
        if(ckboption3.isChecked()) {
          cbMessage[0] = cbMessage[0] + finalOption3 + ",";
        }
      }
    };

    ckboption1.setOnCheckedChangeListener(cblistener);
    ckboption2.setOnCheckedChangeListener(cblistener);
    ckboption3.setOnCheckedChangeListener(cblistener);
    // 監聽加入按鈕  備註
    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String mealRemark;
        if(etmeal_remark.getText() == null){
          mealRemark = "";
        } else{
          mealRemark = etmeal_remark.getText().toString();
        }

        cartdata.addMeal(maelName, mealprice, quantity, cbMessage[0], mealRemark);
        Intent intent = new Intent(Product.this, Menu.class);
        startActivity(intent);
      }
    };

    btnadd_meal.setOnClickListener(onClickListener);

  }
  }
}