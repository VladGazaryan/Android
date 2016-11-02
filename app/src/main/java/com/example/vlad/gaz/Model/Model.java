package com.example.vlad.gaz.Model;


public class Model {
    public City         city         = new City();
    public Temperature  temperature  = new Temperature();
    public PH           ph           = new PH();
    public Weather      weather      = new Weather();
    public Image        image        = new Image();
    public Clouds       clouds       = new Clouds();
    public Wind         wind         = new Wind();
    public Date         date         = new Date();
    public Model() {}

    private class City
    {
        String name,country;

        public String getCountry() {return country;}

        public void setCountry(String country) {this.country = country;}

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}
    }

    private class Temperature
    {
        float min,current,max;

        public float getCurrent() {return current;}

        public void setCurrent(float current) {this.current = current;}

        public float getMax() {return max;}

        public void setMax(float max) {this.max = max;}

        public float getMin() {return min;}

        public void setMin(float min) {this.min = min;}
    }
    private class PH
    {
        float pressure,humidity;

        public float getHumidity() {return humidity;}

        public void setHumidity(float humidity) {this.humidity = humidity;}

        public float getPressure() {return pressure;}

        public void setPressure(float pressure) {this.pressure = pressure;}
    }
    private class Weather
    {
        String main,description;

        public String getDescription() {return description;}

        public void setDescription(String description) {this.description = description;}

        public String getMain() {return main;}

        public void setMain(String main) {this.main = main;}
    }
    private class Image
    {
        String icon;

        public String getIcon() {return icon;}

        public void setIcon(String icon) {this.icon = icon;}
    }
    private class Clouds
    {
        int all;

        public int getAll() {return all;}

        public void setAll(int all) {this.all = all;}
    }
    private class Wind
    {
        float speed,deg;

        public float getDeg() {return deg;}

        public void setDeg(float deg) {this.deg = deg;}

        public float getSpeed() {return speed;}

        public void setSpeed(float speed) {this.speed = speed;}
    }
    private class Date
    {
        String dt_txt;

        public String getDt_txt() {return dt_txt;}

        public void setDt_txt(String dt_txt) {this.dt_txt = dt_txt;}
    }
}
