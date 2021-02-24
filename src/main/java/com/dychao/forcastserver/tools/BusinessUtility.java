package com.dychao.forcastserver.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dychao.forcastserver.model.ReturnEnity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by D.Y_chao on 2018/8/23.
 */
@Service
public class BusinessUtility {

    /**
     * 转换字段
     */
    public static List<JSONObject> transformField(List<JSONObject> list){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<JSONObject> result = null;
        if(null != list){
            result = new ArrayList<JSONObject>();
            for(Map<String, Object> tempMap : list){
                JSONObject newMap = new JSONObject();
                //去除null、undefined值
                for(String key: tempMap.keySet()){
                    Object value = tempMap.get(key);
                    String newKey = key.toLowerCase();
                    while(newKey.indexOf("_")>-1){
                        newKey = newKey.replaceAll(newKey.substring(newKey.indexOf("_"),newKey.indexOf("_")+2)
                                ,newKey.substring(newKey.indexOf("_")+1,newKey.indexOf("_")+2).toUpperCase());
                    }

                    tempMap.put(key,value);
                    if(tempMap.get(key) == null || tempMap.get(key).equals("null") || tempMap.get(key).equals("undefined")){
                        newMap.put(newKey, "");
                    }else{
                        if(tempMap.get(key) instanceof Timestamp){
                            newMap.put(newKey, sdf.format(tempMap.get(key)));
                        }else {
                            newMap.put(newKey, tempMap.get(key));
                        }
                    }
                }
                result.add(newMap);
            }
        }
        return result;
    }/**
     * 转换字段
     */
    public static JSONArray transformField(JSONArray list){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray result = null;
        if(null != list && !list.isEmpty()){
            result = new JSONArray();
            for(int i=0;i<list.size();i++){
                JSONObject tempMap = list.getJSONObject(i);
                JSONObject newMap = new JSONObject();
                //去除null、undefined值
                for(String key: tempMap.keySet()){
                    Object value = tempMap.get(key);
                    String newKey = key.toLowerCase();
                    while(newKey.indexOf("_")>-1){
                        newKey = newKey.replaceAll(newKey.substring(newKey.indexOf("_"),newKey.indexOf("_")+2)
                                ,newKey.substring(newKey.indexOf("_")+1,newKey.indexOf("_")+2).toUpperCase());
                    }

                    tempMap.put(key,value);
                    if(tempMap.get(key) == null || tempMap.get(key).equals("null") || tempMap.get(key).equals("undefined")){
                        newMap.put(newKey, "");
                    }else{
                        if(tempMap.get(key) instanceof Timestamp){
                            newMap.put(newKey, sdf.format(tempMap.get(key)));
                        }else {
                            newMap.put(newKey, tempMap.get(key));
                        }
                    }
                }
                result.add(newMap);
            }
        }
        return result;
    }

    /**
     * 转换字段
     */
    public static List<Map> transformField2(List<Map> list){
        List<Map> result = null;
        if(null != list){
            result = new ArrayList<Map>();
            for(Map<String, Object> tempMap : list){
                Map<String, Object> newMap = new HashMap<String, Object>();
                //去除null、undefined值
                for(String key: tempMap.keySet()){
                    Object value = tempMap.get(key);
                    String newKey = key.toLowerCase();
                    while(newKey.indexOf("_")>-1){
                        newKey = newKey.replaceAll(newKey.substring(newKey.indexOf("_"),newKey.indexOf("_")+2)
                                ,newKey.substring(newKey.indexOf("_")+1,newKey.indexOf("_")+2).toUpperCase());
                    }

                    tempMap.put(key,value);
                    if(tempMap.get(key) == null || tempMap.get(key).equals("null") || tempMap.get(key).equals("undefined")){
                        newMap.put(newKey, "");
                    }else{
                        newMap.put(newKey, tempMap.get(key));
                    }
                }
                result.add(newMap);
            }
        }
        return result;
    }

    public static List<Map<String, Object>> transformFieldAndTime(List<Map<String, Object>> list){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for(Map<String, Object> tempMap : list){
            Map<String, Object> newMap = new HashMap<String, Object>();
            //去除null、undefined值
            for(String key: tempMap.keySet()){
                Object value = tempMap.get(key);
                String newKey = key.toLowerCase();
                while(newKey.indexOf("_")>-1){
                    newKey = newKey.replaceAll(newKey.substring(newKey.indexOf("_"),newKey.indexOf("_")+2)
                            ,newKey.substring(newKey.indexOf("_")+1,newKey.indexOf("_")+2).toUpperCase());
                }

                tempMap.put(key,value);
                if(tempMap.get(key) == null || tempMap.get(key).equals("null") || tempMap.get(key).equals("undefined")){
                    newMap.put(newKey, "");
                }else{
                    if(Timestamp.class.isInstance(tempMap.get(key))){
                        Timestamp ts = (Timestamp)tempMap.get(key);
                        tempMap.put(key, ts.toString().substring(0, ts.toString().indexOf(".")));
                    }
                    newMap.put(newKey, tempMap.get(key));
                }
            }
            result.add(newMap);
        }
        return result;
    }

    public static boolean checkParams(Map map, String[] params){
        for(String key : params){
            if(!map.containsKey(key) || map.get(key) == null || map.get(key).toString().equals("")){
                return false;
            }
        }
        return true;
    }

    /**
     * utc格式(2018-08-17T12:43:26.271Z) 转成2018-08-17 12:43:26
     * @param utcDate
     * @return
     */
    public static String utcToCommon(String utcDate){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            date = sdf.parse(utcDate);
        }catch (ParseException e){
            e.getStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf2.format(calendar.getTime());
    }


    /**
     * list转化成tree
     * @param list  sql查出的记录
     * @param id
     * @param pid
     * @param child
     * @return
     */
    public static List<Map<String, Object>> listToTree(List<Map<String, Object>> list,String id,String pid,String child){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> idMap = new HashMap<String, Object>();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<list.size();i++){
            Map<String, Object> json = list.get(i);
            idMap.put(json.get(id).toString(), json);
        }
        //遍历结果集
        for(int j=0;j<list.size();j++){
            //单条记录
            Map<String, Object> aVal = list.get(j);
            //在hash中取出key为单条记录中pid的值
            Map<String, Object> hashVP = (Map<String, Object>)idMap.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    List<Map<String, Object>> ch = (List<Map<String, Object>>)hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    List<Map<String, Object>> ch = new ArrayList<Map<String, Object>>();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                result.add(aVal);
            }
        }
        return result;
    }

    /**
     * 检查是否非空
     * @param map
     * @param key
     * @return
     */
    public static boolean checkIsNotNull(Map map, String key){
        boolean result = false;
        if(map.containsKey(key)
                && null != map.get(key)
                && !map.get(key).toString().equals("")
                && !map.get(key).toString().equals("undefined")
                && !map.get(key).toString().equals("null")){
            result = true;
        }
        return result;
    }

    /**
     * 检查是否非空
     * @param map
     * @return
     */
    public static boolean checkIsNotNull(Map map){
        if (map == null || map.isEmpty()) {
            return  false;
        }
        return true;
    }

    /**
     * 检查是否非空
     * @param map
     * @param key
     * @return
     */
    public static boolean checkIsNotNull2(Map map, String key){
        boolean result = false;
        if(map.containsKey(key) && null != map.get(key)){
            result = true;
        }
        return result;
    }

    /**
     * 检查是否非空
     * @param map
     * @param key
     * @return
     */
    public static boolean checkIsNotNull3(Map map, String key){
        return map.containsKey(key);
    }

    /**
     * 驼峰转下划线
     * "clientNo" 转为下划线命名：CLIENT_NO
     * @param para
     * @return
     */
    public static String HumpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    public static boolean checkParams(Map map, String[] params, ReturnEnity result){
        for(String key : params){
            if(!map.containsKey(key) || map.get(key) == null || map.get(key).toString().equals("")){
                result.setInfo(key + "为必填字段!");
                result.setMessage(key + "为必填字段!");
                result.setStatus(false);
                return false;
            }
        }
        return true;
    }

    public static String getRealPath(String oldRootPath, String rootPath, String url){
        String result = "";
        if(url.indexOf(rootPath) > -1){
            result = "hresource/" + url.replace(rootPath, "");
        }else{
            result = "resource/" + url.replace(oldRootPath, "");
        }
        return result;
    }
}
