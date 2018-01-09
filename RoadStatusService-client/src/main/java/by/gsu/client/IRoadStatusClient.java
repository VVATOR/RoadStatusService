package by.gsu.client;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.gsu.RoadStatusService.models.Picture;

public interface IRoadStatusClient {

    public String getHost();

    public void setHost(String host);

    public List<Picture> getListPictures() throws JsonParseException, JsonMappingException, IOException;

    public Picture methodGetPicture(long id) throws JsonParseException, JsonMappingException, IOException;

    public void methodPostPicture(Picture picture);

    public void methodPutPicture(Picture picture);

    public void methodDeletePicture(long id);

}
