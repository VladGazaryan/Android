package com.example.vlad.gaz;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity {
    TextView json;
    Button btngetjson;
    HttpUrlConnection httpUrlConnection;
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

    public class Get extends AsyncTask<Void,JSONObject,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(Void... params) {
            httpUrlConnection = new HttpUrlConnection();
            JSONObject jsonObject = new JSONObject();
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
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            json.setText(jsonObject.toString());
        }
    }

}
