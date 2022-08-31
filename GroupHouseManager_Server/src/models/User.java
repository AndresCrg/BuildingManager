package models;

import java.awt.Color;

public class User implements IOwnership{
	
	private static final String NAME_LEVEL = "lvlFour";
	private String name;
	private Color color;
	
	public User(String name) {
		this.name = name;
		this.color = Color.decode("#000000");
	}
	
	public User() {}

	@Override
	public boolean canAddApartment() {
		return true;
	}

	@Override
	public boolean canAddInvoice() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return name;
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
		return true;
	}
	
	@Override
	public boolean setBePurchased(boolean isAvalible) {
		return false;
	}

	@Override
	public String getLevelName() {
		return NAME_LEVEL;
	}

	@Override
	public void setLevelName(String lvlName) {
		
	}

	@Override
	public boolean isInvoice() {
		return false;
	}

	@Override
	public int getValueInvoice() {
		return 0;
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