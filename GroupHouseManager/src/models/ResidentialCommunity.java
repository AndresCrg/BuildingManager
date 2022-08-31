package models;

import java.awt.Color;

import constants.Constant;

public class ResidentialCommunity implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/residentialCommunity.png";
	private static final String RESIDENCIAL_COMMUNITY = "Residential Community";
	private Color color;
		
	public ResidentialCommunity() {
		this.color = Constant.MY_COLOR_BLACK;
	}
	
	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return RESIDENCIAL_COMMUNITY;
	}

	@Override
	public boolean canAddInvoice() {
		return false;
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