package com.njxz.exam.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class StringUtil {
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	//随机生成6位数主键
	public static Long seqGenerate(){
		//获取当前时间毫秒数
		long mTime = System.currentTimeMillis();
		//随机生成6位随机数
		long radomNum = (int)((Math.random()*9+1)*100000);
		//System.out.println(mTime + "" + radomNum);
		long seq = mTime*1000000+radomNum;
		return seq;
	}
	
	//MD5加密
	public static String EncoderByMd5(String password) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	/*public static void main(String[] args) {
		System.out.println(StringUtil.EncoderByMd5("123456"));
	}*/
	
}
