package fcu.app.breakfast.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

public class BuildMenu {

    static void builingMenu(AppCompatActivity activity){

        MenuDatabase manudata;


        manudata = new MenuDatabase(activity);
        manudata.open();

        manudata.deleteAll();

        // 資料
        String[] mealliat = {"mainmeal 炸雞堡 炸雞排佐黃芥末醬 50 image_1",
                "mainmeal 蛋沙拉三明治 水煮蛋加美乃滋 40 image_2",
                "mainmeal 培根義麵 煎培根與白醬 75 image_3",
                "snack 薯條 外酥內軟 30 image_1",
                "drink 橙汁 新鮮現榨 45 image_2"};

        // 讀取陣列 分割字串
        for(String x: mealliat){
            String[] temp = x.split(" "); // 切片
            manudata.addMeal(temp[0], temp[1], temp[2], Integer.valueOf(temp[3]), temp[4]); // 寫入資料庫
        }

        manudata.close();


    }
}
