package MusicView;

import java.util.Scanner;

import MusicController.MainController;

public class MainView {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		MainController c = new MainController();	
		int sel;
		while (true) {
			System.out.println("[1] 재생 [2] 정지 [3] 다음곡 [4] 이전곡 [5] 음악찾기 [6] 종료");
			sel = sc.nextInt();
			if(sel ==1) {
				c.playSuccess();
			} else if(sel ==2) {
				c.stop();
			} else {
				c.stop();
				break;
			}
		
		
		
		}
	}

}
