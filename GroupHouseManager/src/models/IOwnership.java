package models;

import java.awt.Color;

public interface IOwnership {
	
	boolean canAddApartment();
	boolean canAddInvoice();
	boolean canAddUser();
	boolean canBePurchased();
	boolean setBePurchased(boolean isAvalible);
	boolean canIPurchase();
	String getTypeOwnership();
	String pathIcon();
	void setPathIcon(String path);
	boolean isInvoice();
	int getValueInvoice();
	Color getColor();
	void setColor(Color newColor);
}