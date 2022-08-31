package models;

import java.awt.Color;

import constants.Constant;

public class Invoice implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/bill.png";
	private String date;
	private int value;
	private Color color;
	
	public Invoice(String data) {
		this.color = Constant.MY_COLOR_BLACK;
		this.date = splitInfo(data)[0];
		this.value = Integer.parseInt(splitInfo(data)[1]);
	}
	
	public String [] splitInfo(String info) {
		return info.split("-");
	}

	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public boolean canAddInvoice() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return date + "-" + value;
	}

	public String getDate() {
		return date;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean canAddUser() {
		return false;
	}

	@Override
	public boolean canBePurchased() {
		return false;
	}
	
	@Override
	public boolean canIPurchase() {
		return false;
	}
	
	@Override
	public boolean setBePurchased(boolean isAvalible) {
		return false;
	}

	@Override
	public String pathIcon() {
		return PATH_ICON;
	}

	@Override
	public void setPathIcon(String path) {}

	@Override
	public boolean isInvoice() {
		return true;
	}

	@Override
	public int getValueInvoice() {
		return value;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color newColor) {
		this.color = newColor;
	}
}