package fcu.app.breakfast.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

public class BuildMenu {

    static void builingMenu(AppCompatActivity activity){

        MenuDatabase manudata;


        manudata = new MenuDatabase(activity);
        manudata.open();

        manudata.deleteAll();

        // 資料
        String[] mealliat = {"mainmeal chicken-burger fried-chicken-with-mustard 50 burger.png",
                "mainmeal eggsalad-sandwich boiled-egg-with-mayonnaise 40 eggsaladS.jpg",
                "mainmeal bacon-pasta fried-bacon-with-cream 75 baconP.jpg",
                "snack fries crispy-and-soft 30 fries.png",
                "drink juice fresh-orange-juice 45 juice.png"};

        // 讀取陣列 分割字串
        for(String x: mealliat){
            String[] temp = x.split(" "); // 切片
            manudata.addMeal(temp[0], temp[1], temp[2], Integer.valueOf(temp[3]), temp[4]); // 寫入資料庫
        }

        manudata.close();


    }
}
