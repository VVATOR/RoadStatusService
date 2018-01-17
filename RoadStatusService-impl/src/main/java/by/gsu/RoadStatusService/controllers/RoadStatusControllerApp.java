package by.gsu.RoadStatusService.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.gsu.RoadStatusService.client.db.postgresql.DBClient;
import by.gsu.RoadStatusService.models.Picture;
import by.gsu.RoadStatusService.models.Point;

@Controller
@RequestMapping("/")
public class RoadStatusControllerApp implements SEIPicture {

	DBClient client = new DBClient();

	public static final Logger LOG = LoggerFactory.getLogger(RoadStatusControllerApp.class);

	private static AtomicLong id = new AtomicLong(10);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "picture", method = RequestMethod.GET)
	public ResponseEntity<List<Picture>> methodGetListPictures(HttpServletResponse response) throws Exception {

		return ResponseEntity.ok(client.getListPictures());
	}

	@RequestMapping(value = "picture/{id}", method = RequestMethod.GET)
	public ResponseEntity<Picture> methodGetPicture(@PathVariable long id, final HttpServletResponse response) {
		List<Picture> store = new ArrayList<>();
		try {
			store = client.getListPictures();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Picture picture : store) {
			if (picture.getId() == id) {
				return ResponseEntity.ok(picture);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "picture", method = RequestMethod.POST)
	public ResponseEntity<Long> methodPostPicture(@RequestBody Picture picture, final HttpServletResponse response) {
		long idTmp = id.incrementAndGet();
		picture.setId(idTmp);
		client.methodPostPicture(picture);
		LOG.info("{}", picture);
		// return (ResponseEntity<Long>) ResponseEntity.ok(idTmp);
		return ResponseEntity.ok(idTmp);
	}

	@RequestMapping(value = "picture", method = RequestMethod.PUT)
	public ResponseEntity<Long> methodPutPicture(@RequestParam Picture picture, final HttpServletResponse response) {
		LOG.info("{}", picture);
		List<Picture> store = new ArrayList<>();
		try {
			store = client.getListPictures();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < store.size(); i++) {
			Picture p = store.get(i);
			if (p.getId() == picture.getId()) {
				LOG.info("PUT: UPDATE; \n\t old:" + p + "\n\tnew:" + picture);
				client.methodPostPicture(picture);
				return ResponseEntity.ok((long) i);
			}
		}

		long idTmp = id.incrementAndGet();
		picture.setId(idTmp);
		store.add(picture);
		LOG.info("PUT: CREATE; new id = " + idTmp + ";" + picture);

		LOG.info("{}", picture);
		System.out.println("lol");
		return (ResponseEntity<Long>) ResponseEntity.notFound();
		// public ResponseEntity<Void> methodPutPicture(Picture picture,
		// HttpServletResponse response) {
		/*
		 * for (int index = 0; index < store.size(); index++) { if
		 * (store.get(index).getId() == picture.getId()) { store.set(index, picture);
		 * return (ResponseEntity<Void>) ResponseEntity.ok(); } } return
		 * (ResponseEntity<Void>) ResponseEntity.notFound();
		 */
	}

	@Override
	@RequestMapping(value = "picture/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> methodDeletePicture(@PathVariable long id, HttpServletResponse response) {
		List<Picture> store = new ArrayList<>();
		try {
			store = client.getListPictures();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Picture picture : store) {
			if (picture.getId() == id) {
				client.methodDeletePicture(id);
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "u", method = RequestMethod.GET)
	public @ResponseBody Picture upload(final HttpServletResponse response) {
		/*
		 * response.setHeader("Accept", "application/json");
		 * response.setHeader("Content-Type", "application/json");
		 */
		return new Picture(11, "aaa", "aaaaaaa", new Point(11, 22));
	}

	@ResponseBody
	@RequestMapping(value = "doc", method = RequestMethod.GET)
	public void userDataSklad(final HttpServletRequest request, final HttpServletResponse response) {
		response.setHeader("Accept", "application/json");
		response.setHeader("Content-Type", "application/json");
		String term = request.getParameter("term");

		response.setContentType("application/json");
		try {
			LOG.info("Data from ajax call " + term);

			String searchList = "ssssssssssss";
			response.getWriter().write(searchList);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Picture> handleAllUserRequest(final HttpServletResponse response) {
		List<Picture> store = new ArrayList<>();
		try {
			return client.getListPictures();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return store;
	}

}
