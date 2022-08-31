package network;

import models.NTree;

public interface IPresenter {
	
	void receivedNotificationLogIn(String message, boolean isAdmin, NTree nTreeClient);
	void receivedNtree(NTree nTree);
}