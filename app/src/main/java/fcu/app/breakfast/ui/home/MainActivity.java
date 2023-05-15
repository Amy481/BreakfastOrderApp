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

import fcu.app.breakfast.ui.menu.Menu;
import fcu.app.breakfast.R;

public class MainActivity extends AppCompatActivity {

  private ListView lvStores;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lvStores=findViewById(R.id.lv_stores);

    List<StoresItem> srores=new ArrayList<StoresItem>();
    srores.add(new StoresItem(R.drawable.image_1,"分店一","分店一地址"));
    srores.add(new StoresItem(R.drawable.image_2,"分店二","分店二地址"));
    srores.add(new StoresItem(R.drawable.image_3,"分店三","分店三地址"));
    StoresListViewAdapter adapter=new StoresListViewAdapter(this,srores);
    lvStores.setAdapter(adapter);

    lvStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(MainActivity.this,"選擇"+String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
      }
    });

    /* 測試用，用來找資料放置位置
    File prjDir = this.getFilesDir();
    File helloText = new File(prjDir, "hello.txt");

    try {
      FileOutputStream out = new FileOutputStream(helloText);
      String hello = "Hello World!\nHello FCU!";
      byte[] helloBytes = hello.getBytes();
      out.write(helloBytes);
      out.close();
    } catch (FileNotFoundException e){
      throw new RuntimeException(e);
    } catch (IOException e){
      throw new RuntimeException(e);
    }*/
  }
}