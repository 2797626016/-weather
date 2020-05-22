package cn.chen.spring_weather.service;

import cn.chen.spring_weather.cityUtil.CityObj;
import cn.chen.spring_weather.cityUtil.JsonToCityUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 具体的定时任务 请求第三方
 */
public class SyncDoJobService extends QuartzJobBean {

    @Autowired
    private RequestUrlService requestUrlService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //遍历 出来5个城市 得到单个城市对象 然后调用urlService接口

        //请求第三方
        List<CityObj> cityObjs = null;
        try {
            cityObjs =  JsonToCityUtil.getInstance().readJson();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CityObj cityObj:cityObjs) {

            try {
                Thread.sleep(10000000);

                requestUrlService.setWeatherInfoToredis(cityObj.getCity_code());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
