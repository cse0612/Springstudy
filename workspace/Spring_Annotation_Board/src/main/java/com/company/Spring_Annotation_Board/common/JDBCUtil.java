package com.company.Spring_Annotation_Board.common;

import java.sql.*;

/**
 * JDBCUtil 클래스는 유틸리티 클래스로 각 DAO 클래스에서 접근하는 클래스로 공통으로 사용하는 클래스이다 한번 만들어 놓으면 다른
 * 프로젝트 개발시 '재사용' 할 수 있다
 */

public class JDBCUtil {
	// H2 데이터베이스 연동에 관한 소스
	static final String driver = "org.h2.Driver";
	static final String url = "jdbc:h2:tcp://localhost/~/test";

	// 초기 DB 연결 메서드   * 반환타입 Connection
	public static Connection getConnection() throws Exception {
		try {
			Class.forName(driver); // driver 로딩
			System.out.println("드라이버 로드 성공!");
			   
			Connection con = DriverManager.getConnection(url, "sa", "");
			System.out.println("데이타베이스 접속 성공!");
			
			return con;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// DML 작업 (insert, update, delete)종료시 호출하여 자원해제 시키는 메서드
	public static void close(PreparedStatement pstmt, Connection conn) {

		if (pstmt != null) {
			try {
				if (!pstmt.isClosed())	pstmt.close(); // 리소스 해제
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null; // 초기화
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed()) conn.close(); // 리소스 해제
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null; // 초기화
			}
		}
	}

	// Overloading : select 작업 종료시 호출하여 자원해제 시키는 메서드
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed())  rs.close(); // 리소스 해제
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null; // 초기화
			}
		}
		if (pstmt != null) {
			try {
				if (!pstmt.isClosed())
					pstmt.close(); // 리소스 해제
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null; // 초기화
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close(); // 리소스 해제
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null; // 초기화
			}
		}
	}
}
