package models;

import java.awt.Color;

public class Bill implements IOwnership{
	
	private static final String NAME_LEVEL = "lvlFive";
	private TypeBill typeBill;
	private Color color;
	
	
	public Bill(TypeBill typeBill) {
		this.typeBill = typeBill;
		this.color = Color.decode("#000000");
	}

	public TypeBill getTypeBill() {
		return typeBill;
	}

	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public boolean canAddInvoice() {
		return true;
	}

	@Override
	public String getTypeOwnership() {
		return typeBill.toString();
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
		// TODO Auto-generated method stub
		
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