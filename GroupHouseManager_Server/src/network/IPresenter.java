package network;

public interface IPresenter {
	
	void addHouse(int idFather);
	void addBuilding(int idFather);
	void addApartment(int idFather);
	void addCoOwnership(int idFather, String coOwnership);
	void addUser(int idFather, String userName);
	void remove(int idNode);
	void assignOwnershipToClient(int idNode, int idFather);
	void createSubNTreeClient(String nameClient);
	void createInvoice(int idFather, String userName, String typeInvoice, String date, int value);
}
