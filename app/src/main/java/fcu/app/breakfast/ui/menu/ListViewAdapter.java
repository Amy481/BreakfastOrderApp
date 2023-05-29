package fcu.app.breakfast.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fcu.app.breakfast.R;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<FoodItem> listFoods;
    public ListViewAdapter(Context context,List<FoodItem> listFoods){
        this.context=context;
        this.listFoods=listFoods;
    }
    @Override
    public int getCount() {
        return listFoods.size();
    }

    @Override
    public Object getItem(int i) {
        return listFoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.food_item_layout,viewGroup,false);
        }
        FoodItem food = listFoods.get(i);
        ImageView iv=view.findViewById(R.id.IMG_FOOD);
        iv.setImageResource(food.getImageId());
        iv.setScaleType((ImageView.ScaleType.CENTER_CROP));

        TextView tvFoodPrice = view.findViewById(R.id.TV_FOOD_PRICE);
        tvFoodPrice.setText(String.valueOf(food.getFoodPrice()));

        TextView tvFoodName = view.findViewById(R.id.TV_FOOD_NAME);
        tvFoodName.setText(String.valueOf(food.getFoodName()));
        return view;
    }
}