package com.company.Spring_Annotation_Board.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.Spring_Annotation_Board.common.EncryptUtil;
import com.company.Spring_Annotation_Board.common.JDBCUtil;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET 
			= "select * from users where id = ? and password = ? and pwencrypt = ?";
	
	private final String USERS_INSERT = "insert into users values(?,?,?,?,?)";
	
	String pwEncrypt = "";
	String dbpwEncrypt = "";
	//로그인 사용자 조회 매소드 구현
	public UserDO getUser(UserDO userObj) {
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리");
			
			pwEncrypt = EncryptUtil.encryptSHA256(userObj.getPassword());
			System.out.println("pwEncryt:" + pwEncrypt);
			
			//JDBCUtil 클래스 커넥션 생성 메서드 호출 /  반환타입 Connection
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userObj.getId());
			pstmt.setString(2, userObj.getPassword());
			pstmt.setString(3, pwEncrypt);
			
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 성공! =>"+USER_GET);
			if(rs.next()) {	
				//userDO 객체 생성후 setter 메서드를 통해 저장
				user = new UserDO();		
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;	//UserDO 객체 반환
	}  // end getUser()
	
	
	//회원가입시 필요한 insertUser()매소드 구현
	public void insertUser(UserDO userDO) {
		System.out.println("===> JDBC로 insertUser()기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USERS_INSERT);
			pstmt.setString(1, userDO.getId());
			pstmt.setString(2,userDO.getPassword());
			//입력 받은 패스워드를 암호화시켜 세번째 물음표 값으로 지정한다.
			String plainText = userDO.getPassword();
			pwEncrypt = EncryptUtil.encryptSHA256(plainText);
			pstmt.setString(3,  pwEncrypt);
			pstmt.setString(4, userDO.getName());
			pstmt.setString(5, userDO.getRole());
			
			pstmt.executeUpdate();
			
		} catch (Exception e){
			System.out.println("insertUser()" + e);
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		
	}
	
}
