package fcu.app.breakfast.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

public class BuildMenu {

    static void builingMenu(AppCompatActivity activity){

        MenuDatabase manudata;


        manudata = new MenuDatabase(activity);
        manudata.open();

        manudata.deleteAll();

        // 資料
        String[] mealliat = {"mainmeal 漢堡 漢堡排與生菜番茄起司片 50 burger",
                "mainmeal 蛋沙拉三明治 一層蛋沙拉、一層起司生菜火腿 40 sandwich",
                "mainmeal 肉包 鬆軟面皮與蔥花肉餡 30 baozi",
                "mainmeal 雞柳捲餅 柔韌餅皮捲炸雞柳搭配美乃滋 45 burrito",
                "mainmeal 可頌三明治 酥到掉渣可頌麵包夾火腿生菜 45 croissant",
                "mainmeal 蛋餅 經典不敗 35 eggcake",
                "mainmeal 培根義麵 煎培根與白醬義大利麵 75 image_3",
                "mainmeal 小籠包 一籠7顆，湯汁豐富 70 xiaolongbao",
                "snack 薯條 外酥內軟他配番茄醬 30 fries",
                "snack 荷包蛋 一顆單面煎太陽蛋 15 egg",
                "snack 熱狗 兩根炸熱狗 20 hotdog",
                "snack 雞塊 4塊炸雞塊 25 nuggets",
                "snack 地瓜球 10顆地瓜QQ球 30 qqball",
                "snack 薯泥沙拉 一球馬鈴薯泥黃瓜紅蘿蔔拌美乃滋 25 salad",
                "snack 厚片 烤到微焦的奶酥厚片吐司 25 toast",
                "drink 橙汁 新鮮現榨柳橙汁 35 juice",
                "drink 紅茶 古早味紅茶 15 blacktea",
                "drink 奶茶 香甜奶茶 25 milktea",
                "drink 豆漿 純正豆香 15 soybean",
                "drink 玉米濃湯 限量供應 35 cornsoup"};

        // 讀取陣列 分割字串
        for(String x: mealliat){
            String[] temp = x.split(" "); // 切片
            manudata.addMeal(temp[0], temp[1], temp[2], Integer.valueOf(temp[3]), temp[4]); // 寫入資料庫
        }

        manudata.close();


    }
}
