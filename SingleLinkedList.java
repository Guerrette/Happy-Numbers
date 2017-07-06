/*
 *  Creates methods that effectively work well with the Node class and basically stores connected nodes 
 *  You can't remove nodes form the list because the use of this method was not needed, but it can be done
 */
public class SingleLinkedList<E> 
{
	/**
	 * Node class holds an element and a pointer to another element
	 *
	 * @param <E>
	 */
	public static class Node<E>   // nested node class
	{
		private E Element;
		private Node<E> Next;
		
		public Node(E element, Node<E> next) // sets all the member variable
		{
			Next = next;
			Element = element;
		}
		public Node<E> getNext()
		{
			return Next; // return the next node of the node called on
						 // will return null if it is the tail
		}
		
		public void setNext( Node<E> next) // sets the next variable of the node
		{
			Next = next;
		}
		public E getElement()   // gets the value of the node
		{
			return Element;
		}
		public String toString() // this assumes that the element of the node has an effective toString method
		{
			return this.getElement().toString();
		}
	}
	// start of linked list class
	private Node<E> Head;
	private Node<E> Tail;
	private int Size = 0; // initial size is 0 because the linked list starts with no nodes connected
	
	public SingleLinkedList()
	{		
		// linked list starts off with no nodes
		// the head node will have a value as well as the tail node
	}
	public int getSize() // returns the size of the linked list
	{
		return Size;
	}
	public Node<E> getHead() // returns the first node of the linked list
	{
		return Head;    	 // will return null if there is no head
	}
	public Node<E> getTail()  // Returns the last node of the linked list
	{
		return Tail;          // will return null if there is no tail
	}
	
	/**
	 * Adds the element to the start of the linked list making it the new head
	 * @param element
	 */
	public void addStart(E element)  
	{
		Node<E> newHead = new Node<E>(element,Head);  // creates new node with the the element and sets the next
													  // node to be the old head
		Head = newHead;								  // member variable head is now the new head
		if(Size == 0) 								  // if the size is 0
			Tail = Head;							  // Tail is also the same as head which means
													  // the head points to null
 		Size++;										  // increase size by one since new node was added						
	}
	
	
	/**
	 * adds a new value to the end of the linked list
	 * @param element
	 */
	public void addEnd(E element)
	{
		Node<E> newTail = new Node<E>(element,null);	// creates a new node and points it to null since it
														// is the new tail
		if(Size == 0)									// if size = 0
		{
			Head = newTail;								// The head is also the tail if it is the first node added
		}
		else
		{
			Tail.setNext(newTail);						// if size isn't 0 it will set the old tail to the new tail
		}
		Tail = newTail;									// sets the member variable to the new tail
		Size++;											// increase size by 1
	}
	
	
	/*
	 * Overrides toString from Object
	 * Will return the string of the elements in the linked list in a set of three elements per line
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		if(Size == 0)
		{
			return null; // if size is 0 returns null because there are no elements in the list
		}
		else  // if size isn't 0
		{
			Node<E> node = this.getHead();   // gets the first node 
			String str = "";				 // sets the string
			for(int x=0;x<Size;x++)          // for loop will run from the first node to the last
			{
				if(x%3 == 0)  // if the element is the a multiple of three in terms of position
				{
					 
					str += ( System.getProperty("line.separator")+ node.toString() + " "); // string will create a new line
																						   // and contain the value of the node
					node = node.getNext();	//gets the next node											   
				}
				else // if the position is not a multiple of 3
				{
					str += (node.toString()+" "); // adds the value to the string and a space
					node = node.getNext();		  // gets the next node 
				}
			}
			return str;  // Returns final string after the for loop
		}
			
	}
}

