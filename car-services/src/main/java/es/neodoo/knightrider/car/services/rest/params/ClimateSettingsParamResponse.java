package es.neodoo.knightrider.car.services.rest.params;

public class ClimateSettingsParamResponse {

	private double inside_temp;

	private double outside_temp;

	private double driver_temp_setting;

	private double passenger_temp_setting;

	private Boolean is_auto_conditioning_on;

	private Boolean is_front_defroster_on;

	private Boolean is_rear_defroster_on;

	private int fan_status;

	public double getInside_temp() {
		return inside_temp;
	}

	public void setInside_temp(double inside_temp) {
		this.inside_temp = inside_temp;
	}

	public double getOutside_temp() {
		return outside_temp;
	}

	public void setOutside_temp(double outside_temp) {
		this.outside_temp = outside_temp;
	}

	public double getDriver_temp_setting() {
		return driver_temp_setting;
	}

	public void setDriver_temp_setting(double driver_temp_setting) {
		this.driver_temp_setting = driver_temp_setting;
	}

	public double getPassenger_temp_setting() {
		return passenger_temp_setting;
	}

	public void setPassenger_temp_setting(double passenger_temp_setting) {
		this.passenger_temp_setting = passenger_temp_setting;
	}

	public Boolean getIs_auto_conditioning_on() {
		return is_auto_conditioning_on;
	}

	public void setIs_auto_conditioning_on(Boolean is_auto_conditioning_on) {
		this.is_auto_conditioning_on = is_auto_conditioning_on;
	}

	public Boolean getIs_front_defroster_on() {
		return is_front_defroster_on;
	}

	public void setIs_front_defroster_on(Boolean is_front_defroster_on) {
		this.is_front_defroster_on = is_front_defroster_on;
	}

	public Boolean getIs_rear_defroster_on() {
		return is_rear_defroster_on;
	}

	public void setIs_rear_defroster_on(Boolean is_rear_defroster_on) {
		this.is_rear_defroster_on = is_rear_defroster_on;
	}

	public int getFan_status() {
		return fan_status;
	}

	public void setFan_status(int fan_status) {
		this.fan_status = fan_status;
	}

	public ClimateSettingsParamResponse (){}

	public ClimateSettingsParamResponse (double insideTemp, double outsideTemp, double driverTempSetting, double passengerTempSetting,
			Boolean isAutoConditioningOn, Boolean isFrontDefrosterOn, Boolean isRearDefrosterOn, int fanStatus){

		inside_temp=insideTemp;		
		outside_temp=outsideTemp;		
		driver_temp_setting=driverTempSetting;		
		passenger_temp_setting=passengerTempSetting;		
		is_auto_conditioning_on=isAutoConditioningOn;		
		is_front_defroster_on=isFrontDefrosterOn;	
		is_rear_defroster_on=isRearDefrosterOn;		
		fan_status=fanStatus;

	}

}