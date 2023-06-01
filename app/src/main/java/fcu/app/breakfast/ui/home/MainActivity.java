package fcu.app.breakfast.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fcu.app.breakfast.ui.cart.CartDatabase;
import fcu.app.breakfast.ui.menu.Menu;
import fcu.app.breakfast.R;

public class MainActivity extends AppCompatActivity {
  private CartDatabase databasecart;
  private ListView lvStores;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    databasecart = new CartDatabase(this);
    databasecart.open();
    databasecart.deleteAll();
    databasecart.close();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lvStores=findViewById(R.id.lv_stores);

    List<StoresItem> stores=new ArrayList<StoresItem>();
    stores.add(new StoresItem(R.drawable.icon,"逢甲店","台中市西屯區逢甲路100號"));
    stores.add(new StoresItem(R.drawable.icon,"河南店","台中市南區河南路200號"));
    stores.add(new StoresItem(R.drawable.icon,"文華店","台中市北區文華路300號"));
    StoresListViewAdapter adapter=new StoresListViewAdapter(this,stores);
    lvStores.setAdapter(adapter);

    lvStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        StoresItem selectedStore = stores.get(position);
        String selectedStoreName = selectedStore.getstoreName();

        Toast.makeText(MainActivity.this,"選擇"+selectedStoreName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
      }
    });
  }
}