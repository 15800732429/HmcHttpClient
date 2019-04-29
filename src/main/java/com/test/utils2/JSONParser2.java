package com.test.utils2;

import com.alibaba.fastjson.JSONObject;

public class JSONParser2 {
	JSONObject internalJSON;
	//JSONObject jsonObject = null;  
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
		
//	public String getProvince(JSONObject jo) {
//		String province = "";
//		try {
//			//先获取反馈中的result这个一个内部JSON对象
//			JSONObject internalJSON = jo.getJSONObject("result");
//			//再根据键名查找键值
//			province = internalJSON.getString("province") ;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return province;
//		
//	}
	
}

