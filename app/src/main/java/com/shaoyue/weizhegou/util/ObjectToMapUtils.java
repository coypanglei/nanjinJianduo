package com.shaoyue.weizhegou.util;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectToMapUtils {


    public static Map<String, String> str2Map(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields(); // 获取对象对应类中的所有属性域
        for (int i = 0; i < fields.length; i++) {
            String varName = fields[i].getName();

            ///将key置为大写，默认为对象的属性
            boolean accessFlag = fields[i].isAccessible(); // 获取原来的访问控制权限
            fields[i].setAccessible(true);// 修改访问控制权限
            try {
                if (fields[i].get(obj) instanceof String || fields[i].get(obj) instanceof Double) {
                    String object = String.valueOf(fields[i].get(obj)); // 获取在对象中属性fields[i]对应的对象中的变量
                    if (object != null) {

                        map.put(varName, object);
                    } else {
                        map.put(varName, null);
                    }
                    fields[i].setAccessible(accessFlag);// 恢复访问控制权限
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, String> JsonToMap(JSONObject json) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Iterator it = json.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                //得到value的值
                String value = json.get(key).toString();
                //System.out.println(value);
                if (ObjectUtils.isNotEmpty(value) && !"null".equals(value)) {
                    map.put(key, value);
                }

            }
        } catch (JSONException e) {
            LogUtils.e(e);
        }
        return map;
    }

}
