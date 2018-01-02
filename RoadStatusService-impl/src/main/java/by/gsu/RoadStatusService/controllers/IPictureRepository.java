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


public interface IPictureRepository {

	public List<Picture> GetListPicture();
	
	public Picture methodGetPicture(long id);
	
	public void methodPostPicture(Picture picture);

	public void methodPutPicture(Picture picture);

	public void methodDeletePicture(long id);	
	

}
