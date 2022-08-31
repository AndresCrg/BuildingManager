package models;

public class Apartment implements IOwnership{
	
	private static final String APARTMENT = "Apartment";
	private static final String PATH_ICON = "/imgs/apartment.png";
	private boolean purchased;
	
	public Apartment() {
		this.purchased = true;
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
		return false;
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

}
