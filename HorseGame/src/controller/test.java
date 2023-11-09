package controller;

import model.DAO;
import model.DAO_Select;

public class test {

	public static void main(String[] args) {
		DAO dao = new DAO();
		
		//dao.Select();
		//dao.RankCheck();
		
		DAO_Select dao_login_check = new DAO_Select();
		dao_login_check.login();
		
		
		
	}

}
