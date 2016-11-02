package com.example.vlad.gaz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vlad.gaz.Activities.WeatherList;
import com.example.vlad.gaz.Model.Model;
import com.example.vlad.gaz.Model.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity {
    TextView json;
    Button btngetjson;
    Intent intent;
    HttpUrlConnection httpUrlConnection;
    public  final static String TAG = "TAG: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json = (TextView)findViewById(R.id.json);
        btngetjson = (Button)findViewById(R.id.button2);
        btngetjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Get().execute();
                btngetjson.setVisibility(View.GONE);

            }
        });
    }

    public class Get extends AsyncTask<Object, Object, JSONObject>
    {
        JSONObject jsonObject;

        @Override
        protected JSONObject doInBackground(Object... params) {
            httpUrlConnection = new HttpUrlConnection();
            jsonObject = new JSONObject();
            try {
                jsonObject = httpUrlConnection.HttpRequest("Rostov-na-Donu");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
        @Override
        protected void onPostExecute(JSONObject post) {
            super.onPostExecute(post);
            intent = new Intent(getApplicationContext(), WeatherList.class);
            intent.putExtra("MODEL",jsonObject.toString());
            startActivity(intent);
            finish();
        }
    }

}
