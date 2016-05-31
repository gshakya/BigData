package W2D1.Question3;

import java.util.ArrayList;

public  class Mapper<V> {
	private ArrayList<V> elements;
	
	public Mapper(){
		elements = new ArrayList<V>();
	}
	
	public void addElement(V element){
		elements.add(element);
	}
	
	public ArrayList<V> getElements(){
		return elements;
	}
	
}
