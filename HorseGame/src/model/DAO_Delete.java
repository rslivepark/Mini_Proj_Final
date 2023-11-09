package model;

import java.sql.SQLException;

public class DAO_Delete extends DAO {

	// 파산 3회 삭제 delete
	public void Delete() {
		Connect();

		String sql = "DELETE FROM 회원정보 WHERE 파산횟수=3";

		try {
			psmt = conn.prepareStatement(sql);
			int cnt = psmt.executeUpdate();

//			if (cnt > 0) {
//				System.out.println("입력성공");
//			} else {
//				System.out.println("입력실패");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}// delete 끝

}
