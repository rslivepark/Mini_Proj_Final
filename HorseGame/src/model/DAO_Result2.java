package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.MovingHorse;

public class DAO_Result2 extends DAO {

	int resultmoney = 0;
	
	

	public void update(MovingHorse mh) {
		Money usermoney = new Money();
		DAO_Select dao_findid = new DAO_Select();

		Connect();
		int userbetmoney = mh.betMoney;
		String sql = "update 회원정보 set 자본금=? WHERE 아이디 = ?"; // 아이디가 id인 회원의 자본금 업데이트

		try {

			resultmoney = usermoney.getMoney() + (userbetmoney * 5);
			System.out.println("배팅 성공!!");
			System.out.println("2등!! 축하드립니다!!");
			System.out.println("상금 : "+(userbetmoney*5));
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, resultmoney);
			psmt.setString(2, dao_findid.id);

			int rowCnt = psmt.executeUpdate();


			if (rowCnt > 0) {
				System.out.println("정산완료");
				//System.out.println("현재 자본금 : " + money + "원");
			} else {
				System.out.println("정산실패");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Close();
		}

	}
}
