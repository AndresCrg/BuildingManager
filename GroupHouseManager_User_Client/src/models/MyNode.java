package models;

import java.util.ArrayList;
import java.util.Iterator;

public class MyNode {
	
	private int id;
	private IOwnership data;
	private MyNode father;
	private ArrayList<MyNode> childrenList;
	
	public MyNode(int id, IOwnership data) {
		this.id = id;
		this.data = data;
		this.childrenList = new ArrayList<MyNode>();
	}
	
	public MyNode(IOwnership data)  {
		this.id = 0;
		this.data = data;
		this.childrenList = new ArrayList<MyNode>();
	}
	
	public void add(MyNode newChild) {
		childrenList.add(newChild);
		newChild.setFather(this);
	}
	
	public void remove(MyNode child) {
		Iterator<MyNode> iterator = getChildrenList().iterator();
		while(iterator.hasNext()) {
			if (iterator.next().getId() == child.getId()) {
				iterator.remove();
				return;
			}
		}
	}
	
	public MyNode getFirstChild() {
		return childrenList.get(0);
	}
	
	public MyNode getLastChild() {
		return childrenList.get(getChildrenList().size() - 1);
	}

	public IOwnership getData() {
		return data;
	}

	public void setData(IOwnership data) {
		this.data = data;
	}

	public MyNode getFather() {
		return father;
	}

	public void setFather(MyNode father) {
		this.father = father;
	}

	public int getId() {
		return id;
	}
	
	public boolean isLeaf() {
		return childrenList.size() == 0;
	}

	public ArrayList<MyNode> getChildrenList() {
		return childrenList;
	}
	
    @Override
    public String toString() {
        return "Node {" + "id = " + getId() + " - Type = " + (data == null ? "Conjunto": data.getTypeOwnership()) + " }";
    }
}