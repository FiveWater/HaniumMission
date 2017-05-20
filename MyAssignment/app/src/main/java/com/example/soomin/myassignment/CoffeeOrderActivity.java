package com.example.soomin.myassignment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SOOMIN on 2017-04-02.
 */

public class CoffeeOrderActivity extends AppCompatActivity{

    ListView listView;
    ListViewAdapter adapter;
    TextView preText, totalPrice;
    ImageView preImage;
    LinearLayout inLayout;
    Double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.actionbar_coffeeorder);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            Log.e("TAG", e.getMessage());
        }

        setContentView(R.layout.layout_coffeeorder);

        listView = (ListView) findViewById(R.id.coffee_items_listview);
        adapter = new ListViewAdapter();

        preText =  (TextView) findViewById(R.id.text_robot_say2);
        preImage = (ImageView) findViewById(R.id.progress_1);
        inLayout = (LinearLayout) findViewById(R.id.coffee_order_view);

        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Esspresso", "3.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.coffee_icon), "Americano", "2.6");

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ListViewAdapter extends BaseAdapter {

        int count = 0;

        private ArrayList listItem = new ArrayList();

        // Adapter 에 추가된 데이터를 저장하기 위한 ArrayList
        private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

        // Adapter 에 사용되는 데이터의 개수를 리턴
        @Override
        public int getCount(){
            return listViewItemList.size();
        }

        // position 에 위치한 데이터를 화면에 출력하는데 사용될 view 를 리턴
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            final Integer pos = position;
            final Context context = parent.getContext();

            // "listview_item" layout 을 inflate 하여 convertView 참조 획득
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_coffee_items, parent, false);
            }

            // 화면에 표시될 View(Layout 이 inflate 된) 로부터 위젯에 대한 참조 획득
            ImageView iconImageView = (ImageView) convertView.findViewById(R.id.image_Esspresso);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.coffeeText);
            final TextView priceTextView = (TextView) convertView.findViewById(R.id.priceText);

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check);

            // Data set(listViewItemList)에서 position 에 위치한 데이터 참조 획득
            final ListViewItem listViewItem = listViewItemList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            iconImageView.setImageDrawable(listViewItem.getIcon());
            titleTextView.setText(listViewItem.getTitle());
            priceTextView.setText(listViewItem.getPrice());

            if(checkBox != null){
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // 체크 할 때_처음 체크
                        if(isChecked && count == 0){
                            count += 1;
                            for (int i = 0; i < listItem.size(); i++) {
                                if (listItem.get(i) == pos) {
                                    System.out.println(pos); //로그찍기
                                    return;
                                }
                            }
                            total += Double.valueOf(priceTextView.getText().toString()).doubleValue();
                            System.out.println(pos+", "+priceTextView.getText()); //로그찍기

                            // 진행 바, 설명 변경
                            preText.setText(R.string.robot_say3);
                            preImage.setImageResource(R.drawable.progress_2);

                            // order 버튼 추가
                            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            RelativeLayout outLayout = (RelativeLayout) inflater.inflate(R.layout.layout_order_button, null);
                            inLayout.addView(outLayout);
                            setContentView(inLayout);

                            totalPrice = (TextView) findViewById(R.id.orderPrice);
                            // total price 변경
                            totalPrice.setText("(Total - "+total+" won)");

                            listItem.add(pos);
                        }
                        // 체크 할 때_2번째 이상 체크
                        else if(isChecked && count != 0){
                            for (int i = 0; i < listItem.size(); i++) {
                                if (listItem.get(i) == pos) {
                                    System.out.println(pos); //로그찍기
                                    return;
                                }
                            }
                            total += Double.valueOf(priceTextView.getText().toString()).doubleValue();
                            System.out.println(pos+", "+priceTextView.getText()); //로그찍기

                            totalPrice = (TextView) findViewById(R.id.orderPrice);
                            // total price 변경
                            totalPrice.setText("(Total - "+total+" won)");

                            listItem.add(pos);
                        }
                        // 체크 해제할 때
                        else{
                            for (int i =0; i < listItem.size(); i++) {
                                if (listItem.get(i) == pos) {
                                    listItem.remove(i);
                                    break;
                                }
                            }
                            total -= Double.valueOf(priceTextView.getText().toString()).doubleValue();
                            System.out.println(pos+", "+priceTextView.getText()); //로그찍기

                            totalPrice = (TextView) findViewById(R.id.orderPrice);
                            // total price 변경
                            totalPrice.setText("(Total - "+total+" won)");
                        }
                    }
                });
                // 체크된 아이템인지 판단할 boolean 변수
                boolean isChecked = false;
                for (int i = 0; i < listItem.size(); i++) {
                    // 만약 체크되었던 아이템이라면
                    if (listItem.get(i) == pos) {
                        // 체크를 한다
                        checkBox.setChecked(true);
                        isChecked = true;
                        break;
                    }
                }
                // 아니라면 체크 안함
                if (!isChecked) {
                    checkBox.setChecked(false);
                }
            }
            return  convertView;
        }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
        @Override
        public long getItemId(int position){
            return position;
        }

        // 지정한 위치(position)에 있는 데이터 리턴
        @Override
        public Object getItem(int position){
            return listViewItemList.get(position);
        }

        // 아이템 데이터 추가를 위한 함수 (개발자가 원하는대로 작성)
        public void addItem(Drawable icon, String title, String price){
            ListViewItem item = new ListViewItem();

            item.setIcon(icon);
            item.setTitle(title);
            item.setPrice(price);

            listViewItemList.add(item);
        }
    }
}
