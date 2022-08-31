package models;

import java.awt.Color;

public class House implements IOwnership{

	private static final String HOUSE = "House";
	private static final String NAME_LEVEL = "lvlOne";
	private boolean purchased;
	private Color color;
	
	public House() {
		this.purchased = true;
		this.color = Color.decode("#000000");
	}

	@Override
	public boolean canBePurchased() {
		return purchased;
	}
	
	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return HOUSE;
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
	public boolean canIPurchase() {
		return false;
	}

	@Override
	public boolean setBePurchased(boolean isAvalible) {
		purchased = isAvalible;
		return purchased;
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