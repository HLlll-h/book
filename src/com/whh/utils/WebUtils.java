package com.whh.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    //把所有的请求参数注入到user对象中
    public static <T> T copyParamToBean(HttpServletRequest req,T bean){
        try {
            BeanUtils.populate(bean,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    /**
     * 将String型转为int型
     * 这里把异常try-catch了，不然原代码要一直try-catch不好看
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }




}
