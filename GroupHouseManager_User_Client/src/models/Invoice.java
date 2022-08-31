package models;

public class Invoice implements IOwnership{
	
	private static final String PATH_ICON = "/imgs/invoice.png";
	private String date;
	private int value;
	
	public Invoice(String date, int value) {
		this.date = date;
		this.value = value;
	}

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
		return date + " - " + value;
	}

	public String getDate() {
		return date;
	}

	public int getValue() {
		return value;
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
		
	}
}