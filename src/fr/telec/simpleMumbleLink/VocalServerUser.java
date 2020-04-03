package fr.telec.simpleMumbleLink;

public class VocalServerUser {
	private String username;
	private String userid;
	private String channel;
	private boolean mute;

	public VocalServerUser(String username, String userid, String channel, boolean mute) {
		this.username = username;
		this.userid = userid;
		this.channel = channel;
		this.mute = mute;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}
}