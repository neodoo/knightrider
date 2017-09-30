package es.neodoo.knightrider.car.services.rest.params;

public class VehicleStateParamResponse {

	private Boolean df;

	private Boolean dr;

	private Boolean pf;

	private Boolean pr;

	private Boolean ft;

	private Boolean rt;

	private String car_verson;

	private Boolean locked;

	private Boolean sun_roof_installed;

	private String sun_roof_state;

	private int sun_roof_percent_open;

	private Boolean dark_rims;

	private String wheel_type;

	private Boolean has_spoiler;

	private String roof_color;

	private String perf_config;

	public Boolean getDf() {
		return df;
	}

	public void setDf(Boolean df) {
		this.df = df;
	}

	public Boolean getDr() {
		return dr;
	}

	public void setDr(Boolean dr) {
		this.dr = dr;
	}

	public Boolean getPf() {
		return pf;
	}

	public void setPf(Boolean pf) {
		this.pf = pf;
	}

	public Boolean getPr() {
		return pr;
	}

	public void setPr(Boolean pr) {
		this.pr = pr;
	}

	public Boolean getFt() {
		return ft;
	}

	public void setFt(Boolean ft) {
		this.ft = ft;
	}

	public Boolean getRt() {
		return rt;
	}

	public void setRt(Boolean rt) {
		this.rt = rt;
	}

	public String getCar_verson() {
		return car_verson;
	}

	public void setCar_version(String car_verson) {
		this.car_verson = car_verson;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getSun_roof_installed() {
		return sun_roof_installed;
	}

	public void setSun_roof_installed(Boolean sun_roof_installed) {
		this.sun_roof_installed = sun_roof_installed;
	}

	public String getSun_roof_state() {
		return sun_roof_state;
	}

	public void setSun_roof_state(String sun_roof_state) {
		this.sun_roof_state = sun_roof_state;
	}

	public int getSun_roof_percent_open() {
		return sun_roof_percent_open;
	}

	public void setSun_roof_percent_open(int sun_roof_percent_open) {
		this.sun_roof_percent_open = sun_roof_percent_open;
	}

	public Boolean getDark_rims() {
		return dark_rims;
	}

	public void setDark_rims(Boolean dark_rims) {
		this.dark_rims = dark_rims;
	}

	public String getWheel_type() {
		return wheel_type;
	}

	public void setWheel_type(String wheel_type) {
		this.wheel_type = wheel_type;
	}

	public Boolean getHas_spoiler() {
		return has_spoiler;
	}

	public void setHas_spoiler(Boolean has_spoiler) {
		this.has_spoiler = has_spoiler;
	}

	public String getRoof_color() {
		return roof_color;
	}

	public void setRoof_color(String roof_color) {
		this.roof_color = roof_color;
	}

	public String getPerf_config() {
		return perf_config;
	}

	public void setPerf_config(String perf_config) {
		this.perf_config = perf_config;
	}

	public VehicleStateParamResponse(){}

	public VehicleStateParamResponse(Boolean df, Boolean dr, Boolean pf, Boolean pr, Boolean ft, Boolean rt,
			String car_verson, Boolean locked, Boolean sun_roof_installed, String sun_roof_state,
			int sun_roof_percent_open, Boolean dark_rims, String wheel_type, Boolean has_spoiler, String roof_color,
			String perf_config) {

		super();
		this.df = df;
		this.dr = dr;
		this.pf = pf;
		this.pr = pr;
		this.ft = ft;
		this.rt = rt;
		this.car_verson = car_verson;
		this.locked = locked;
		this.sun_roof_installed = sun_roof_installed;
		this.sun_roof_state = sun_roof_state;
		this.sun_roof_percent_open = sun_roof_percent_open;
		this.dark_rims = dark_rims;
		this.wheel_type = wheel_type;
		this.has_spoiler = has_spoiler;
		this.roof_color = roof_color;
		this.perf_config = perf_config;

	}	

}