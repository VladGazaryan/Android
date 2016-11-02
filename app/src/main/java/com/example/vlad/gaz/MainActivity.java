package com.example.vlad.gaz;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vlad.gaz.Model.Model;
import com.example.vlad.gaz.Model.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity {
    TextView json;
    Button btngetjson;
    Integer count;
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

    public class Get extends AsyncTask<Void,Model,Model>
    {
        Model model = new Model();
        Parser parser = new Parser();

        @Override
        protected Model doInBackground(Void... params) {
            httpUrlConnection = new HttpUrlConnection();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject = httpUrlConnection.HttpRequest("Rostov-na-Donu");
                count= Integer.valueOf(jsonObject.getString("cnt"));
               Log.e(TAG,"JSON: "+count);

                    model = parser.getData(jsonObject);

                    Log.e(TAG, "MODEL: " + model.temperature.getCurrent());


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return model;
        }
        @Override
        protected void onPostExecute(Model post) {
            super.onPostExecute(post);
            //json.setText(model.weather.getDescription());

        }
    }

}
