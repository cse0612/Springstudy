package com.company.Model2_Board.user;

import java.sql.*;
import com.company.Model2_Board.common.JDBCUtil;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET 
			= "select id, password from users where id = ? and password = ?";
	
	//로그인 사용자 조회 매소드 구현
	public UserDO getUser(UserDO userObj) {
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리");
			
			//JDBCUtil 클래스 커넥션 생성 메서드 호출 /  반환타입 Connection
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userObj.getId());
			pstmt.setString(2, userObj.getPassword());
			
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 성공! =>"+USER_GET);
			if(rs.next()) {	
				//userDO 객체 생성후 setter 메서드를 통해 저장
				user = new UserDO();		
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;	//UserDO 객체 반환
	} 
	
	
}
