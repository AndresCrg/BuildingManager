package models;

import java.awt.Color;

public class Invoice implements IOwnership{
	
	private static final String LEVEL = "lvlSix";
	private String date;
	private int value;
	private Color color;
	
	public Invoice(String date, int value) {
		this.date = date;
		this.value = value;
		this.color = Color.decode("#000000");
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
	public String getLevelName() {
		return LEVEL;
	}

	@Override
	public void setLevelName(String lvlName) {}

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