package es.neodoo.knightrider.car.services.rest.params;

public class ChargeStateParamResponse {

	private String charging_state;

	private Boolean charge_to_max_range;

	private int max_range_charger_counter;

	private Boolean fast_charger_present;

	private double battery_range;

	private double est_battery_range;

	private double ideal_battery_range;

	private int battery_level;

	private double battery_current;

	private String charge_starting_range;

	private String charge_starting_soc;

	private int charger_voltage;

	private int charger_pilot_current;

	private int charger_actual_current;

	private int charger_power;

	private String time_to_full_charge;

	private double charge_rate;

	private Boolean charge_port_door_open;

	public String getCharging_state() {
		return charging_state;
	}

	public void setCharging_state(String charging_state) {
		this.charging_state = charging_state;
	}

	public Boolean getCharge_to_max_range() {
		return charge_to_max_range;
	}

	public void setCharge_to_max_range(Boolean charge_to_max_range) {
		this.charge_to_max_range = charge_to_max_range;
	}

	public int getMax_range_charge_counter() {
		return max_range_charger_counter;
	}

	public void setMax_range_charge_counter(int max_range_charge_counter) {
		this.max_range_charger_counter = max_range_charge_counter;
	}

	public Boolean getFast_charger_present() {
		return fast_charger_present;
	}

	public void setFast_charger_present(Boolean fast_charger_present) {
		this.fast_charger_present = fast_charger_present;
	}

	public double getBattery_range() {
		return battery_range;
	}

	public void setBattery_range(double battery_range) {
		this.battery_range = battery_range;
	}

	public double getEst_battery_range() {
		return est_battery_range;
	}

	public void setEst_battery_range(double est_battery_range) {
		this.est_battery_range = est_battery_range;
	}

	public double getIdeal_battery_range() {
		return ideal_battery_range;
	}

	public void setIdeal_battery_range(double ideal_battery_range) {
		this.ideal_battery_range = ideal_battery_range;
	}

	public int getBattery_level() {
		return battery_level;
	}

	public void setBattery_level(int battery_level) {
		this.battery_level = battery_level;
	}

	public double getBattery_current() {
		return battery_current;
	}

	public void setBattery_current(double battery_current) {
		this.battery_current = battery_current;
	}

	public String getCharge_starting_range() {
		return charge_starting_range;
	}

	public void setCharge_starting_range(String charge_starting_range) {
		this.charge_starting_range = charge_starting_range;
	}

	public String getCharge_starting_soc() {
		return charge_starting_soc;
	}

	public void setCharge_starting_soc(String charge_starting_soc) {
		this.charge_starting_soc = charge_starting_soc;
	}

	public int getCharger_voltage() {
		return charger_voltage;
	}

	public void setCharger_voltage(int charger_voltage) {
		this.charger_voltage = charger_voltage;
	}

	public int getCharger_pilot_current() {
		return charger_pilot_current;
	}

	public void setCharger_pilot_current(int charger_pilot_current) {
		this.charger_pilot_current = charger_pilot_current;
	}

	public int getCharger_actual_current() {
		return charger_actual_current;
	}

	public void setCharger_actual_current(int charger_actual_current) {
		this.charger_actual_current = charger_actual_current;
	}

	public int getCharger_power() {
		return charger_power;
	}

	public void setCharger_power(int charger_power) {
		this.charger_power = charger_power;
	}

	public double getCharge_rate() {
		return charge_rate;
	}

	public void setCharge_rate(double charge_rate) {
		this.charge_rate = charge_rate;
	}

	public Boolean getCharge_port_door_open() {
		return charge_port_door_open;
	}

	public void setCharge_port_door_open(Boolean charge_port_door_open) {
		this.charge_port_door_open = charge_port_door_open;
	}

	public String getTime_to_full_charge() {
		return time_to_full_charge;
	}

	public void setTime_to_full_charge(String time_to_full_charge) {
		this.time_to_full_charge = time_to_full_charge;
	}

	public ChargeStateParamResponse(){}

	public ChargeStateParamResponse(String charging_state, Boolean charge_to_max_range, int max_range_charger_counter,
			Boolean fast_charger_present, double battery_range, double est_battery_range, double ideal_battery_range,
			int battery_level, double battery_current, String charge_starting_range, String charge_starting_soc,
			int charger_voltage, int charger_pilot_current, int charger_actual_current, int charger_power,
			String time_to_full_charge, double charge_rate, Boolean charge_port_door_open) {

		this.charging_state = charging_state;
		this.charge_to_max_range = charge_to_max_range;
		this.max_range_charger_counter = max_range_charger_counter;
		this.fast_charger_present = fast_charger_present;
		this.battery_range = battery_range;
		this.est_battery_range = est_battery_range;
		this.ideal_battery_range = ideal_battery_range;
		this.battery_level = battery_level;
		this.battery_current = battery_current;
		this.charge_starting_range = charge_starting_range;
		this.charge_starting_soc = charge_starting_soc;
		this.charger_voltage = charger_voltage;
		this.charger_pilot_current = charger_pilot_current;
		this.charger_actual_current = charger_actual_current;
		this.charger_power = charger_power;
		this.time_to_full_charge = time_to_full_charge;
		this.charge_rate = charge_rate;
		this.charge_port_door_open = charge_port_door_open;
	
	}

}