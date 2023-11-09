package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import MusicController.MainController;
import controller.MovingHorse;
import view.asciiArt;

public class DAO_Result3 extends DAO {

	int resultmoney = 0;
	MainController c = new MainController();
	
	public void update(MovingHorse mh) {
		DAO_Select dao_findid = new DAO_Select();
		
		
		Money usermoney = new Money();

		Connect();
		int userbetmoney = mh.betMoney;
		String sql = "update 회원정보 set 자본금=? WHERE 아이디 = ?"; // 아이디가 id인 회원의 자본금 업데이트

		try {
			resultmoney = usermoney.getMoney() - userbetmoney;
			
			
			System.out.println("배팅 실패!!");
			System.out.println(userbetmoney + "원을 잃었습니다...");
			
			c.playFail();

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
			
			System.out.println("현재 내 남은돈 : " + resultmoney);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Close();
		}

	}
}
