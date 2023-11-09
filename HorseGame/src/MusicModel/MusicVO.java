package MusicModel;


public class MusicVO {

	private String title;
	private int playTime;
	private String musicPath;

	public MusicVO(String title, int playTime, String musicPath) {
		this.title = title;
		this.playTime = playTime;
		this.musicPath = musicPath;

	}

	public String getTitle() {
		return title;
	}


	public int getPlayTime() {
		return playTime;
	}

	public String getMusicPath() {
		return musicPath;
	}

}
