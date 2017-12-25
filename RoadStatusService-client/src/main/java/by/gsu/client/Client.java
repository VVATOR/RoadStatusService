package by.gsu.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.gsu.RoadStatusService.models.Picture;

public class Client {

	public List<Picture> GetListPicture() {
		return null;
	}

	public Picture methodGetPicture(long id) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = call("http://localhost:1380/RoadStatusService-impl/picture/" + id, "GET");;
		
		return mapper.readValue(jsonInString, Picture.class);
	}

	public void methodPostPicture(Picture picture) {
	}

	public void methodPutPicture(Picture picture) {
	}

	public void methodDeletePicture(long id) {

	}

	private String call(String urlAdress, String method) {
		String result = "";
		try {

			URL url = new URL(urlAdress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				result += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return result;

	}
}
