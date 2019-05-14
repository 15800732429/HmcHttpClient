package com.test.utils2;

import com.alibaba.fastjson.JSONObject;

public class JSONParser2 {
	JSONObject internalJSON;  
	public String getStatus(JSONObject jo){
		 String status = "";
		 try{
			  status = jo.get("status").toString();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //return status;
		return status;
		
	}
		
	public String getaccessToken(JSONObject jo) {
		String accessToken = "";
		try {
			JSONObject internalJSON = jo.getJSONObject("data");
			JSONObject authJSON = internalJSON.getJSONObject("auth");
			accessToken = authJSON.getString("accessToken");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return accessToken;
		
	}
	
	public String getsubscribeflag(JSONObject jo) {
		String flag = "";
		try {
			JSONObject internalJSON = jo.getJSONObject("data");
			flag = internalJSON.getString("flag");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	
}

