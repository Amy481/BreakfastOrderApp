package fcu.app.breakfast.ui.menu.pageview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fcu.app.breakfast.R;
import fcu.app.breakfast.databinding.FragmentMenuBinding;
import fcu.app.breakfast.ui.menu.MenuDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
  private TextView tvMealClass;
  private MenuDatabase databaseHandler;
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
    tvMealClass=rootView.findViewById(R.id.tv_meal_class);
    setMealClass("菜單");

    showAllMeals();
    return rootView;
    //binding = FragmentMenuBinding.inflate(inflater, container, false);
    //View root = binding.getRoot();

    //View root = inflater.inflate(R.layout.fragment_menu, container, false);
//    final TextView textView = binding.lvMenuList.sectionLabel;
//    pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//      @Override
//      public void onChanged(@Nullable String s) {
//        textView.setText(s);
//      }
//    });
    //return root;
  }
  public void setMealClass(String mealClass) {
      tvMealClass.setText(mealClass);
  }
  private void showAllMeals() {
    Cursor cursor = databaseHandler.getMeal(1);
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(requireContext(), R.layout.food_item_layout,
            cursor, new String[]{"name", "price"}, new int[]{R.id.TV_FOOD_NAME, R.id.TV_FOOD_PRICE}, 0);
    lvMeals.setAdapter(adapter);
  }
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}