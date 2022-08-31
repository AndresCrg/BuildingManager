package models;

public enum TypeBill {

	WATER("Water"), GAS("Gas"), INTERNET("Internet"), LIGHT("Light");
	
	private String value;

	private TypeBill(String value) {
		this.value = value;
	}

	@Override
    public String toString() {
        return value;
    }
}