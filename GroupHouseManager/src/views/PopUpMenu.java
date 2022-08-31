package views;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import constants.Constant;
import presenters.Action;
import presenters.Presenter;

public class PopUpMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;

	public void popUpRootLeaf(Presenter presenter) {
		removeAll();
		JMenuItem itemAddHouse = new JMenuItem(Constant.ADD_HOUSE_TXT);
		itemAddHouse.setIcon(new ImageIcon(((new ImageIcon(getClass().getResource(Constant.ICON_PATH_ADD_HOUSE))).getImage()).getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
		itemAddHouse.setActionCommand(Action.ADD_HOUSE.toString());
		itemAddHouse.addActionListener(presenter);
		add(itemAddHouse);

		JMenuItem itemAddbuilding = new JMenuItem(Constant.ADD_BUILDING_TXT);
		itemAddbuilding.setIcon(new ImageIcon(((new ImageIcon(getClass().getResource(Constant.ICON_PATH_ADD_BUILDING))).getImage()).getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
		itemAddbuilding.setActionCommand(Action.ADD_BUILDING.toString());
		itemAddbuilding.addActionListener(presenter);
		add(itemAddbuilding);

		JMenu menuAddCoOwnership = new JMenu(Constant.ADD_CO_OWNERSHIP_TXT);
		JMenuItem itemAddGym = new JMenuItem(Constant.ADD_GYM_TXT);
		itemAddGym.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_GYM)));
		itemAddGym.setActionCommand(Action.GYM.toString());
		itemAddGym.addActionListener(presenter);
		menuAddCoOwnership.add(itemAddGym);

		JMenuItem itemAddPark = new JMenuItem(Constant.ADD_PARK_TXT);
		itemAddPark.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_PARK)));
		itemAddPark.setActionCommand(Action.PARK.toString());
		itemAddPark.addActionListener(presenter);
		menuAddCoOwnership.add(itemAddPark);

		JMenuItem itemAddBBQZone = new JMenuItem(Constant.ADD_BBQ_ZONE_TXT);
		itemAddBBQZone.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_BBQ)));
		itemAddBBQZone.setActionCommand(Action.BBQ.toString());
		itemAddBBQZone.addActionListener(presenter);
		menuAddCoOwnership.add(itemAddBBQZone);

		JMenuItem itemAddCommunityHall = new JMenuItem(Constant.ADD_COMMUNITY_HALL_TXT);
		itemAddCommunityHall.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_HALL)));
		itemAddCommunityHall.setActionCommand(Action.HALL.toString());
		itemAddCommunityHall.addActionListener(presenter);
		menuAddCoOwnership.add(itemAddCommunityHall);

		JMenuItem itemAddSwimmingPool = new JMenuItem(Constant.ADD_SWIMMING_POOL_TXT);
		itemAddSwimmingPool.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_SWIMMING)));
		itemAddSwimmingPool.setActionCommand(Action.SWIMMING.toString());
		itemAddSwimmingPool.addActionListener(presenter);
		menuAddCoOwnership.add(itemAddSwimmingPool);
		add(menuAddCoOwnership);
	}

	public void popUpRootFull(Presenter presenter) {
		removeAll();
		popUpRootLeaf(presenter);
		remove(presenter);
	}

	public void popUpBuilding(Presenter presenter) {
		removeAll();
		JMenuItem itemAddAparment = new JMenuItem(Constant.ADD_APARTMENT_TXT);
		itemAddAparment.setActionCommand(Action.ADD_APARTMENT.toString());
		itemAddAparment.addActionListener(presenter);
		add(itemAddAparment);
		remove(presenter);
	}
	
	public void popUpPrivileges(Presenter presenter) {
		JMenuItem itemAddUser = new JMenuItem(Constant.ADD_USER_TXT);
		itemAddUser.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_USER)));
		itemAddUser.setActionCommand(Action.SHOW_DIALOG_ADD_USER.toString());
		itemAddUser.addActionListener(presenter);
		add(itemAddUser);
		remove(presenter);
	}
	
	public void popUpUserOwnership(Presenter presenter) {
		JMenuItem itemAddOwnership = new JMenuItem(Constant.ADD_OWNERSHIP_TXT);
		itemAddOwnership.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_TO_PURCHASE)));
		itemAddOwnership.setActionCommand(Action.SHOW_DIALOG_PURCHASE_OWNERSHIP.toString());
		itemAddOwnership.addActionListener(presenter);
		add(itemAddOwnership);
		remove(presenter);
	}

	public void remove(Presenter presenter) {
		JMenuItem itemDelete = new JMenuItem(Constant.REMOVE_TXT);
		itemDelete.setIcon(new ImageIcon(getClass().getResource(Constant.ICON_PATH_REMOVE)));
		itemDelete.setActionCommand(Action.REMOVE.toString());
		itemDelete.addActionListener(presenter);
		add(itemDelete);
	}
}