package models;

public class User implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/userTree.png";
	private String name;
	
	public User(String name) {
		this.name = name;
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
	public boolean setBePurchased(boolean isAvalible) {
		return false;
	}

	@Override
	public String pathIcon() {
		return PATH_ICON;
	}

	@Override
	public void setPathIcon(String path) {
		// TODO Auto-generated method stub
		
	}
}