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

public class Subscribe{
    RestfulClientpo2 Subscribeclient;
    JSONObject subscriberesponseBody;
    JSONParser2 subscribejParser;
    int subscriberesponseCode;
    String subscribestatus;
    String postBody;
    String subscribeflag;
    //static String phoneRan = PhoneBuilder.build();
    
  @Test
  public void testPostRequest() {
       //断言订阅接口反馈中status信息是否正确
      Assert.assertEquals(subscribeflag, "1");
       //断言订阅接口反馈的状态码是否正确
      Assert.assertEquals(subscriberesponseCode, 200);
  }
  @BeforeClass
  public void beforeClass() throws ClientProtocolException, IOException, ParseException {
	  
	  
	  
	  Subscribeclient = new RestfulClientpo2();

   
	  Date date = new Date();
	  System.out.println("订阅时间"+Long.toString(date.getTime()));
	  String tsj1 = Long.toString(date.getTime());
      //订阅降价通知接口地址    
      String Subscribeurl = "http://api-test.haomaiche.com/user/member/subscribe";
      
      //订阅接口request body
      String Subscribebody = "{\"data\":{\"subscribeModelId\":\"2f5dce4f08d44fc88759d7383b6dad27\",\"subscribeModelPrice\":191000,\"subscribePrice\":52600,\"subscribeCategory\":1,\"subscribeExpireTime\":1565509392233,\"cityCode\":310000},\"time\":"+tsj1+",\"source\":102}";
      
      //用哈希图准备请求头部信息
      //HashMap<String, String> hashHead = new HashMap<String, String>();
      //hashHead.put("Content-Type", "application/json;charset=UTF-8");
      //hashHead.put("referer", "http://mb-test.haomaiche.com/sh/subscribeView");
     
      String accessToken = RegisterPost.registeraccessToken;
      System.out.println("String accessToken是"+accessToken);
	//传参发送验证码接口post请求并接收反馈
      Subscribeclient.sendPost(Subscribeurl, Subscribebody,accessToken);
      
      subscriberesponseBody = Subscribeclient.getBodyInJSON();
      subscriberesponseCode = Subscribeclient.getCodeInNumber();
      System.out.println("订阅接口smsCoderesponseCode等于"+subscriberesponseCode);
      
      System.out.println("订阅接口subscriberesponseBody等于："+subscriberesponseBody);
      subscribejParser = new JSONParser2();
      subscribestatus = subscribejParser.getStatus(subscriberesponseBody);
      System.out.println("订阅接口subscribestatus的值为"+subscribestatus);
      
      subscribeflag = subscribejParser.getsubscribeflag(subscriberesponseBody);
      System.out.println("订阅接口subscribeflag的值为"+subscribeflag);
      
  }
  
	
  

}

