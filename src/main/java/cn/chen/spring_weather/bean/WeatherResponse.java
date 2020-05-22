package cn.chen.spring_weather.bean;

import java.util.Date;

public class WeatherResponse {

    private String message;
    private int status;
    private String date;
    private String time;
    private CityInfo cityInfo;
    private Data data;
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }



    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }
    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }
}
