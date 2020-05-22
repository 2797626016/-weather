package cn.chen.spring_weather.config;

import cn.chen.spring_weather.service.SyncDoJobService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean//任务派遣
    public JobDetail quartJob(){

        return JobBuilder.newJob(SyncDoJobService.class).withIdentity("SyncDoJobService").storeDurably().build();

    }

    @Bean
    public Trigger quartzTrigger(){
        //每隔多少秒调用一次
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(100000)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(quartJob()).withIdentity("weath").withSchedule(simpleScheduleBuilder).build();

    }

}
