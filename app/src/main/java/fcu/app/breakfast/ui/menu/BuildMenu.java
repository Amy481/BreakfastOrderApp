package fcu.app.breakfast.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

public class BuildMenu {

    static void builingMenu(AppCompatActivity activity){

        MenuDatabase manudata;


        manudata = new MenuDatabase(activity);
        manudata.open();

        manudata.deleteAll();

        // 資料
        String[] mealliat = {"mainmeal chicken-burger fried-chicken-with-mustard 50 image_1",
                "mainmeal eggsalad-sandwich boiled-egg-with-mayonnaise 40 image_2",
                "mainmeal bacon-pasta fried-bacon-with-cream 75 image_3",
                "snack fries crispy-and-soft 30 image_1",
                "drink juice fresh-orange-juice 45 image_2"};

        // 讀取陣列 分割字串
        for(String x: mealliat){
            String[] temp = x.split(" "); // 切片
            manudata.addMeal(temp[0], temp[1], temp[2], Integer.valueOf(temp[3]), temp[4]); // 寫入資料庫
        }

        manudata.close();


    }
}
