package fr.telec.simpleMumbleLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MumbleLink implements VocalServerLink {


	private class Endpoint {
		public final String method;
		public final String request;
		public final List<String> datas;

		public Endpoint(String method, String request) {
			this(method, request, null);
		}

		public Endpoint(String method, String request, List<String> datas) {
			this.method = method;
			this.request = request;
			this.datas = datas;
		}
	}

	
	// See: https://github.com/alfg/murmur-rest
	//Placeholders
	private static final String SERVER_ID = ":serverid";
	private static final String USER_ID = ":userid";
	private static final String CHANNEL_ID = ":channelid";
	//Form data
	private static final String MESSAGE = "message";
	private static final String USER_SESSION = "usersession";
	// Servers
	private final Endpoint SERVERS_LIST = new Endpoint(HttpRequest.GET, "servers/");
	private final Endpoint SERVER_DETAILS = new Endpoint(HttpRequest.GET, "servers/" + SERVER_ID);
	private final Endpoint BROADCAST = new Endpoint(HttpRequest.POST, "servers/" + SERVER_ID + "/sendmessage", Arrays.asList(MESSAGE));
	// Stats
	private final Endpoint STATS = new Endpoint(HttpRequest.GET, "stats/");
	// Users
	private final Endpoint USERS_LIST = new Endpoint(HttpRequest.GET, "servers/" + SERVER_ID + "/user");
	private final Endpoint USER_DETAILS = new Endpoint(HttpRequest.GET, "servers/" + SERVER_ID + "/user/" + USER_ID);
	private final Endpoint USER_MUTE = new Endpoint(HttpRequest.POST, "servers/" + SERVER_ID + "/user/" + USER_ID + "/mute");
	private final Endpoint USER_UNMUTE = new Endpoint(HttpRequest.POST, "servers/" + SERVER_ID + "/user/" + USER_ID + "/unmute");
	private final Endpoint USER_KICK = new Endpoint(HttpRequest.POST, "servers/" + SERVER_ID + "/kickuser", Arrays.asList(USER_SESSION));
	// Channels
	private final Endpoint CHANNELS_LIST = new Endpoint(HttpRequest.GET, "servers/" + SERVER_ID + "/channels");
	private final Endpoint CHANNEL_DETAILS = new Endpoint(HttpRequest.GET, "servers/" + SERVER_ID + "/channels/" + CHANNEL_ID);


	private String baseUri;
	private String serverId;

	public MumbleLink(String baseUri, String serverId) {
		reload(baseUri, serverId);
	}

	@Override
	public void reload(String baseUri, String serverId) {
		this.baseUri = baseUri;
		this.serverId = serverId;
	}

	@Override
	public List<VocalServerUser> getActiveUsers(String channel, boolean deepSearch) throws IOException {
		List<String> channels = getValidsChannelsIds(channel, deepSearch);
		List<VocalServerUser> users = getAllUsers();
		List<VocalServerUser> actives = new ArrayList<VocalServerUser>();

		for (VocalServerUser user : users) {
			if (channels.contains(user.getChannel()))
				actives.add(user);
		}

		return actives;
	}

	@Override
	public boolean kickUser(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<String> getValidsChannelsIds(String channel, boolean deepSearch) throws IOException {
		List<String> channels = new ArrayList<String>();
		String json = HttpRequest.r(CHANNELS_LIST.method, baseUri, CHANNELS_LIST.request.replace(SERVER_ID, serverId));

		return channels;

	}

	private List<VocalServerUser> getAllUsers() throws IOException {
		List<VocalServerUser> users = new ArrayList<VocalServerUser>();
		String json = HttpRequest.r(USERS_LIST.method, baseUri, USERS_LIST.request.replace(SERVER_ID, serverId));

		return users;

	}

}
