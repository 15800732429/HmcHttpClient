package com.test.client2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;


public class RestfulClientpo2 {
	CloseableHttpClient httpclient;
    HttpPost httpPost;
    CloseableHttpResponse httpResponse;
    int responseCode;
    JSONObject responseBody;
    HashMap<String, String> responseHeads;
    
//  //通过httpclient获取post请求的反馈
    public void sendPost(String url, String body,String accessToken) throws ClientProtocolException, IOException{
    	//String accessToken1 = "";
    	httpclient = HttpClients.createDefault();
    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        //创建post请求对象
        httpPost = new HttpPost(url);

	    //httpPost.setConfig(requestConfig);

        //设置请求主体格式
        //httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        //httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        //设置发送验证码接口主体格式
        httpPost.setEntity(new StringEntity(body));
        
        
        //测试订阅降价通知，添加头部的accesstoken，请求成功
        httpPost.addHeader("accessToken",accessToken);
        //添加发送验证码接口referer
        //httpPost.addHeader("referer", "http://mb-test.haomaiche.com/sh/login?fromPage=%2Fsh%2FuserCenter%2FparityOrderList");
        //httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
        //添加订阅接口referer
        httpPost.addHeader("referer","http://mb-test.haomaiche.com/sh/subscribeView");
        ////设置下头部信息
//        Set<String> set = headers.keySet();
//        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
//            String key = iterator.next();
//            String value = headers.get(key);
//            httpPost.addHeader(key, value);
//        }
        httpResponse = httpclient.execute(httpPost);
        System.out.println("httpResponse"+httpResponse);
        HttpEntity httpEntity = httpResponse.getEntity();//获取响应正文对象
        System.out.println("httpEntity是"+httpEntity);
        //return EntityUtils.toString(httpEntity,"utf-8");
    }
    
    
    
    
  //以JSON格式获取到反馈的主体
    public JSONObject getBodyInJSON() throws ParseException, IOException{
        HttpEntity entity;
        String entityToString;
        entity = httpResponse.getEntity();
        entityToString = EntityUtils.toString(entity);
        System.out.println("entityToString的值为"+entityToString);
        responseBody = JSONObject.parseObject(entityToString);
        
        System.out.println("This is your response body" + responseBody);
        
        return responseBody;
    }
    
  //以哈希图的方式获取到反馈头部
    public HashMap<String, String> getHeaderInHash(){
        Header[] headers;
        headers = httpResponse.getAllHeaders();
        
        responseHeads = new HashMap<String, String>();
        
        for(Header header:headers){
            responseHeads.put(header.getName(), header.getValue());
        }
        
        System.out.println("This is your response header" + responseHeads);
        
        return    responseHeads;
    }
    
  //获取反馈状态码
    public int getCodeInNumber(){
    	responseCode = httpResponse.getStatusLine().getStatusCode();
        
        System.out.println("This is your response code" + responseCode);
        
        return responseCode;
    }
    
}


