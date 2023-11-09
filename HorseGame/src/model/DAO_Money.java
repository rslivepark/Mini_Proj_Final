package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.MovingHorse;

public class DAO_Money extends DAO {
	
	public int money_select() {
		DAO_Select dao_select = new DAO_Select();

		Connect();
		String sql = "SELECT 자본금 FROM 회원정보 WHERE 아이디 = ?";

		try {
			while(rs.next()) {
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(5, dao_select.id);
				
				rs = psmt.executeQuery();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch (NullPointerException e)  {
//			System.out.println("자본금이 없습니다.");
//		}
		finally {
			Close();
		}
		return 0;

	}
	
}
