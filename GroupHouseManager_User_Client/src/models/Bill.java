package models;

public class Bill implements IOwnership{
	
	private TypeBill typeBill;
	private String pathIcon;
	
	public Bill(TypeBill typeBill) {
		this.typeBill = typeBill;
		pathIcon = choosePathIcon(typeBill);
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
	public boolean setBePurchased(boolean isAvalible) {
		return false;
	}

	@Override
	public String pathIcon() {
		return pathIcon;
	}

	@Override
	public void setPathIcon(String path) {
		this.pathIcon = path;
	}
	
	private String choosePathIcon(TypeBill typeBill) {
		String result = "";
		switch (typeBill) {
		case GAS:
			result = "/imgs/fire.png";
			break;
		case INTERNET:
			result = "/imgs/modem.png";
			break;
		case WATER:
			result = "/imgs/faucet.png";
			break;
		case LIGHT:
			result = "/imgs/light-bulb.png";
			break;
		default:
			break;
		}
		return result;
	}
}