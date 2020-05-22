package cn.chen.spring_weather.service.imp;

import cn.chen.spring_weather.bean.WeatherResponse;
import cn.chen.spring_weather.service.RequestUrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestUrlServiceImp implements RequestUrlService {

    //json数据地址
    private static final String WEATHER_RUL="http://t.weather.sojson.com/api/weather/city/";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private StringRedisTemplate redisTemplate;

    private WeatherResponse weatherResponse ;

    private String body;

    private  ObjectMapper objectMapper;


    @Override
    public WeatherResponse getWeatherResponseByCityId(String cityId)  {

        //获取redis的内容
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(cityId)){ //判断这个键值是否存在
            //如果存在则在redis中获取
            System.out.println("判断是否有数据"+cityId);
            body =   ops.get(cityId);
            System.out.println(body.toString());
        }else {
            //如果不存在则直接调用第三方接口拿数据
            //再把数据存在reids中
            setRedisData(cityId);
        }


        objectMapper = new ObjectMapper(); //实体类映射提取
        weatherResponse = null ;
        try {
            weatherResponse =  objectMapper.readValue(body,WeatherResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return weatherResponse;
    }




    //存到redis中
    private void setRedisData(String cityId) {
        //获取json数据地址
        //cityid
        //请求数据 使用hhttpclient spingboot RestTemplate 类 来请求
        ResponseEntity<String> forEntity = restTemplate.getForEntity(WEATHER_RUL + cityId, String.class);

        //获取请求body
         body = forEntity.getBody();
         //将获取的body数据存到reids中
        redisTemplate.opsForValue().set(cityId,body);
        //把获取的内容body转换成WeatherResponse类
         objectMapper = new ObjectMapper(); //实体类映射提取
         weatherResponse = null ;
        try {
            weatherResponse =  objectMapper.readValue(body,WeatherResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }





    }


    /**
     * 定时存入数据库中
     * @param cityId
     */


    @Override
    public void setWeatherInfoToredis(String cityId) {
        setRedisData(cityId);
    }
}
