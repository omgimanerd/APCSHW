public class Demo1 {
  public static void main(String[] args) {
    String s1 = "Tim";
    String s2 = "Jim";
    if (s1.equals(s2)){ 
	    System.out.println("the strings are equal"); 
    }else{
	    System.out.println("the strings are not equal"); 
    }

    //What would we do if we wanted to determine which string 
    //comes first alphabetically?

    //s1 < s2 will only compare the addresses and isn't helpful!

    //To compare objects in Java we use the Comparable interface. 
    //Classes that implement this interface must have a compareTo 
    //method that is used to compare two objects to see if one is 
    //less than, equal to, or greater than the other. 

    //Test things here, to determine what compare to returns:
    //UNCOMMENT THE NEXT LINE TO TEST compareTo()
    System.out.println("ayellowsubmarine".compareTo("Zeetles"));

    //For two strings variables s1 and s2, s1.compareTo(s2) returns:
    //an integer value: (students must explain...)
    //When s1 < s2              compareTo returns:
    //When s1 is the same as s2 compareTo returns:
    //When s1 > s2              compareTo returns:

	
    //Now we have a way to compare objects, and a way to write sorts
    //on objects (only comparable objects)
  }
}
