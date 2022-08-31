package models;

import java.awt.Color;

public interface IOwnership {
	
	boolean canAddApartment();
	boolean canAddInvoice();
	boolean canAddUser();
	boolean canBePurchased();
	boolean setBePurchased(boolean isAvalible);
	boolean canIPurchase();
	boolean isInvoice();
	int getValueInvoice();
	Color getColor();
	void setColor(Color newColor);
	String getTypeOwnership();
	String getLevelName();
	void setLevelName(String lvlName);
}