package model;

import java.util.Scanner;

public class BettingRate {
	
	public int userbet;
	
	public int getBetMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.print("배팅할 금액 : ");
		int bet = sc.nextInt();
		
		userbet = bet;
		
		return userbet;
	}
	

}
