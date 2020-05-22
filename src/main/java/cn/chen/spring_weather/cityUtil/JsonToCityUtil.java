package cn.chen.spring_weather.cityUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用 json  变为 实体类
 *
 *
 */
public class JsonToCityUtil {

    private JsonToCityUtil(){};
    private static JsonToCityUtil jsonToCityUtil;
    public static JsonToCityUtil getInstance() {
        if (jsonToCityUtil == null) {
            jsonToCityUtil = new JsonToCityUtil();
        }
        return jsonToCityUtil;
    }


    public List<CityObj> readJson() throws Exception {
        List<CityObj> list= new ArrayList<>();
        // 读取 json 文件
        File file = ResourceUtils.getFile("classpath:static/cityJson.json");
        //  System.out.println(file);
        String jsonData = jsonRead(file);
        //System.out.println(jsonData);
        JSONArray array = JSONArray.parseArray(jsonData);
        // System.out.println(array);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject2 = array.getJSONObject(i);
            //  System.out.println(jsonObject2);
            String city_name = jsonObject2.getString("city_name");
            String city_code = jsonObject2.getString("city_code");
            String pid = jsonObject2.getString("pid");
            String _id = jsonObject2.getString("_id");
            String id = jsonObject2.getString("id");


            // 把 读取到 jsonObject2  弄成一个个的对象
            CityObj cityObj = new CityObj();
            cityObj.setCity_name(city_name);
            cityObj.setCity_code(city_code);
            cityObj.setPid(Integer.parseInt(pid));
            cityObj.setId(Integer.parseInt(id));
            cityObj.set_id(Integer.parseInt(_id));
            System.out.println(cityObj);
            list.add(cityObj);  // 把对象 放到了集合中..

        }

        return list;

    }


/*  public static void main(String[] args) throws Exception {

        readJson();
    }*/


    private    String jsonRead(File file) {

        FileInputStream is = null;
        StringBuilder stringBuilder = null;
        try {

            /**
             * 文件有内容才去读文件
             */
            is = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(is,"utf-8");
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
            }
            reader.close();
            is.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);

    }

}

