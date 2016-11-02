package com.example.vlad.gaz.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.vlad.gaz.Model.Model;
import com.example.vlad.gaz.Model.Parser;
import com.example.vlad.gaz.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WeatherList extends Activity {
    final String ID = "";
    final String ATR_IC = "ic";
    final String ATR_DATE = "date";
    final String ATR_TEMP = "temp";
    public final static String TAG = "LIST: ";
    ListView lvSimple;
    Map<String, String> m;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, String>> data;
    Intent intent;
    Parser parser = new Parser();
    String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_list);

        lvSimple = (ListView) findViewById(R.id.list);

        data = new ArrayList<Map<String, String>>();
        String[] from = {ID,ATR_IC, ATR_DATE, ATR_TEMP};
        int[] to = {R.id.idd,R.id.item_ic, R.id.item_date, R.id.item_temp};
        new InflateList().execute();


        sAdapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);

        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent   = new Intent(getApplicationContext(),WeatherMain.class);
                startActivity(intent);
            }
        });
    }

    private class InflateList extends AsyncTask<Void, Model[], Model[]> {
        Model[] model = new Model[5];
        JSONObject jsonObject;
        ImageView bmImage;
        Bitmap mIcon11 = null;

        @Override
        protected Model[] doInBackground(Void... params) {
            intent = getIntent();
            json = intent.getStringExtra("MODEL");
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < model.length; i++) {
                try {
                    model[i] = parser.getData(jsonObject, i * 8);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "array " + " " + model[i].date.getDt_txt());
                m = new HashMap<String, String>();
                m.put(ID, String.valueOf(i));
                m.put(ATR_IC, model[i].image.getIcon());
                m.put(ATR_DATE, model[i].date.getDt_txt());
                m.put(ATR_TEMP, String.valueOf(model[i].temperature.getCurrent()));
                data.add(m);
            }
            return model;
        }
    }
}



