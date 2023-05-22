package fcu.app.breakfast.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

//import android.view.Menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import fcu.app.breakfast.R;
import fcu.app.breakfast.ui.cart.Cart;
import fcu.app.breakfast.ui.menu.pageview.SectionsPagerAdapter;
import fcu.app.breakfast.databinding.ActivityMenuBinding;

public class Menu extends AppCompatActivity {
  private ActivityMenuBinding binding;

  private MenuDatabase manudata;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMenuBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
    ViewPager viewPager = binding.viewPager;
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = binding.tabs;
    tabs.setupWithViewPager(viewPager);

    /* 傳入菜單(建立菜單資料庫) */
    // 開啟資料庫
    manudata = new MenuDatabase(this);
    manudata.open();
    // 讀取檔案((檔案要放在需要的位置
    File prjDir = this.getFilesDir();
    File manuText = new File(prjDir, "menu.txt");

    try {
      FileInputStream fis = new FileInputStream(manuText);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);

      String line = "";
      StringBuilder sbContent = new StringBuilder();
      // 讀取檔案資料
      while ((line = br.readLine()) != null){
        sbContent.append(line);
        String[] temp = sbContent.toString().split(" "); // 切片
        manudata.addMeal(temp[0], temp[1], temp[2], Integer.valueOf(temp[3]), temp[4]);
      }

    } catch (FileNotFoundException e){
      throw new RuntimeException(e);
    } catch (IOException e){
      throw new RuntimeException(e);
    }

  }
}