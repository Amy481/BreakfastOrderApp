package fcu.app.breakfast;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Product extends AppCompatActivity {

  private int id;
  private MenuDatabase menudata;
  private CartDatabase cartdata;

  private TextView tvmeal_name;
  private ImageView imgmeal_img;
  private TextView tvmeal_discrip;
  private CheckBox ckboption1;
  private CheckBox ckboption2;
  private CheckBox ckboption3;
  private EditText etmeal_remark;
  private Button btnadd_meal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);

    menudata = new MenuDatabase(this);
    menudata.open();

    tvmeal_name = findViewById(R.id.tv_pd_meal_name);
    imgmeal_img = findViewById(R.id.img_pd_meal);
    tvmeal_discrip = findViewById(R.id.tv_pd_meal_descrip);
    ckboption1 = findViewById(R.id.ckb_first);
    ckboption2 = findViewById(R.id.ckb_second);
    ckboption3 = findViewById(R.id.ckb_third);
    etmeal_remark = findViewById(R.id.et_pd_remark);
    btnadd_meal = findViewById(R.id.btn_add_meal);

    Cursor cursor = menudata.getMeal(id);

    String classification = cursor.getString(1);
    String maelName = cursor.getString(2);
    String mealdiscrip = cursor.getString(3);
    int mealprice = cursor.getInt(4);
    String img_file = cursor.getString(5);

    tvmeal_name.setText(maelName);
    tvmeal_discrip.setText(mealdiscrip);
    String option1 = "";
    String option2 = "";
    String option3 = "";
    if(classification.equals("burger")){  //可以自由新增
      option1 = "more sauce";
      option2 = "more veg";
      option3 = "less sauce";
    }
    ckboption1.setText(option1);
    ckboption2.setText(option2);
    ckboption3.setText(option3);
  }
}