
public class HappyNumberFinder 
{	
	/**
	 * Checks all the happy numbers from 1-10,000.
	 * Prints all the happy numbers from 9,001-10,000.
	 * Checks user specified input of positive integer to be a happy number.
	 * The linked List should only work with integers because we are not testing decimal numbers
	 * in this assignment.
	 */
	
	private SingleLinkedList<Integer> sumList; 			// Linked List used to store all the sums of a number
	private SingleLinkedList<Integer> AllHappyNumbers;	// happy numbers from 1-10,000
	private SingleLinkedList<Integer> HappyNumbers9k;	// happy numbers from 9,001-10,000
	
	private String number;								// set to hold the value of the number being tested
														// used to keep track of the original number
	
	public HappyNumberFinder()
	{
		sumList = new SingleLinkedList<Integer>();
		AllHappyNumbers = new SingleLinkedList<Integer>();
		HappyNumbers9k = new SingleLinkedList<Integer>();	
	}
	/**
	 * checkNumber will check any string input.
	 * Returns true if the input is a number and returns false if the input is not a number.
	 * @param str
	 * @return
	 */
	private boolean checkNumber(String str) 
	{
		if(str == null)
		{
			return false;	// handles null
		}
		int length = str.length();
		for(int x = 0;x<length;x++) 						// checks every char in the string 
		{
			if(!Character.isDigit(str.charAt(x)))			// if char is not a digit will return false
			{
				return false;
			}
		}
		return true; // otherwise will return true meaning it is a number (every character is a number)
	}
	/**
	 * Takes input as a string under the assumption the input is a number represented as a string.
	 * Tests whether the input is a happy number. Since the input is a string the number can be longer
	 * than the range of a double.
	 * @param str
	 */
	private void RealHappyMath(String str)
	{
		String test = str; 								// sets new  variable  to copy the string
		boolean isLoop = false;							// if true this means the original number was found to loop
		
			
			int length = test.length();
			Integer sum = 0;
			for(int x = 0; x<length;x++)				// for loop that finds the square of each sum individually
														// and adds that sum together to find the some of the input
														// using the happy number method
			{
				int z = Character.getNumericValue(test.charAt(x));  // gets the number of one char in the string
				sum += (z*z);										// squares the number and adds it to the sum
			}
			isLoop = (this.compareInt(sum,sumList)); // if sum is found in the linked list isLoop will be true
			sumList.addEnd(sum);					  // number is added to the linked list to be checked again with the
												  // new number
			
			test = new String(Integer.toString(sum)); // sets test to be the sum which will then be tested again
													  // if it is not 1 or is not found in the linked list
			
		
		
		if(test.equals("1"))  // input ended up being a happy number
		{
				System.out.println(number + " is a happy number");  // will the the user the information
		}
		else if(!isLoop)  		  // if the sum of the squares of each digit of the input was not found in the linked list
			RealHappyMath(test);  // meaning the loop wasn't found it will run the test again with the new input
		else
			System.out.println(number + " is not a happy number");  // test string does not equal 1 and was found 
																	// repeating in the linked list will not be a happy number
		
	}
	
	/**
	 *  This method is similar to the HappyMath() method but it tests every value 
	 *  from 1-10k instead of taking inputs.
	 */
	public SingleLinkedList<Integer> HappyMath10k() 
	{
		for(int x=1;x<=10000;x++)
		{
			sumList= new SingleLinkedList<Integer>();  // resets the linked list member so it can be used again
													    // Takes each number 1-10,000
			Integer n = x;							    // Converts to Integer
			number = (n.toString());				    // Converts to string and stores it as member variable
			HappyMath10k(number);					    // Calls HappyMath10k with string as input
		}
		return HappyNumbers9k;
	}
	
	
	private void HappyMath10k(String str) 
	{
		boolean happy = false;       		// happy tells us whether or not it is a happy number
											// if it is a happy number happy will be true
		int length = str.length();			// find length of the string which is used for the for loop
		Integer sum = 0;					// sets sum to 0 so every time the method is called it will start at 0
		
		for(int x = 0; x<length;x++) 		// for loop that does the calculation
			{
				int z = Character.getNumericValue(str.charAt(x));   // sets the character at certain length to int
				sum += (z*z);										// squares the the character and adds it to the sum
			}
		happy = (this.compareInt(sum,sumList)); // this method compares the sum of the digits  squared of the number
											// that went through the for loop to all the other sums stored in it
		sumList.addEnd(sum);					// adds the sum to the list
		String test = new String(Integer.toString(sum)); // sets the test string to be the sum
		
		if(test.equals("1"))
		{
			AllHappyNumbers.addEnd(Integer.valueOf(number)); // store the value of the happy number to a linked list
			if(Integer.valueOf(number)>=9001)
				HappyNumbers9k.addEnd(Integer.valueOf(number)); // store the value of the happy number greater than 9001
																// to a singly linked list which was asked for in the
																// assignment
		}
		else if(!happy)
			HappyMath10k(test); // uses the test string to be tested again
		else
		{}                  	// method ends if it reaches here which means the sum wasn't equal to one
								// and the sum was found in the linked list which means it will loop forever
								// when recursion is used to do the calculation, so the else statement means 
								// it is not a happy number
		
		
	}
	/**
	 * Takes input and checks the if the string is number.
	 * If true it will run the method and return true if false it will just return false.
	 * @param str
	 */
	public boolean HappyMath(String str)
	{
		boolean isNumber  = checkNumber(str);   // checks number to make sure it is a number
												// this is how this method avoids running into errors
												// because it can not handle any string that is more than numbers
		
		if(isNumber) 				// if string is a number
		{
			number = str;  			// sets member variable to the input
			RealHappyMath(str); 	// calls RealHappyMath which does the calculation and determines if it is a 
									// happy number
			return true;			// if success returns true
		}
		else
			return false;  // returns false if calculation was not done which means the input was not a number
	}
	/**
	 * 
	 * @param num, list
	 * @return boolean
	 * Requires a number and a linked list. Will search for that number through the linked list
	 * to check if it is in the linked list.
	 */
	private boolean compareInt(Integer num,SingleLinkedList<Integer> list) 
	{

		if(list.getSize() == 0)
		{
			return false; // no numbers to check if list is 0
		}
		else
		{
			SingleLinkedList.Node<Integer> node = list.getHead(); // gets the first node in the linked list
			for(int x=1;x<=list.getSize();x++)
			{
				if(num.equals(node.getElement()))
				{
					return true;
					// number being searched for in the linked list was found
				}
				else if(node.getNext() == list.getTail())
				{
					return false;
					// reached end of linked list without finding same variable
				}
				else
				{
					node= node.getNext();
					// if node is not the last and there hasn't been the same element found
					// node is now set to the next and the loop runs again
				}
			}
		}
		return false; // default 
						// this return should never be used but if it is it will be false
		
	}
	public SingleLinkedList<Integer> getHappyNumbers()
	{
		return AllHappyNumbers;  // returns list of all happy numbers from 1-10,000
	}
	
}
