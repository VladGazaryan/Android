package com.example.vlad.gaz.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class Parser {
    public  final static String TAG = "Parser: ";
    Model model;
    JSONObject city_obj,main_obj,weather_obj,item,clouds_obj,wind_obj;
    JSONArray list_arr,weather_arr;
    String date_txt;
    public Model getData(JSONObject jsonObject) throws JSONException {
        model = new Model();

        city_obj = jsonObject.getJSONObject("city");
        model.city.setName(getString("name", city_obj));
        model.city.setCountry(getString("country", city_obj));

        list_arr =jsonObject.getJSONArray("list");


            item        = list_arr.getJSONObject(1);
            main_obj    = item.getJSONObject("main");
            weather_arr = item.getJSONArray("weather");
            weather_obj = weather_arr.getJSONObject(0);
            clouds_obj  = item.getJSONObject("clouds");
            wind_obj    = item.getJSONObject("wind");
            date_txt    = item.getString("dt_txt");


            model.temperature.setCurrent(getFloat("temp",main_obj));
            model.temperature.setMin(getFloat("temp_min",main_obj));
            model.temperature.setMax(getFloat("temp_max",main_obj));
            model.ph.setHumidity(getFloat("humidity",main_obj));
            model.ph.setPressure(getFloat("pressure",main_obj));
            model.weather.setMain(getString("main",weather_obj));
            model.weather.setDescription(getString("description",weather_obj));
            model.image.setIcon(getString("icon",weather_obj));
            model.clouds.setAll(getInt("all",clouds_obj));
            model.wind.setSpeed(getFloat("speed",wind_obj));
            model.wind.setDeg(getFloat("deg",wind_obj));
            model.date.setDt_txt(date_txt);


        Log.e(TAG,"PARSER: "+date_txt);
        return model;

    }


    private static String getString(String val, JSONObject jObj) throws JSONException {
        return jObj.getString(val);
    }
    private static float  getFloat(String val, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(val);
    }
    private static int  getInt(String val, JSONObject jObj) throws JSONException {
        return jObj.getInt(val);
    }
}
