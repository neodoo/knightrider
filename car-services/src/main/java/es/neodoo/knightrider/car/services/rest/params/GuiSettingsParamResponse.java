package es.neodoo.knightrider.car.services.rest.params;

public class GuiSettingsParamResponse {

	private String gui_distance_units;

	private String gui_temperature_units;

	private String gui_charge_rate_units;

	private Boolean gui_24_hour_time;

	private String gui_range_display;

	public String getGui_distance_units() {
		return gui_distance_units;
	}

	public void setGui_distance_units(String gui_distance_units) {
		this.gui_distance_units = gui_distance_units;
	}

	public String getGui_temperature_units() {
		return gui_temperature_units;
	}

	public void setGui_temperature_units(String gui_temperature_units) {
		this.gui_temperature_units = gui_temperature_units;
	}

	public String getGui_charge_rate_units() {
		return gui_charge_rate_units;
	}

	public void setGui_charge_rate_units(String gui_charge_rate_units) {
		this.gui_charge_rate_units = gui_charge_rate_units;
	}

	public Boolean getGui_24_hour_time() {
		return gui_24_hour_time;
	}

	public void setGui_24_hour_time(Boolean gui_24_hour_time) {
		this.gui_24_hour_time = gui_24_hour_time;
	}

	public String getGui_range_display() {
		return gui_range_display;
	}

	public void setGui_range_display(String gui_range_display) {
		this.gui_range_display = gui_range_display;
	}

	public GuiSettingsParamResponse(){}

	public GuiSettingsParamResponse(String gui_distance_units, String gui_temperature_units,
			String gui_charge_rate_units, Boolean gui_24_hour_time, String gui_range_display) {
	
		this.gui_distance_units = gui_distance_units;
		this.gui_temperature_units = gui_temperature_units;
		this.gui_charge_rate_units = gui_charge_rate_units;
		this.gui_24_hour_time = gui_24_hour_time;
		this.gui_range_display = gui_range_display;
	
	}

}