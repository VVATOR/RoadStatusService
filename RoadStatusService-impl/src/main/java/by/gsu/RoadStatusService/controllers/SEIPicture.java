package by.gsu.RoadStatusService.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.gsu.RoadStatusService.models.Picture;


public interface SEIPicture {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index();

	
	@RequestMapping(value = "picture", method = RequestMethod.GET)
	public ResponseEntity<Picture> methodGetListPicture(final HttpServletResponse response);

	@RequestMapping(value = "picture/{id}", method = RequestMethod.GET)
	public ResponseEntity<Picture> methodGetPicture(@PathVariable long id, final HttpServletResponse response);

	
	@RequestMapping(value = "picture", method = RequestMethod.POST)
	public ResponseEntity<Void> methodPostPicture(@RequestParam Picture picture, final HttpServletResponse response);

	@RequestMapping(value = "picture", method = RequestMethod.PUT)
	public ResponseEntity<Void> methodPutPicture(@RequestParam Picture picture, final HttpServletResponse response);

	@RequestMapping(value = "picture/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> methodDeletePicture(long id, HttpServletResponse response);
	
	
	
	
	@RequestMapping(value = "u", method = RequestMethod.GET)
	public @ResponseBody Picture upload(final HttpServletResponse response);

	@ResponseBody
	@RequestMapping(value = "doc", method = RequestMethod.GET)
	public void userDataSklad(final HttpServletRequest request, final HttpServletResponse response);

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Picture> handleAllUserRequest(final HttpServletResponse response);

}
