package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Money extends DAO {

	private int money;

	public Money() {
		Connect();
		DAO_Select dao_findid = new DAO_Select();
		String sql = "SELECT 자본금 FROM 회원정보 WHERE 아이디 = ?";
		

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dao_findid.id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				this.money = rs.getInt("자본금");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return money;

	}

	public int getMoney() {

		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}