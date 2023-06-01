package fcu.app.breakfast.ui.menu.pageview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fcu.app.breakfast.R;
import fcu.app.breakfast.databinding.FragmentMenuBinding;
import fcu.app.breakfast.ui.cart.Cart;
import fcu.app.breakfast.ui.cart.CartDatabase;
import fcu.app.breakfast.ui.cart.Product;
import fcu.app.breakfast.ui.menu.ImageCursorAdapter;
import fcu.app.breakfast.ui.menu.MenuDatabase;
/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
  private TextView tvMealClass;
  private Button btnBills;
  private MenuDatabase databaseHandler;
  private CartDatabase databaseCart;
  private ListView lvMeals;
  private static final String ARG_SECTION_NUMBER = "section_number";

  private PageViewModel pageViewModel;
  private FragmentMenuBinding binding;

  public static PlaceholderFragment newInstance(int index) {
    PlaceholderFragment fragment = new PlaceholderFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
    int index = 1;
    if (getArguments() != null) {
      index = getArguments().getInt(ARG_SECTION_NUMBER);
    }
    pageViewModel.setIndex(index);
  }

  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {

    databaseHandler = new MenuDatabase((AppCompatActivity) getActivity());
    databaseHandler.open();

    View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
    lvMeals = rootView.findViewById(R.id.lv_menu_list);
    //tvMealClass = rootView.findViewById(R.id.tv_meal_class);
    btnBills =rootView.findViewById(R.id.btn_checkout_bills);


    lvMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
        @SuppressLint("Range") int productId = cursor.getInt(cursor.getColumnIndex("_id"));

        Intent intent = new Intent(getActivity(), Product.class);
        intent.putExtra("productId", productId);

        //Toast.makeText(getActivity(),"餐點"+String.valueOf(productId).get, Toast.LENGTH_SHORT).show();

        startActivity(intent);
      }
    });

    databaseCart = new CartDatabase((AppCompatActivity) getActivity());
    databaseCart.open();
    if (databaseCart.isTableExists(databaseCart.getDatabase(), "Shopping") && databaseCart.getCartItemCount() > 0) {
      btnBills.setVisibility(View.VISIBLE);
    } else {
      btnBills.setVisibility(View.GONE);
    }
    databaseCart.close();

    btnBills.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Cart.class);
        // 在這裡可以添加任何需要傳遞到 Cart 頁面的資料

        // 啟動 Cart 頁面
        startActivity(intent);
      }
    });
    //databaseHandler.addMeal("mainmeal" ,"chicken-burger" ,"fried-chicken-with-mustard ",50, "chickenB.pjg");
    showAllMeals();
    return rootView;
  }

  private void showAllMeals() {
    int index = getArguments().getInt(ARG_SECTION_NUMBER);
    Cursor cursor = null;
    if(index==1)
    {cursor = databaseHandler.getClassMeal("mainmeal");}
    else if(index==2)
    {cursor = databaseHandler.getClassMeal("snack");}
    else if(index==3)
    {cursor = databaseHandler.getClassMeal("drink");}
    /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(requireContext(), R.layout.food_item_layout,
            cursor, new String[]{"name", "price"}, new int[]{R.id.TV_FOOD_NAME, R.id.TV_FOOD_PRICE}, 0);*/
    SimpleCursorAdapter adapter = new ImageCursorAdapter(this.getActivity(), R.layout.food_item_layout,
            cursor, new String[]{"name", "price", "filename"},
            new int[]{R.id.TV_FOOD_NAME, R.id.TV_FOOD_PRICE, R.id.IMG_FOOD});
    lvMeals.setAdapter(adapter);
  }
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}