package MusicController;

import java.util.ArrayList;

import MusicModel.MusicVO;
import javazoom.jl.player.MP3Player;

public class MainController {

	ArrayList<MusicVO> musicList = new ArrayList<MusicVO>(5);

	MP3Player mp3 = new MP3Player();
	
	int musicNum =3;
	int index = musicNum;

	public MainController() {
		musicList.add(new MusicVO("미션실패",  1, "musicList/미션실패.mp3"));
		musicList.add(new MusicVO("돈",  1, "musicList/돈.mp3"));
		musicList.add(new MusicVO("입장곡",  1, "musicList/입장곡.mp3"));
		musicList.add(new MusicVO("탕",  1, "musicList/탕.mp3"));
		
	}

	public void playFail() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(0).getMusicPath());

	}
	
	public void playSuccess() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(1).getMusicPath());

	}
	public void playStart() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(2).getMusicPath());

	}
	public void betStart() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(3).getMusicPath());

	}

	public void stop() {
		mp3.stop();
	}

}
