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

public class TestDrive{
    RestfulClientpo2 Testdriverclient;
    JSONObject TestdriverresponseBody;
    JSONParser2 TestdriverjParser;
    int TestdriverresponseCode;
    String Testdriverstatus;
    String postBody;
    String Testdriverflag;
    //static String phoneRan = PhoneBuilder.build();
    
  @Test
  public void testPostRequest() {
       //断言添加试驾接口反馈中flag状态码是否正确
      Assert.assertEquals(Testdriverflag, "1");
       //断言添加试驾接口反馈的状态码是否正确
      Assert.assertEquals(TestdriverresponseCode, 200);
  }
  @BeforeClass
  public void beforeClass() throws ClientProtocolException, IOException, ParseException {
	  
	  
	  
	  Testdriverclient = new RestfulClientpo2();

   
	  Date date = new Date();
	  System.out.println("试驾时间"+Long.toString(date.getTime()));
	  String tsj2 = Long.toString(date.getTime());
      //添加试驾接口地址    
      String Subscribeurl = "http://api-test.haomaiche.com/user/test-drive";
      
      //添加试驾接口request body
      String Subscribebody = "{\"data\":{\"testDriveType\":\"cb0b8dd72ac74bae8d83813bf3c80c0a\",\"testDriveFsName\":\"一汽大众江杨南路店\",\"testDriveFsId\":\"1505201\",\"cityCode\":310000},\"time\":"+tsj2+",\"source\":102}";
      
      //用哈希图准备请求头部信息
      //HashMap<String, String> hashHead = new HashMap<String, String>();
      //hashHead.put("Content-Type", "application/json;charset=UTF-8");
      //hashHead.put("referer", "http://mb-test.haomaiche.com/sh/subscribeView");
     
      String accessToken = RegisterPost.registeraccessToken;
      System.out.println("String accessToken是"+accessToken);
	//传参发送验证码接口post请求并接收反馈
      Testdriverclient.sendPost(Subscribeurl, Subscribebody,accessToken);
      
      TestdriverresponseBody = Testdriverclient.getBodyInJSON();
      TestdriverresponseCode = Testdriverclient.getCodeInNumber();
      System.out.println("订阅接口TestdriverresponseCode等于"+TestdriverresponseCode);
      
      System.out.println("订阅接口TestdriverresponseBody等于："+TestdriverresponseBody);
      TestdriverjParser = new JSONParser2();
      Testdriverstatus = TestdriverjParser.getStatus(TestdriverresponseBody);
      System.out.println("订阅接口subscribestatus的值为"+Testdriverstatus);
      
      Testdriverflag = TestdriverjParser.getsubscribeflag(TestdriverresponseBody);
      System.out.println("订阅接口subscribeflag的值为"+Testdriverflag);
      
  }
  
	
  

}
