package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

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
	
	public void printBreadth(){
		Queue<MyNode> queue = new LinkedList<>();
		queue.add(root);
		printBreadth(root, queue);
		while (!queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}

	private void printBreadth(MyNode base, Queue<MyNode> queue) {
		for (MyNode child : base.getChildrenList()) {
			queue.add(child);
		}
		for (MyNode child : base.getChildrenList()) {
			printBreadth(child, queue);
		}
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
	
	public MyNode searchByType(String name) {
		return searchByType(name, root);
	}

	private MyNode searchByType(String name, MyNode current) {
		if (current.getData().getTypeOwnership().equals(name)) {
			return current;
		}
		for (MyNode node : current.getChildrenList()) {
			MyNode result = searchByType(name, node);
			if(result != null){
				return result;
			} 
		}
		return null;
	}
	
	public NTree getSubTree(String type) {
		MyNode rootNode = searchByType(type);
		NTree subNTree = new NTree(rootNode);
		return subNTree;
	}

	public MyNode getRoot() {
		return root;
	}
	
	public static void main(String[] args) {
		try {
			MyNode root = new MyNode(new ResidentialCommunity());
			NTree tree = new NTree(root);
			tree.add(root.getId(), new MyNode(new House()));
			tree.add(root.getId(), new MyNode(new House()));
			tree.add(root.getId(), new MyNode(new House()));
			tree.add(root.getId(), new MyNode(new House()));
			MyNode building = new MyNode(new Building());
			tree.add(root.getId(), building);
			tree.add(building.getId(), new MyNode(new Apartment()));
			MyNode nodeApartment = new MyNode(new Apartment());
			tree.add(building.getId(), nodeApartment);
			tree.add(nodeApartment.getId(), new MyNode(new Bill(TypeBill.INTERNET)));
			tree.add(nodeApartment.getId(), new MyNode(new Bill(TypeBill.LIGHT)));
			tree.add(nodeApartment.getId(), new MyNode(new Bill(TypeBill.WATER)));
			tree.add(nodeApartment.getId(), new MyNode(new Bill(TypeBill.GAS)));
			tree.add(building.getId(), new MyNode(new Apartment()));
			tree.add(building.getId(), new MyNode(new Apartment()));
			tree.add(building.getId(), new MyNode(new Apartment()));
			tree.add(root.getId(), new MyNode(new CoOwnership(TypeCoOwnership.BBQ)));
			tree.printBreadth();
			System.out.println("***************************");
			tree.print();
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
}