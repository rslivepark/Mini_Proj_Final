package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MusicController.MainController;
import model.BettingRate;
import model.DAO_InsertInto;
import model.DAO_Money;
import model.DAO_Result1;
import model.DAO_Result2;
import model.DAO_Result3;
import model.DAO_Select;
import model.DAO_자본금re;
import model.DAO_파산횟수update;
import model.Horse;
import view.CheckInfo;
import view.GameStart;
import view.Login;
import view.Main;
import view.SignUp;
import view.asciiArt;

//경마 로직
public class MovingHorse {

	public static int horseSelect = 0;
	public static int betSuccess = 0;
	public int betMoney = 0;

	public static void main(String[] args) {
		MainController c = new MainController();
		Scanner sc = new Scanner(System.in);

		// 프롤로그
		c.playStart();
		asciiArt ascii = new asciiArt();
		asciiArt.asciiname();
		Main main = new Main();
		main.main_Render();

		Login login = new Login();
		login.login_Render();

		Horse horse = new Horse(null);

		DAO_Select dao_Select = new DAO_Select();
		DAO_InsertInto dao_InsertInto = new DAO_InsertInto();
		GameStart gameStart = new GameStart();

		while (true) {

			// 게임 초기 화면 -> 로그인 or 회원가입 or 종료

			int select = sc.nextInt();

			if (select == 1) { // 로그인
				dao_Select.login();
				// 로그인이 됐음을 알리는 문구 ->view login에서
				break;

			} else if (select == 2) { // 회원가입
				SignUp signUp = new SignUp();
				signUp.sign();

				dao_InsertInto.newMembership();
				break;

			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		


		// 게임 스타트 부분
		DAO_Result1 remainMoney = new DAO_Result1();
		while (true) {
			c.stop();

			// 말 5마리 생성 -> 배팅 전에 생성해야 선택한 말 변수에 담음.
			List<Horse> horses = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				horses.add(new Horse("Horse" + i));
			}
			
			gameStart.start_Render();

			BettingRate bettingRate = new BettingRate();
			MovingHorse mh = new MovingHorse();

			// 1.배팅 or 2.정보조회 선택
			int select = sc.nextInt();
			Line line = new Line();
			if (select == 1) {
				line.lineNext();
				ascii.asciiStart();
				System.out.println("배팅이 시작됐습니다!!");
				System.out.println();
				System.out.println("몇 번 말에 배팅 하시겠습니까?");
				System.out.print("[1] [2] [3] [4] [5] >> ");
				horseSelect = sc.nextInt();
				
				// 배팅 금액
				remainMoney.remainMoney(mh);
				mh.betMoney = bettingRate.getBetMoney();
				c.betStart();
				
				System.out.println();

				// 내가 선택한 말의 번호를 List<Horse> horses의 말 인덱스번호+1로
				// 그 번호를 필드 변수에 담아줌.
				// 그 변수가 우승한 말과 비교 -> 우승 판별
				// if 1등이면 자본금에 로그인 아이디에 상금 누적
				line.lineNext();
				System.out.println("경마 게임을 시작합니다!!");

				// 말을 랜덤하게 이동하면서 천천히 출력
				moveHorses(horses, 5);

				// 우승한 말 찾기
				Horse winner = findWinner(horses);

				DAO_Result1 dao1 = new DAO_Result1();
				DAO_Result2 dao2 = new DAO_Result2();
				DAO_Result3 dao3 = new DAO_Result3();

				DAO_자본금re 자본re = new DAO_자본금re();
				DAO_파산횟수update 파산 = new DAO_파산횟수update();


				if (winner.getName().equals("Horse" + horseSelect)) {
					ascii.asciiBetSuccess();
					System.out.println("우승한 말은 " + winner.getName() + "입니다!!");
					dao1.update(mh);
					// dao_bet_Update.update();
					// 1등 축하 메세지
					// dao 업데이트 -> 상금 자본금 누적

				} else {
					ascii.asciibetfail();
					System.out.println("우승한 말은 " + winner.getName() + "입니다!!");
					dao3.update(mh);
					파산.checkUnderZero();
					자본re.checkZero();
				}


				// 파산.checkUnderZero();
				// 자본re.checkZero();
				
				System.out.println();
				System.out.print("게임을 다시 시작하겠습니까? [1]네 [2]아니오 >> ");
				int choose = sc.nextInt();
				if (choose == 1) {

				} else {
					break;
				}

			} else if (select == 2) {

				CheckInfo checkInfo = new CheckInfo();
				checkInfo.info();

				int infoSelect = sc.nextInt();
				if (infoSelect == 1) {
					dao_Select.RankCheck();
				} else if (infoSelect == 2) {
					dao_Select.playerInfo();
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}

				System.out.print("게임을 다시 시작하겠습니까? [1]네 [2]아니오 >> ");
				int choose = sc.nextInt();
				if (choose == 1) {

				} else {
					break;
				}

			}

			else {
				System.out.println("잘못 입력하셨습니다.");
			}

			// 배팅 후 후행 알고리즘.

		}

	}

	// 말을 랜덤하게 이동하면서 천천히 출력하게 하기 위한 메서드
	private static void moveHorses(List<Horse> horses, int moves) {
		int finishLine = 15; // 결승선 위치
		
		Line line = new Line();
		
		for (int i = 1; i <= moves; i++) {
			for (Horse horse : horses) {
				if (!horse.hasFinished()) {
					horse.move();
					System.out.println(horse.getName() + ": " + horsePosition(horse.getPosition(), finishLine));

					// 결승선에 도달한 말은 이동을 멈춥니다.
					if (horse.getPosition() >= finishLine) {
						horse.setFinished(true);
					}
				}
			}
			speed(1000); // 1000밀리초(1초) 대기
			line.lineNext();
			
		}
	}

	// 스레드 슬립 매서드.
	private static void speed(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 경주중 말 위치 메서드
	private static String horsePosition(int position, int finishLine) {
		StringBuilder positionString = new StringBuilder();
		for (int i = 0; i < finishLine; i++) {
			if (i == position) {
				positionString.append("★");
			} else {
				positionString.append("     ");
			}
		}
		positionString.append("|"); // 결승선을 나타내는 부분
		return positionString.toString();
	}

	// 우승한 말 찾기 메서드
	private static Horse findWinner(List<Horse> horses) {
		Horse winner = horses.get(0);
		for (Horse horse : horses) {
			if (horse.getPosition() > winner.getPosition()) {
				winner = horse;
			}
		}
		return winner;
	}
	// 내가 선택한 말과 우승한 말이 같은지 검증
	// if 같다면 내 DB에 update로 자본금 누적, 같지 않다면 배팅 실패
	// 같지 않을 때 update로 자본금-배팅금액
	// 파산 여부 확인 -> DB에서 select로 불러온 자본금이 0이면 파산으로 판별
	// 파산이면 파산횟수 DB에 update로 횟수 누적
	// DB에서 파산횟수를 select로 불러오고 if == 3 이면
	// 계정 삭제 DAO 실행

	// MovingHorse 클래스에서 배팅~결과 까지는 while로 반복

}