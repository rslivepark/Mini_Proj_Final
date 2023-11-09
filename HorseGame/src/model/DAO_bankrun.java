package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.MovingHorse;

public class DAO_bankrun extends DAO {

	
	DAO_Select dao_select = new DAO_Select();
	
	public int 파산() {

		Connect();
		String sql = "SELECT 파산횟수 FROM 회원정보 WHERE 아이디 = ?";

		try {
			while(rs.next()) {
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(5, dao_select.id);
				
				
				rs = psmt.executeQuery();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
		return 0;

	}
	
	
	
}
