package fcu.app.breakfast.ui.menu;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import fcu.app.breakfast.R;
import fcu.app.breakfast.ui.menu.pageview.PlaceholderFragment;

public class ImageCursorAdapter extends SimpleCursorAdapter {

    private Cursor c;
    private Context context;

    public ImageCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.c = c;
        this.context = context;
    }

    public View getView(int pos, View inView, ViewGroup parent) {
        View v = inView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.food_item_layout, null);
        }

        this.c.moveToPosition(pos);

        // 抓取資料庫資料
        String mealname = this.c.getString(2);
        int mealprice = this.c.getInt(4);
        // 抓取圖片id
        String filename = this.c.getString(5);
        int resID = context.getResources().getIdentifier(filename , "drawable", context.getPackageName());

        ImageView iv = (ImageView) v.findViewById(R.id.IMG_FOOD);

        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.food_item_layout, parent, false);
        }


        iv.setImageResource(resID);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setAdjustViewBounds(true);
        iv.setMaxHeight(300);

        TextView fname = (TextView) v.findViewById(R.id.TV_FOOD_NAME);
        fname.setText(mealname);

        TextView lname = (TextView) v.findViewById(R.id.TV_FOOD_PRICE);
        lname.setText(Integer.toString(mealprice));

        return(v);
    }
}

