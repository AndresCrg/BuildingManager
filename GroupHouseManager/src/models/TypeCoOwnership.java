package models;

public enum TypeCoOwnership {
	
	GYM("Gym"), PARK("Park"), BBQ("BBQ"), HALL("Hall"),
	SWIMMING("Swimming");
	
	private String value;

	private TypeCoOwnership(String value) {
		this.value = value;
	}
	
	@Override
    public String toString() {
        return value;
    }
}