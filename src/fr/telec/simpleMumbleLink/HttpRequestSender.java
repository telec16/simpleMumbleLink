package fr.telec.simpleMumbleLink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestSender {

	// private static final String GET_URL =
	// "https://voice.clubelek.fr:80/api/servers/1/user";
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";

	public static String r(String method, String endpoint, String request) throws IOException {
		return r(method, endpoint, request, null);
	}

	public static String r(String method, String endpoint, String request, String params) throws IOException {
		URL obj = new URL(endpoint + request);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod(method);

		if (params != null && params.length() > 0) {
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(params.getBytes());
			os.flush();
			os.close();
		}

		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} else {
			return null;
		}

	}
}
