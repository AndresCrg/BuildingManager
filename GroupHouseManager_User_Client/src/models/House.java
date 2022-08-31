package models;

public class House implements IOwnership{

	private static final String PATH_ICOM = "/imgs/house.png";
	private static final String HOUSE = "House";
	private boolean purchased;
	
	public House() {
		this.purchased = true;
	}

	@Override
	public boolean canBePurchased() {
		return purchased;
	}
	
	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return HOUSE;
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
	public boolean setBePurchased(boolean isAvalible) {
		purchased = isAvalible;
		return purchased;
	}

	@Override
	public String pathIcon() {
		return PATH_ICOM;
	}

	@Override
	public void setPathIcon(String path) {
		// TODO Auto-generated method stub
		
	}
}