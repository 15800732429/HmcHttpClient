package com.test.api2;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.test.client2.RestfulClientpo2;
import com.test.utils2.JSONParser2;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class SmsCodePost2{
    RestfulClientpo2 smsCodeclient;
    JSONObject smsCoderesponseBody;
    JSONParser2 smsCodejParser;
    int smsCoderesponseCode;
    String smsCodestatus;
    String postBody;
    static String phoneRan = PhoneBuilder.build();
    
  @Test
  public void testPostRequest() {
       //断言发送验证码接口反馈中status信息是否正确
      Assert.assertEquals(smsCodestatus, "1");
       //断言发送验证码接口反馈的状态码是否正确
      Assert.assertEquals(smsCoderesponseCode, 200);
  }
  @BeforeClass
  public void beforeClass() throws ClientProtocolException, IOException, ParseException {
	  
	  
	  
	  smsCodeclient = new RestfulClientpo2();

//      HashMap map = new HashMap();
//      
//      map.put("userPhone","13605998814");
//      map.put("codeType", "1");	
//      map.put("cityCode", "310000");
//      System.out.println("JSON.toJSONString(map)的值为"+JSON.toJSONString(map));
//      
//      //用NameValuePair的list来添加请求主体参数
//     List<NameValuePair> params = new ArrayList<NameValuePair>();
//      params.add(new BasicNameValuePair("data",JSON.toJSONString(map)));
//      
//      Date date = new Date();
//     
//      
//     params.add(new BasicNameValuePair("time",Long.toString(date.getTime())));
//      params.add(new BasicNameValuePair("source","101")); 
//      	
//    
//      
//      HashMap params1 = new HashMap();
//     params1.put("data",map);
//      params1.put("source",101);
//      params1.put("time", "1555990238000");
//     
	  Date date = new Date();
	  System.out.println(Long.toString(date.getTime()));
	  String tsj = Long.toString(date.getTime());
      //发送验证码接口地址    
      String smsCodeurl = "http://api-test.haomaiche.com/user/member/sms-code";
      
      System.out.println("手机号码是"+phoneRan);
      //发送验证码接口request body
      String smsCodebody = "{\"data\":{\"userPhone\":\""+phoneRan+"\",\"codeType\":\"1\",\"cityCode\":310000},\"time\":"+tsj+",\"source\":102}";
    
      
      //用哈希图准备请求头部信息
      //HashMap<String, String> hashHead = new HashMap<String, String>();
      //hashHead.put("Content-Type", "application/json;charset=UTF-8");
      //hashHead.put("referer", "http://mb-test.haomaiche.com/sh/subscribeView");
      //hashHead.put("accessToken","OGM3ZGFiMWI2ZjMzNGJiY2JkZTY5Njc4NTM1NjM1ZWQjMTAyI0FUI3NXIW1AWk1p");
     
	//传参发送验证码接口post请求并接收反馈
      smsCodeclient.sendPost(smsCodeurl, smsCodebody);
      
      smsCoderesponseBody = smsCodeclient.getBodyInJSON();
      smsCoderesponseCode = smsCodeclient.getCodeInNumber();
      System.out.println("发送验证码接口smsCoderesponseCode等于"+smsCoderesponseCode);
      
      System.out.println("发送验证码接口smsCoderesponseBody等于："+smsCoderesponseBody);
      smsCodejParser = new JSONParser2();
      smsCodestatus = smsCodejParser.getStatus(smsCoderesponseBody);
      System.out.println("发送验证码接口smsCodestatus的值为"+smsCodestatus);
  }
  
	
  

}


