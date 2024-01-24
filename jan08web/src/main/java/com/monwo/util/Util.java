package com.monwo.util;

import javax.servlet.http.HttpServletRequest;

public class Util {
	//String 값이 들어오면 int타입인지 확인해보는 메소드
	/* 
	 * 예시 127 => true
	 * 		1A2A5 => false
	 * 
	 * static 객체 생성 안하고 쓴다
	 * */
	
	public static int str3Int(String str) {
		//A59 -> 59로 바꿔서 나오게
		// for문 + 아무 숫자도 없고 문자 하나만 들어올때의 경우도 까지 걸러내는 메소드
		
		StringBuilder stringBuilder = new StringBuilder(); //StringBuilder를 사용하려면 객체 생성 후 사용이 가능하다.
		
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				stringBuilder.append(str.charAt(i));
			}
		}
		int number = 0;
		if (stringBuilder.length() > 0) {
			number = Integer.parseInt(stringBuilder.toString());
		}
		//System.out.println("변환된 숫자: "+number);
		return number;
	}
	
	public static int str2Int(String str) {
		//A59 -> 59로 바꿔서 나오게
		// 정규표현식으로 숫자를 제외한 나머지 문자들을 없애버리는 코드
		
		String numberOnly = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(numberOnly);
	}

	
	
	public static boolean intCheck(String str) { // str로 들어오는 값이 숫자인지 숫자가 아닌지 확인 하는 코드(try catch를 활용하여)
		try {
			Integer.parseInt(str); // str로 들어오는 걸 숫자로 변환 한다는 뜻
			return true; // 숫자로 변환이 되면 참
		} catch (Exception e) {
			return false;// 숫자로 변환 할 수 없다면 거짓
		}
	}
	
	
	
	public static boolean intCheck2(String str) {//for문과 if문을 활용하여 str에 들어오는 값 확인 하는 메소드 (intCheck 메소드와 같은 기능이다.)
		boolean result = true;
		
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				result=false;
			}else {
				result = true;
			}
		}
		
		return result;
	}
	
	//ip가져오기
	public static String getIP(HttpServletRequest request) {
	      String ip = request.getHeader("X-FORWARDED-FOR");
	      if(ip == null) {
	         ip = request.getHeader("Proxy-Client-IP");
	      }
	      if(ip == null) {
	         ip = request.getHeader("WL-Proxy-Client-IP");   
	      }
	      if(ip == null) {
	         ip = request.getHeader("HTTP_CLIENT_IP");
	      }
	      if(ip == null) {
	         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	      }
	      if(ip == null) {
	         ip = request.getRemoteAddr();
	      }
	      return ip;
	   }
	
	// HTML태그를 특수기호로 변경하기	< = &lt 	> = &gt 로 변경하기
	public static String removeTag(String str) {
		str = str.replaceAll("<", "&lt");
		str = str.replaceAll(">", "&gt");
		
		return str;
	}
	// 댓글에서 엔터를 누르면 줄바꿈으로 처리해주는 메소드
	public static String addBR(String str) {
		return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	   }
	// IP 중간을 ○으로 가리기 -> 172.30.1.200 => 172.○.1.200
	public static String ipMarking(String ip) {
		if (ip.indexOf('.') != -1) {
			StringBuffer sb = new StringBuffer();
			sb.append(ip.substring(0, ip.indexOf('.')+1));
			sb.append("○");
			sb.append(ip.substring(ip.indexOf('.', ip.indexOf('.')+1)));
			return sb.toString();
		} else {
			return ip;
		}
	}
  }

