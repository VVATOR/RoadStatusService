package by.gsu.RoadStatusService.models;

import java.io.Serializable;





public abstract class Model implements Serializable {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model(long id) {
		super();
		this.id = id;
	}

}
