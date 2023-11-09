package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.MovingHorse;

public class DAO_자본금re extends DAO {

	public void checkZero() {
		DAO_Select dao_findid = new DAO_Select();
		Connect();

		try {
			// SQL 업데이트 쿼리
			String updateQuery = "UPDATE 회원정보 SET 자본금 = 500000 WHERE 아이디 = ? and 자본금 <= 0";
			
			PreparedStatement psmt = conn.prepareStatement(updateQuery);
			
			psmt.setString(1, dao_findid.id);

			int rowsUpdated = psmt.executeUpdate();
			
			if (rowsUpdated > 0) {
				System.out.println("파산!!! 자본금 추가지급 500만원!! 가즈아!!");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}
}
