package by.gsu.RoadStatusService.client.db.postgresql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.gsu.RoadStatusService.models.Picture;
import by.gsu.RoadStatusService.models.Point;

public class DBClient {
	private Connection conn = null;

	public DBClient() {
		super();
		try {
			conn = PostgresqlConnection.newConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBClient client = new DBClient();

		try {
			System.out.println(client.methodGetPicture(3));

			client.methodPostPicture(new Picture(1, "11", "111", new Point(20.3, 30.5)));
			System.out.println(client.getListPictures());

			client.methodDeletePicture(5);
			System.out.println(client.getListPictures());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setHost(String host) {
		// TODO Auto-generated method stub

	}

	public List<Picture> getListPictures() throws JsonParseException, JsonMappingException, IOException {

		List<Picture> pictures = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM picture");
			ResultSet result1 = preparedStatement.executeQuery();

			System.out.println("Выводим statement");
			while (result1.next()) {
				result1.getRow();
				Picture picture = new Picture(

						result1.getInt("id"), result1.getString("name"), result1.getString("description"),
						result1.getString("data"), new Point(result1.getDouble("x"), result1.getDouble("y")));
				pictures.add(picture);

				System.out.println("Номер в выборке #" + "\t Номер в базе #" + result1.getInt("id") + "\t"
						+ result1.getString("name") + "\t" + result1.getString("description") + "\t"
						+ result1.getString("data")

				);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pictures;
	}

	public Picture methodGetPicture(long id) throws JsonParseException, JsonMappingException, IOException {
		List<Picture> pictures = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM picture");
			ResultSet result1 = preparedStatement.executeQuery();

			System.out.println("Выводим statement");
			while (result1.next()) {
				result1.getRow();
				Picture picture = new Picture(

						result1.getInt("id"), result1.getString("name"), result1.getString("description"),
						result1.getString("data"), new Point(result1.getDouble("x"), result1.getDouble("y")));

				System.out.println("Номер в выборке #" + "\t Номер в базе #" + result1.getInt("id") + "\t"
						+ result1.getString("name") + "\t" + result1.getString("description") + "\t"
						+ result1.getString("data"));

				return picture;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void methodPostPicture(Picture picture) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO picture ("
					// + "id,"
					+ "name," + "description," + "data," + "x," + "y" + ") values("
					// + "?,"
					+ "?," + "?," + "?," + "?," + "?" + ")");

			// preparedStatement.setInt(1, (int) picture.getId());
			preparedStatement.setString(1, picture.getName());
			preparedStatement.setString(2, picture.getDescription());
			preparedStatement.setString(3, picture.getData());
			preparedStatement.setString(4, "" + picture.getPoint().getX());
			preparedStatement.setString(5, "" + picture.getPoint().getY());

			// метод принимает значение без параметров
			// темже способом можно сделать и UPDATE
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void methodPutPicture(Picture picture) {
	}

	public void methodDeletePicture(long id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM picture WHERE id = ?");
			preparedStatement.setInt(1, (int) id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
