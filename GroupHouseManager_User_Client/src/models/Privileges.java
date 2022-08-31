package models;

public class Privileges implements IOwnership{
	
	private static final String PRIVILEGES_TXT = "Privileges";

	@Override
	public boolean canAddApartment() {
		return false;
	}

	@Override
	public boolean canAddInvoice() {
		return false;
	}

	@Override
	public String getTypeOwnership() {
		return PRIVILEGES_TXT;
	}

	@Override
	public boolean canAddUser() {
		return true;
	}

	@Override
	public boolean canBePurchased() {
		return false;
	}
	
	@Override
	public boolean setBePurchased(boolean isAvalible) {
		return false;
	}

	@Override
	public String pathIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPathIcon(String path) {
		// TODO Auto-generated method stub
		
	}
}