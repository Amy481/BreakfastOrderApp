package fcu.app.breakfast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StoresListViewAdapter extends BaseAdapter {
    private Context context;
    private List<StoresItem> liststores;
    public StoresListViewAdapter(Context context,List<StoresItem> liststores){
        this.context=context;
        this.liststores=liststores;
    }
    @Override
    public int getCount() {
        return liststores.size();
    }

    @Override
    public Object getItem(int i) {
        return liststores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.stores_item_layout,viewGroup,false);
        }
        StoresItem food = liststores.get(i);
        ImageView iv=view.findViewById(R.id.iv_stores);
        iv.setImageResource(food.getImageId());
        iv.setScaleType((ImageView.ScaleType.CENTER_CROP));
        iv.setAdjustViewBounds(true);
        iv.setMaxHeight(500);
        iv.setMaxWidth(500);

        TextView tvFoodPrice = view.findViewById(R.id.tv_store_name);
        tvFoodPrice.setText(String.valueOf(food.getstoreName()));

        TextView tvFoodName = view.findViewById(R.id.tv_store_address);
        tvFoodName.setText(String.valueOf(food.getstoreAddress()));
        return view;
    }
}