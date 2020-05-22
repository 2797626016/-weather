package cn.chen.spring_weather.bean;

public class CityInfo {
    private String city;
    private int citykey;
    private String parent;
    private String updateTime;
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public int getCitykey() {
        return citykey;
    }

    public void setCitykey(int citykey) {
        this.citykey = citykey;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    public String getParent() {
        return parent;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateTime() {
        return updateTime;
    }
}
