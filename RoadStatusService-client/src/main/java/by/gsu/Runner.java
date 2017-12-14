package by.gsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Runner {
	 static final Logger log = LoggerFactory.getLogger(Runner.class);
/*
	public static void main(String[] args) {

		log.info("___CONVERT TO BASE64___\n");
		String filePath = "src/main/resources/road.jpeg";
		File originalFile = new File(filePath);
		String encodedBase64 = null;
		try (InputStream fileInputStreamReader = Files.newInputStream(Paths.get(filePath))) {
			byte[] bytes = new byte[(int) originalFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			Picture obj = new Picture(11);
			obj.setId(11);
			obj.setPoint(new Point(11, 33));
			obj.setData(encodedBase64);
			obj.setName("aaaa");
			obj.setDescription("fuck");

			// Object to JSON in file
			String jsonInString = mapper.writeValueAsString(obj);
			//mapper.writeValue(new File("D:\\file.json"), obj);
			log.info(encodedBase64);
			log.info(jsonInString);
			
			System.out.println(jsonInString);
			// Object to JSON in String
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	*/
	
	
	public static void main(String[] args) {

		  try {

			URL url = new URL("http://localhost:1380/RoadStatusService-impl/picture/3");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
}
