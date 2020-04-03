package fr.telec.simpleMumbleLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface VocalServerLink {

	void reload(String baseUri, String serverId);

	List<VocalServerUser> getActiveUsers(String channel, boolean deepSearch) throws IOException;

	boolean kickUser(String name);

}
