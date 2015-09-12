package com.example.luke.knowweather.util;

import android.text.TextUtils;

import com.example.luke.knowweather.model.City;
import com.example.luke.knowweather.model.County;
import com.example.luke.knowweather.model.KnowWeatherDB;
import com.example.luke.knowweather.model.Province;

/**
 * 解析服务器传回来的数据的工具类
 * Created by LUKE on 2015/9/12.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     * @param knowWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvincesResponse(KnowWeatherDB knowWeatherDB,
                                                               String response){
        if ( ! TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p: allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    knowWeatherDB.saveProvince(province);
                }
                return  true;
            }
        }
        return false;
    }

    /**
     *解析和处理服务器返回的市级数据
     * @param knowWeatherDB
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCitiesResponse(KnowWeatherDB knowWeatherDB,
                                               String response, int provinceId){
        if ( ! TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    knowWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param knowWeatherDB
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountriesResponse(KnowWeatherDB knowWeatherDB,
                                                  String response, int cityId){
        if ( ! TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    //将解析出来的数据存储到City表
                    knowWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
