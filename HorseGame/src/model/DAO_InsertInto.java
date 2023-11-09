package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DAO_InsertInto extends DAO {

	String id;

	public boolean checkId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("ID를 입력하세요 : ");
		id = sc.next();
		ResultSet rs = null;
		Connect();

		String sql = "SELECT * FROM 회원정보 WHERE 아이디 = ?";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			return !rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 회원가입 insert into
	public void newMembership() {
		Scanner sc = new Scanner(System.in);
		while (true) {

			boolean idcheck = checkId();
			if (idcheck) {
				System.out.println();
				System.out.println("사용 가능한 ID입니다.");
				System.out.println();
				break;
			} else {
				System.out.println();
				System.out.println("이미 사용중인 ID입니다.");
				System.out.println();
			}
		}

		System.out.print("비밀번호를 입력하세요 : ");
		String pw = sc.next();
		System.out.print("사용할 닉네임을 입력하세요 : ");
		String nick = sc.next();
		System.out.print("나이를 입력하세요 : ");
		int age = sc.nextInt();
		try {
			Connect();

			String sql = "INSERT INTO 회원정보 VALUES (?,?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);

			// 1. 아이디 2. 비밀번호 3.닉네임 4.연령 5.자본금 6.파산횟수
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);
			psmt.setInt(4, age);
			psmt.setInt(5, 1000000);
			psmt.setInt(6, 0);

			int rowCnt = psmt.executeUpdate();

			if (rowCnt > 0) {
				System.out.println();
				System.out.println("회원가입 성공!!");
				System.out.println();
			} else {
				System.out.println();
				System.out.println("회원가입 실패...");
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Close();
		}

	}

}
