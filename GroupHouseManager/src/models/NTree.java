package models;

import java.util.ArrayList;
import java.util.Iterator;
import constants.Constant;

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

	public void getDebt(int firstValueFilter, int secondValueFilter) {
		getDebt(root, firstValueFilter, secondValueFilter);
	}

	private void getDebt(MyNode node, int firstValueFilter, int secondValueFilter) {
		if (node.getData().getTypeOwnership().equals("House")) {
			int total = subTotal(node);
			if (total > firstValueFilter && total < secondValueFilter) {
				node.getData().setColor(Constant.MY_COLOR_RED);
			}
		}
		for (MyNode current : node.getChildrenList()) {
			getDebt(current, firstValueFilter, secondValueFilter);
		}
	}

	private int subTotal(MyNode node) {
		int subTotal = 0;
		if (node.getData().isInvoice()) {
			subTotal = node.getData().getValueInvoice();
		}
		for (MyNode child : node.getChildrenList()) {
			subTotal += subTotal(child);
		}
		return subTotal;
	}

	public MyNode getRoot() {
		return root;
	}

	public static void main(String[] args) {
		//		try {
		//			Node root = new Node(new ResidentialCommunity());
		//			NTree tree = new NTree(root);
		//			tree.add(root.getId(), new Node(new House()));
		//			tree.add(root.getId(), new Node(new House()));
		//			tree.add(root.getId(), new Node(new House()));
		//			tree.add(root.getId(), new Node(new House()));
		//			System.out.println(tree.getNodesAvaliblesForPurchase());
		//		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
		//				| TransformerException e) {
		//			e.printStackTrace();
		//		}
	}
}