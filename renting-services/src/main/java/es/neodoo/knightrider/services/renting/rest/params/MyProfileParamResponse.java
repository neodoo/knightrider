package es.neodoo.knightrider.services.renting.rest.params;

public class MyProfileParamResponse {

	private long num_travels;

	private double cost;

	private double time;

	private double average;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNum_travels() {
		return num_travels;
	}

	public void setNum_travels(long num_travels) {
		this.num_travels = num_travels;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public MyProfileParamResponse(long num_travels, double time, double cost, double average, String name) {
		this.num_travels = num_travels;
		this.cost = cost;
		this.time = time;
		this.average = average;
		this.name = name;
	}

	public MyProfileParamResponse() {
	}

}
