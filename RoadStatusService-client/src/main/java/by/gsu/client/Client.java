package by.gsu.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.gsu.RoadStatusService.models.Picture;
import by.gsu.RoadStatusService.models.Point;

public class Client implements IRoadStatusClient {
    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    private String host = "http://localhost:8080/RoadStatusService-impl";
    // private String host = "http://localhost:8080/RoadStatusService-impl";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Client() {
        super();
    }

    public Client(String host) {
        super();
        this.host = host;
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.getListPictures();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        client.methodPutPicture(new Picture(4, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "222", new Point(20.3363, 40.2555)));

        client.methodPostPicture(new Picture(4, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq111", "222", new Point(20.3363, 40.2555)));
        
        try {
            client.methodGetPicture(4);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // client.methodDeletePicture(3);

    }

    public List<Picture> getListPictures() throws JsonParseException, JsonMappingException, IOException {
        String result = "";
        try {

            URL url = new URL(host + "/picture");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
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

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = result;
        Picture[] pictures = mapper.readValue(jsonInString, Picture[].class);
        return new ArrayList(Arrays.asList(pictures));
    }

    public Picture methodGetPicture(long id) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = call(host + "/picture/" + id, "GET");

        return mapper.readValue(jsonInString, Picture.class);
    }

    public void methodPostPicture(Picture picture) {

        String result = "";

        try {

            URL url = new URL(host + "/picture");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            ObjectMapper mapper = new ObjectMapper();

            String jsonInString = mapper.writeValueAsString(picture);
            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes("UTF-8"));
            os.close();

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
        // return result;

    }

    public void methodPutPicture(Picture picture) {

        String result = "";

        try {

            URL url = new URL(host + "/picture");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            ObjectMapper mapper = new ObjectMapper();

            String jsonInString = mapper.writeValueAsString(picture);
            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes("UTF-8"));
            os.close();
            int status = conn.getResponseCode();
            if (status != 200) {
                System.err.println(status);
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
        // return result;

    }

    public void methodDeletePicture(long id) {
        String result = "";
        try {

            URL url = new URL(host + "/picture/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

            System.out.println("---Output from Server .... \n");
            if (conn.getResponseCode() < 200 || conn.getResponseCode() >= 300) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            System.out.println("+++Output from Server .... \n" + conn.getResponseCode());

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (RuntimeException e) {
            System.out.println("ok" + e.getMessage());
        }
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
