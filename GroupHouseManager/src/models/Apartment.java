package models;

import java.awt.Color;

import constants.Constant;

public class Apartment implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/apartment.png";
	private static final String APARTMENT = "Apartment";
	private boolean purchased;
	private Color color;
	
	public Apartment() {
		this.purchased = true;
		this.color = Constant.MY_COLOR_BLACK;
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
	public String pathIcon() {
		return PATH_ICON;
	}

	@Override
	public void setPathIcon(String path) {
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