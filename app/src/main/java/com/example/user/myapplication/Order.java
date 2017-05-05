package com.example.user.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Order extends AppCompatActivity  {

    public static String KEY_ORDER = "KEY_ORDER";
    public static String KEY_PRICE = "KEY_PRICE";
    EditText et_order;
    EditText et_price;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        et_order = (EditText)findViewById(R.id.ET_order);
        et_price = (EditText)findViewById(R.id.ET_price);

        Button btn = (Button)findViewById(R.id.BTN_order);
        btn.setOnClickListener(submit);
        Button btn2 = (Button)findViewById(R.id.BTN_Cancel);
        btn2.setOnClickListener(cancel);

        ArrayList<StoreMenu> store = new ArrayList<StoreMenu>();

        store.add(new StoreMenu(R.drawable.dp, "達美樂"));
        store.add(new StoreMenu(R.drawable.kfc, "肯德基"));
        store.add(new StoreMenu(R.drawable.mc, "麥當勞"));
        store.add(new StoreMenu(R.drawable.npl, "拿坡里"));
        store.add(new StoreMenu(R.drawable.ph, "必勝客"));

        StoreArrayAdapter adapter = new StoreArrayAdapter(this, store);

        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(click);
    }

    private OnClickListener cancel = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    };

    private OnClickListener submit = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String order = et_order.getText().toString();
            String price = et_price.getText().toString();
            Intent intent = new Intent();

            intent.setClass(Order.this, Room.class);
            intent.putExtra(KEY_ORDER,order);
            intent.putExtra(KEY_PRICE,price);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    private OnItemClickListener click= new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    Uri uri0 = Uri.parse("https://www.dominos.com.tw/Menu/MenuNew.aspx");
                    Intent i0 = new Intent(Intent.ACTION_VIEW, uri0);
                    startActivity(i0);
                    break;
                case 1:
                    Uri uri1 = Uri.parse("https://order.kfcclub.com.tw/web/Form/ProdBrowseIndex.html?code=new");
                    Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(i1);
                    break;
                case 2:
                    Uri uri2 = Uri.parse("http://www.mcdonalds.com.tw/tw/ch/mdsl/mds_menu.html");
                    Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                    startActivity(i2);
                    break;
                case 3:
                    Uri uri3 = Uri.parse("https://www.0800076666.com.tw/Menu/MenuNew");
                    Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                    startActivity(i3);
                    break;
                case 4:
                    Uri uri4 = Uri.parse("http://www.pizzahut.com.tw/menu/");
                    Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                    startActivity(i4);
                    break;
            }
        }
    };

   /* protected void onListItemClick(ListView l, View v, int position, long id){

        switch(position){
            case 0:
                Uri uri0 = Uri.parse("https://www.dominos.com.tw/Menu/MenuNew.aspx");
                Intent i0 = new Intent(Intent.ACTION_VIEW, uri0);
                startActivity(i0);
                break;
            case 1:
                Uri uri1 = Uri.parse("https://order.kfcclub.com.tw/web/Form/ProdBrowseIndex.html?code=new");
                Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(i1);
                break;
            case 2:
                Uri uri2 = Uri.parse("http://www.mcdonalds.com.tw/tw/ch/mdsl/mds_menu.html");
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;
            case 3:
                Uri uri3 = Uri.parse("https://www.0800076666.com.tw/Menu/MenuNew");
                Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(i3);
                break;
            case 4:
                Uri uri4 = Uri.parse("http://www.pizzahut.com.tw/menu/");
                Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(i4);
                break;

        }
    }*/
}
