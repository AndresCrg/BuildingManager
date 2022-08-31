package models;

import java.awt.Color;

public class Privileges implements IOwnership{
	
	private static final String PRIVILEGES_TXT = "Privileges";
	private Color color;
	
	public Privileges() {
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
		return PRIVILEGES_TXT;
	}

	@Override
	public boolean canAddUser() {
		return true;
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
		return null;
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