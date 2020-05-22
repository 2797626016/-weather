package cn.chen.spring_weather.controller;


import cn.chen.spring_weather.bean.WeatherResponse;
import cn.chen.spring_weather.cityUtil.CityObj;
import cn.chen.spring_weather.cityUtil.JsonToCityUtil;
import cn.chen.spring_weather.service.RequestUrlService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/requestUrl")
public class RequestUrlController {


    @Autowired
    private RequestUrlService requestUrlService;
    /**
     *
     * @param cityId 第三方请求 根据cityId
     * @return 返回json数据 使用WeatherResponse实体类接收
     */
    @RequestMapping("/cityId/{cityId}")
    @ResponseBody
    public WeatherResponse getWeatherResponseCityId(@PathVariable("cityId") String  cityId ) {

        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = requestUrlService.getWeatherResponseByCityId(cityId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weatherResponse ;
    }

    @RequestMapping("/cityIdPage/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String  cityId , Model model) throws Exception {
        List<CityObj> cityObjList = JsonToCityUtil.getInstance().readJson();
        model.addAttribute("title","下面是天气预报");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",cityObjList);
        model.addAttribute("report",requestUrlService.getWeatherResponseByCityId(cityId));


        return new ModelAndView("report","reportModel",model);
    }

}
