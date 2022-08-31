package network;

import models.NTree;

public interface IPresenter {
	
	void receivedNotificationLogIn(String message, boolean isAdmin, NTree nTreeOwnership, NTree nTreePrivileges);
	void receivedNtreeOwnership(NTree nTree);
	void receivedNtreePrivileges(NTree nTree);
	void receivedMessageUserIsAlready(String readUTF);
	void receivedNotificationAdminNotFound(String message, boolean isAdmin);
}
