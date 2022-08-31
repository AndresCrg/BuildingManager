package models;

import java.util.ArrayList;
import java.util.Iterator;

public class NTree {

	private MyNode root;

	public NTree(MyNode root) {
		this.root = root;
	}

	public boolean add(int idFather, MyNode newNode) {
		MyNode father = search(idFather);
		if (father != null) {
			father.add(newNode);
			return true;
		}
		return false;
	}

	public MyNode search(int id) {
		return search(id, root);
	}

	private MyNode search(int id, MyNode current) {
		if (current.getId() == id) {
			return current;
		}
		for (MyNode child : current.getChildrenList()) {
			MyNode result = search(id, child);
			if(result != null){
				return result;
			} 
		}
		return null;
	}

	public void remove(int id){
		remove(id, root);
	}

	private boolean remove(int id, MyNode actual) {
		if(actual.getId() == id){
			actual.getFather().remove(actual);
			return true;
		}
		Iterator<MyNode> iterator = actual.getChildrenList().iterator();
		while (iterator.hasNext()){
			if(remove(id, iterator.next())){
				return true;
			}
		}
		return false;
	}

	public void print() {
		print(root, "");
	}

	private void print(MyNode actual, String space) {
		System.out.println(space + actual);
		space += "  ";
		for (MyNode child : actual.getChildrenList()) {
			print(child, space);
		}
	}

	public ArrayList<MyNode> getNodesAvaliblesForPurchase(){
		ArrayList<MyNode> nodes = new ArrayList<MyNode>();
		getNodesAvaliblesForPurchase(nodes, root);
		return nodes;
	}

	private void getNodesAvaliblesForPurchase(ArrayList<MyNode> nodes, MyNode current) {
		if (current.getData().canBePurchased()) {
			nodes.add(current);
		}
		for (MyNode node : current.getChildrenList()) {
			getNodesAvaliblesForPurchase(nodes, node);
		}
	}

	public MyNode getRoot() {
		return root;
	}

	public static void main(String[] args) {

	}
}