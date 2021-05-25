package com.company.Spring_Annotation_Board.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 * 
 * SHA256 암호화 알고리즘
 * (Secure Hash Algorithm) 약자 해쉬함수사용
 *  
 */
public class EncryptUtil {
	public static String encryptSHA256(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sha256 ="";
		
		try {
			//SHA256 MessageDigest 객체 인스턴스 얻기
			MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
			
			//해쉬값 업데이트
			mdSHA256.update(plainText.getBytes("UTF-8"));
			
			//해쉬 계산 반환값은 바이트 배열에 할당한다.
			byte[] sha256Hash = mdSHA256.digest();
			
			//[이유] STring클래스 객체는 변경되면 새로운 객체가 메모리에 계속해서 생되지만 StringBuffer는 오직 하나의 객체만 생성된다.
			StringBuffer hexSHA256hash = new StringBuffer();
			
			//바이트를 헥사값으로 변환
			for(byte b: sha256Hash) {
				String hexString = String.format("%02x", b);
				hexSHA256hash.append(hexString);
			}
			sha256 = hexSHA256hash.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha256;
	}
}
