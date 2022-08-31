package models;

import java.awt.Color;

import constants.Constant;

public class CoOwnership implements IOwnership{

	private TypeCoOwnership type;
	private String pathIcon;
	private Color color;

	public CoOwnership(TypeCoOwnership type) {
		this.type = type;
		this.color = Constant.MY_COLOR_BLACK;
		this.pathIcon = choosePathIcon(type);
	}

	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return type.toString();
	}

	public TypeCoOwnership getType() {
		return type;
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
		return pathIcon;
	}

	private String choosePathIcon(TypeCoOwnership type) {
		String result = "";
		switch (type) {
		case GYM:
			result = "/imgs/dumbbell.png";
			break;
		case HALL:
			result = "/imgs/representative.png";
			break;
		case BBQ:
			result = "/imgs/barbecue.png";
			break;
		case PARK:
			result = "/imgs/childhood.png";
			break;
		case SWIMMING:
			result = "/imgs/swimming-pool.png";
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public void setPathIcon(String path) {
		this.pathIcon = path;
	}

	@Override
	public boolean isInvoice() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValueInvoice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setColor(Color newColor) {
		this.color = newColor;
	}
}