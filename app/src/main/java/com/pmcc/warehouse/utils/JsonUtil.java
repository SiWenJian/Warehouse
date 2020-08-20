package com.pmcc.warehouse.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * json javaBean map object 互转
 * @author DarkBF
 * 引用包 
 * fastjson-1.1.38.jar
 * fastjson-1.1.38-sources.jar
 * 
 */
public class JsonUtil {
	/**
	 *  把JSON文本parse为JSONObject或者JSONArray 
	 */
	public static Object parse(String text) {
		return JSON.parse(text);
	} 
	/**
	 * 把JSON文本parse成JSONObject
	 */
	public static JSONObject parseObject(String text){
		return JSON.parseObject(text);
	}
	/**
	 *  javaBean convert to json string
	 */
	public static final String obj2json(Object object){
		return JSON.toJSONString(object);
	}

	/**
	 * javaBean convert to JSONObject、JSONArray
	 */
	public static Object bean2obj(Object javaObject){
		return JSON.toJSON(javaObject);
	}
	
	/** 
     * json string convert to javaBean、map 
     */  
    public static <T> T json2obj(String jsonStr, Class<T> clazz){
        return JSON.parseObject(jsonStr,clazz);
    }  
      
    /** 
     * json array string convert to list with javaBean 
     */  
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz){
        return JSON.parseArray(jsonArrayStr, clazz);
    }  
      
    /** 
     * json string convert to map 
     */  
    @SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr){
        return json2obj(jsonStr, Map.class);
    }  
      
    /** 
     * json string convert to map with javaBean 
     */  
    public static <T> Map<String,T> json2map(String jsonStr, Class<T> clazz){
        Map<String,T> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, T>>() {});
        for (Entry<String, T> entry : map.entrySet()) {
            JSONObject obj = (JSONObject) entry.getValue();
            map.put(entry.getKey(), JSONObject.toJavaObject(obj, clazz));
        }  
        return map;  
    }

	//判断字符串是否为json格式
	public static Boolean isListJson(String value){
		try {
			new JSONObject().parseArray(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static Boolean isStringJson(String value){
		try {
			new JSONObject().parseObject(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
