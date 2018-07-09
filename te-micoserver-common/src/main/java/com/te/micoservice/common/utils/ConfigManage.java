package com.te.micoservice.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hcw07605 on 2017/3/13.
 */
public class ConfigManage {

    private static String ConfigPath="/properties/resource.properties";
    /**
     *  通过配置name获取resource.properties配置文件值
     *@param name
     *@return String
     *@author hcw
     */
    public static String getValue(String name){
        String result = new String();
        Properties  p = new Properties();
        try {
            InputStream in = ConfigManage.class.getResourceAsStream(ConfigPath);
            p.load(in);
            in.close();
            if(p.containsKey(name)){
                result = p.getProperty(name);
            }
        }catch (IOException ex) {
            result = ex.getMessage();
        }
        return result;
    }
}
