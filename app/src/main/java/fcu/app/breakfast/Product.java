package fcu.app.breakfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Product extends AppCompatActivity { // 商品介面

  private int id; // 假設從菜單那裡得到的id
  private MenuDatabase menudata; // 菜單資料庫
  private CartDatabase cartdata; // 購物車資料庫

  // 抓介面
  private TextView tvmeal_name;
  private ImageView imgmeal_img;
  private TextView tvmeal_discrip;
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
    // 開菜單資料庫抓特定資料
    menudata = new MenuDatabase(this);
    menudata.open();
    cartdata = new CartDatabase(this);
    cartdata.open();

    // 抓介面id
    tvmeal_name = findViewById(R.id.tv_pd_meal_name);
    imgmeal_img = findViewById(R.id.img_pd_meal);
    tvmeal_discrip = findViewById(R.id.tv_pd_meal_descrip);
    ckboption1 = findViewById(R.id.ckb_first);
    ckboption2 = findViewById(R.id.ckb_second);
    ckboption3 = findViewById(R.id.ckb_third);
    etmeal_remark = findViewById(R.id.et_pd_remark);
    btnadd_meal = findViewById(R.id.btn_add_meal);
    spmealQuan = findViewById(R.id.sp_pd_quantity);

    // 獲得特定商品資料
    Cursor cursor = menudata.getMeal(id); // 只會有一筆

    // 得到的所有商品資料
    String classification = cursor.getString(1); // 類別 用來識別客製化要放的
    String maelName = cursor.getString(2); // 商品名稱
    String mealdiscrip = cursor.getString(3); // 商品描述
    int mealprice = cursor.getInt(4); // 商品價格
    String img_file = cursor.getString(5); // 商品圖片名稱

    // 商品文字資料投放到介面上
    tvmeal_name.setText(maelName);
    tvmeal_discrip.setText(mealdiscrip);
    String option1, option2, option3;
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
    File prjDir = this.getFilesDir();

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
    String cbMessage = ",";
    CompoundButton.OnCheckedChangeListener cblistener = new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(ckboption1.isChecked()) {
          cbMessage = cbMessage + option1 + ",";
        }
        if(ckboption2.isChecked()) {
          cbMessage = cbMessage + option2 + ",";
        }
        if(ckboption3.isChecked()) {
          cbMessage = cbMessage + option3 + ",";
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
        if(etmeal_remark.getText() == null){
          String mealRemark = "";
        } else{
          String mealRemark = etmeal_remark.getText().toString();
        }

        cartdata.addMeal(maelName, mealprice, quantity, cbMessage, mealRemark);
        Intent intent = new Intent(Product.this, Menu.class);
        startActivity(intent);
      }
    };

    btnadd_meal.setOnClickListener(onClickListener);

  }
}