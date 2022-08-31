package models;

import java.awt.Color;

public class CoOwnership implements IOwnership{
	
	private static final String NAME_LEVEL = "lvlOne";
	private TypeCoOwnership type;
	private Color color;
	
	public CoOwnership(TypeCoOwnership type) {
		this.type = type;
		this.color = Color.decode("#000000");
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