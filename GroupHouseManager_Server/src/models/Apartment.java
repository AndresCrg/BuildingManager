package models;

import java.awt.Color;

public class Apartment implements IOwnership{
	
	private static final String APARTMENT = "Apartment";
	private static final String NAME_LEVEL = "lvlTwo";
	private String lvlName;
	private boolean purchased;
	private Color color;
	
	public Apartment() {
		this.purchased = true;
		this.lvlName = NAME_LEVEL;
		this.color = Color.decode("#000000");
	}

	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return APARTMENT;
	}
	
	@Override
	public String getLevelName() {
		return lvlName;
	}

	@Override
	public boolean canAddInvoice() {
		return true;
	}

	@Override
	public boolean canAddUser() {
		return false;
	}

	@Override
	public boolean canBePurchased() {
		return purchased;
	}

	@Override
	public boolean canIPurchase() {
		return false;
	}

	@Override
	public boolean setBePurchased(boolean isAvalible) {
		this.purchased = isAvalible;
		return purchased;
	}

	@Override
	public void setLevelName(String lvlName) {
		this.lvlName = lvlName;
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