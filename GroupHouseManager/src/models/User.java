package models;

import java.awt.Color;

import constants.Constant;

public class User implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/userTree.png";
	private String name;
	private Color color;
	
	public User(String name) {
		this.name = name;
		this.color = Constant.MY_COLOR_BLACK;
	}

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