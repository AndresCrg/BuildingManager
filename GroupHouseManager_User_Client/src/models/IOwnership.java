package models;

public interface IOwnership {
	
	boolean canAddApartment();
	boolean canAddInvoice();
	boolean canAddUser();
	boolean canBePurchased();
	boolean setBePurchased(boolean isAvalible);
	String getTypeOwnership();
	String pathIcon();
	void setPathIcon(String path);
}