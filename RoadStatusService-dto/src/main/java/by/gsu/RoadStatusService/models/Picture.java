package by.gsu.RoadStatusService.models;

import java.io.Serializable;

//@Entity
//(name = "road_picture")
public class Picture extends Model implements Serializable {
    // @Column
    private String name;
    // @Column
    private String description;

    private String data;
    // @Column
    private Point point;

    public Picture() {

    }

    public Picture(long id) {
        super(id);
    }

    public Picture(long id, String name, String description, Point point) {
        super(id);
        this.name = name;
        this.description = description;
        this.point = point;
    }

    public Picture(long id, String name, String description, Point point, String data) {
        super(id);
        this.name = name;
        this.description = description;
        this.point = point;
        this.data = data;
    }

    public Picture(long id, String name, String description, String data, Point point) {
        super(id);
        this.name = name;
        this.description = description;
        this.data = data;
        this.point = point;
    }

    public String getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Picture [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", data=");
		builder.append(data);
		builder.append(", point=");
		builder.append(point);
		builder.append("]");
		return builder.toString();
	}



}
