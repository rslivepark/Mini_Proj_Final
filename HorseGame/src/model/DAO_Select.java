package model;

import java.sql.SQLException;
import java.util.Scanner;

import view.asciiArt;

public class DAO_Select extends DAO {
	
	//로그인하기
	public static String id;
	public boolean login() {

		Connect();
		Scanner sc = new Scanner(System.in);

		boolean result = false;
		String sql = "SELECT * FROM 회원정보 WHERE 아이디 = ? AND 비밀번호 = ?";

		try {

			while (true) {
				psmt = conn.prepareStatement(sql);
				System.out.println();
				System.out.print("아이디를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("비밀번호를 입력해주세요 : ");
				String pw = sc.next();
				
				psmt.setString(1, id);
				psmt.setString(2, pw);
				rs = psmt.executeQuery();
				
				this.id = id;
				
				if (rs.next()) {
					result = true;
					break;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("로그인 성공");
		System.out.println();
		return result;
		
	}

	// 조회하기 select -> 정보조회
	public void playerInfo() {
		Connect();

		String sql = "SELECT * FROM 회원정보 WHERE 닉네임=?";

		Scanner sc = new Scanner(System.in);

		try {
			psmt = conn.prepareStatement(sql);
			System.out.println(" ");
			System.out.print("조회하실 닉네임을 입력해주세요 : ");
			String insertId = sc.next();
			psmt.setString(1, insertId);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String nick = rs.getString(3);
				int age = rs.getInt(4);
				int money = rs.getInt(5);
				int bankrun = rs.getInt(6);
				System.out.println();
				System.out.print("닉네임 : " + nick + " | ");
				System.out.print("연령 : " + age + " | ");
				System.out.print("자본금 : " + money + " | ");
				System.out.println("파산횟수 : " + bankrun + " | ");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}// select 끝

	// 조회하기 select -> 랭킹
	public void RankCheck() {
		Connect();

		String sql = "SELECT * FROM 회원정보 ORDER BY 자본금 DESC";

		Scanner sc = new Scanner(System.in);

		int rankcnt = 1;
		asciiArt ascii = new asciiArt();
		ascii.asciisearchRank();
		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String nick = rs.getString(3);
				int money = rs.getInt(5);
				System.out.print(rankcnt + "위 | ");
				System.out.print("닉네임 : " + nick + " | ");
				System.out.print("자본금 : " + money + " | ");
				System.out.println();
				rankcnt++;
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// select 끝

	
}
