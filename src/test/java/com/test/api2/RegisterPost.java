package com.test.api2;

import org.testng.annotations.Test;


import com.alibaba.fastjson.JSONObject;
import com.test.client2.RestfulClientpo2;
import com.test.utils2.JSONParser2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class RegisterPost{
	 RestfulClientpo2 registerclient;
	 JSONObject registerresponseBody;
	 JSONParser2 registerjParser;
	 int registerresponseCode;
	 String registerstatus;
	 String postBody;
	 static String registeraccessToken;
	 String phoneRan = SmsCodePost2.phoneRan;
     
	    
	  @Test
	  public void testPostRequest() {
	       //断言注册接口反馈中status信息是否正确
	      Assert.assertEquals(registerstatus, "1");
	       //断言注册接口反馈的状态码是否正确
	      Assert.assertEquals(registerresponseCode, 200);
	  }
	  @BeforeClass
	  public void beforeClass() throws ClientProtocolException, IOException, ParseException {
		  
		  
		  try {
	            Class.forName("com.mysql.jdbc.Driver");
	 
	            // 建立与数据库的Connection连接
	            // 这里需要提供：
	            // 数据库所处于的ip:****
	            // 数据库的端口号： ****** （mysql专用端口号）
	            // 数据库名称 ******
	            // 编码方式 UTF-8
	   
	 
	            
	            Connection c = DriverManager
	                    .getConnection(
	                            "jdbc:mysql://****:****/hmc_test?characterEncoding=UTF-8",
	                            "****", "****");
	         // 注意：使用的是 java.sql.Statement
	            // 不要不小心使用到： com.mysql.jdbc.Statement;
	            Statement s = c.createStatement();
	            System.out.println("连接成功，获取连接对象： " + c);
	            System.out.println("获取 Statement对象： " + s);
	          System.out.println("注册phoneRan"+phoneRan);   
	            
	            String sql = "SELECT * FROM sms_mt WHERE mt_mobile= '"+phoneRan+"' ORDER BY mt_sendTime DESC";
	            
	           
	         // 执行查询语句，并把结果集返回给ResultSet
	            String subsms_code = "";
	            ResultSet rs = s.executeQuery(sql);
	            
	            while (rs.next()) {
	            	int mt_id = rs.getInt("mt_id");
	            	String mt_mobile = rs.getString("mt_mobile");
	            	String mt_content = rs.getString("mt_content");
	            	System.out.print("mt_id的值为"+mt_id);
	            	System.out.println("mt_mobile值为"+mt_mobile);
	            	System.out.println("mt_content值为"+mt_content);
	                subsms_code = mt_content.substring(5, 9);
	            	System.out.println("验证码的值等于"+subsms_code);
	            }
	            s.execute(sql);
	            this.request(subsms_code);
	           
	  	    
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	     
	  }
	  
	  public void request(String subsms_code) {
		  registerclient = new RestfulClientpo2();
          //注册接口地址    
  	      String registerurl1 = "http://api-test.haomaiche.com/user/member/register";
  	      //注册接口request body
  	      Date date = new Date();
  		  System.out.println(Long.toString(date.getTime()));
  		  String tsj = Long.toString(date.getTime());
  		  //String phoneRan = PhoneBuilder.build();
  		  System.out.println("143行的手机号码为"+phoneRan);
  	      String registerbody = "{\"data\":{\"userPhone\":\""+phoneRan+"\",\"smsCode\":\""+subsms_code+"\",\"cityCode\":310000,\"attachMarketOrigin\":\"null\",\"attachMarketKeyword\":\"null\",\"userName\":\"接口测试\",\"attachUserOrigin\":3,\"attachUserRemark\":\"\"},\"time\":"+tsj+",\"source\":102}";
  	      //String registerbody = "{\"data\":{\"userPhone\":\""+phoneRan+"\",\"smsCode\":\"8019\",\"cityCode\":310000,\"attachMarketOrigin\":\"null\",\"attachMarketKeyword\":\"null\",\"userName\":\"2936\",\"attachUserOrigin\":3,\"attachUserRemark\":\"\"},\"time\":"+tsj+",\"source\":102}";
  	    
  	      
  	  //传参发送验证码接口post请求并接收反馈
	     try {
	    	 
		      registerclient.sendPost(registerurl1, registerbody,null);

		      registerresponseBody = registerclient.getBodyInJSON();
		      registerresponseCode = registerclient.getCodeInNumber();
		      System.out.println("注册接口registerresponseCode等于"+registerresponseCode);
		      System.out.println("注册接口registerresponseBody等于："+registerresponseBody);
		     
		      registerjParser = new JSONParser2();
		      registerstatus = registerjParser.getStatus(registerresponseBody);
		      System.out.println("注册接口registerstatus的值为"+registerstatus);
		      
		      registeraccessToken = registerjParser.getaccessToken(registerresponseBody);
		      System.out.println("注册之后用户的accessToken值为"+registeraccessToken);
		      
		      
		      
	     }catch(IOException e) {
	    	 
	     }catch(ParseException e) {
	    	 
	    	 
	     }
	  }
	  

}
