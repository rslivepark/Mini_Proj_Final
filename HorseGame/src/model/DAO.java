package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

	// 로그인 DB와 동일한지

	// 회원가입 insert into
	// 게임 할 때마다 update

	// 조회하기 select -> 랭킹, 정보조회(닉네임 기준)
	// 파산 3회 삭제 delete
	
	// 파산할 때마다 파산횟수를 DB에서 증가시켜줄 DAO
	// 파산횟수>=3 조건을 발동시키기 위한 파산횟수 조회 DAO

	// 커넥션
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	protected void Connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
			String db_id = "mokpo_21K_AI_mini_3";
			String db_pw = "smhrd3";
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String db_id = "service";
//			String db_pw = "12345";
			conn = DriverManager.getConnection(url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			System.out.println("DB 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}

	// 클로즈
	public void Close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//	// 조회하기 select -> 정보조회
//	public void Select() {
//		Connect();
//
//		String sql = "SELECT * FROM 회원정보 WHERE 아이디=?";
//
//		Scanner sc = new Scanner(System.in);
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			System.out.print("조회하실 아이디를 입력해주세요 >>");
//			String insertId = sc.next();
//			psmt.setString(1, insertId);
//
//			rs = psmt.executeQuery();
//
//			while (rs.next()) {
//				String id = rs.getString(1);
//				String nick = rs.getString(3);
//				int age = rs.getInt(4);
//				int money = rs.getInt(5);
//				int bankrun = rs.getInt(6);
//				System.out.print("아이디 : " + id + " | ");
//				System.out.print("닉네임 : " + nick + " | ");
//				System.out.print("연령 : " + age + " | ");
//				System.out.print("자본금 : " + money + " | ");
//				System.out.print("파산횟수 : " + bankrun + " | ");
//				System.out.println();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Close();
//		}
//	}// select 끝
//
//	// 조회하기 select -> 랭킹
//	public void RankCheck() {
//		Connect();
//
//		String sql = "SELECT * FROM 회원정보 ORDER BY 자본금 DESC";
//
//		Scanner sc = new Scanner(System.in);
//
//		int rankcnt = 1;
//
//		try {
//			psmt = conn.prepareStatement(sql);
//
//			rs = psmt.executeQuery();
//
//			while (rs.next()) {
//				String nick = rs.getString(3);
//				int money = rs.getInt(5);
//				int bankrun = rs.getInt(6);
//				System.out.print(rankcnt + "위 | ");
//				System.out.print("닉네임 : " + nick + " | ");
//				System.out.print("자본금 : " + money + " | ");
//				System.out.print("파산횟수 : " + bankrun + " | ");
//				System.out.println();
//				rankcnt++;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				psmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}// select 끝
//
//	// 회원가입 insert into
//	public void newMembership() {
//		Scanner sc = new Scanner(System.in);
//		Connect();
//		try {
//
//			System.out.print("ID를 입력하세요 : ");
//			String id = sc.next();
//			System.out.print("비밀번호를 입력하세요 : ");
//			String pw = sc.next();
//			System.out.print("사용할 닉네임을 입력하세요 : ");
//			String nick = sc.next();
//			System.out.print("나이를 입력하세요");
//			int age = sc.nextInt();
//
//			String sql = "INSERT INTO 회원정보 VALUES (?,?,?,?,?,?)";
//
//			PreparedStatement psmt = conn.prepareStatement(sql);
//
//			// 1. 아이디 2. 비밀번호 3.닉네임 4.연령 5.자본금 6.파산횟수
//			psmt.setString(1, id);
//			psmt.setString(2, pw);
//			psmt.setString(3, nick);
//			psmt.setInt(4, age);
//			psmt.setInt(5, 10000000);
//			psmt.setInt(6, 0);
//
//			int rowCnt = psmt.executeUpdate();
//
//			if (rowCnt > 0) {
//				System.out.println("회원가입 성공!");
//			} else {
//				System.out.println("회원가입 실패...");
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			Close();
//		}
//
//	}
//
//	// 게임 할 때마다 update
//	public void update() {
//		boolean result = true; // 경마결과: 베팅성공 -> true, 실패 -> false / 경기결과에 따라 바뀌도록 수정해야함!
//		int money = 0; // 자본금
//		int betmoney = 0; // 베팅금액
//		int DIVIDEND_RATE = 5; // 1등배당률
//		Connect();
//		String sql = "update 회원정보 set 자본금=? WHERE 아이디 = id";
//
//		try {
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			int resultmoney;
//			if (result) {
//				resultmoney = money + (betmoney * DIVIDEND_RATE);
//			} else {
//				resultmoney = money - betmoney;
//			}
//
//			psmt.setInt(1, (resultmoney));
//
//			int rowCnt = psmt.executeUpdate();
//			if (rowCnt > 0) {
//				System.out.println("정산완료");
//			} else {
//				System.out.println("정산실패");
//			}
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} finally {
//			Close();
//		}
//
//	}
//
//	// 파산 3회 삭제 delete
//	public void Delete() {
//		Connect();
//
//		String sql = "DELETE FROM 회원정보 WHERE 파산횟수=3";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			int cnt = psmt.executeUpdate();
//
//			if (cnt > 0) {
//				System.out.println("입력성공");
//			} else {
//				System.out.println("입력실패");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Close();
//		}
//	}// delete 끝

}
