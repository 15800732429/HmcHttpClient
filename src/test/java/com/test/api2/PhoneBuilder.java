package com.test.api2;

public class PhoneBuilder {
	
	static public String build() {
		java.util.Random random = new java.util.Random();
		int randomNum = random.nextInt(9000) + 1000;
		System.out.println("随机数为："+randomNum);
		String qiqi = "1772000";
		System.out.println("手机号码为："+qiqi+randomNum);
		
		return qiqi + randomNum;
	}
	
}
