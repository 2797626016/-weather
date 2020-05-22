package cn.chen.spring_weather.service;

import cn.chen.spring_weather.bean.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestUrlService {


    /**
     * 根据id 查询json数据
     * @param cityId
     * @return
     */
    WeatherResponse getWeatherResponseByCityId(String cityId) throws JsonProcessingException;

    /**
     * 定时存入数据库
     */
    void setWeatherInfoToredis(String  cityId);

}
