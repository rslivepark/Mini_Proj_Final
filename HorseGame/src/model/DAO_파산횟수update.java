package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.MovingHorse;

public class DAO_파산횟수update extends DAO {

	public void checkUnderZero() {
		DAO_Select dao_findid = new DAO_Select();
		Connect();

		try {
			// SQL 업데이트 쿼리
			String updateQuery = "UPDATE 회원정보 SET 파산횟수 = 파산횟수 + 1 WHERE 아이디 =? and 자본금 <= 0";

			PreparedStatement psmt = conn.prepareStatement(updateQuery);

			psmt.setString(1, dao_findid.id);

			int rowsUpdated = psmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}
}
