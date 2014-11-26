import java.util.*;

public class Demo3{

    //When you define a new class, consider if it makes sense for 
    //instances of your class to be compared to do things such as 
    //sorting. If so, your class should implement the Comparable<T> 
    //interface. To do this you need to:

    //a. Include the clause implements Comparable<T> in the class header 
    //right after the class name replacing T with the class's name.

    //b. Implement a compareTo method having the signature:
    //int compareTo(T other)    where T is the type parameter.


    //1. Copy your Adventurer + Warrior classes into this directory. 

    //2. Try to compile the code below before you make Warrior Comparable.

    //3. Modify the Warrior so that it is comparable as per directions above.
    //dont forget:
    //   implements Comparable<Warrior>
    //   public int compareTo(Warrior other)

    //4 about compareTo():
    //How can we compare warriors? What determines less than or greater?
    //We must pick one measure of a warriors greatness.
    //To make it simple, More HP means the warrior is greater.

    //5. Test to see if the sorting now works with Warriors.
    //How do you know it worked? 
    //How can you verify this?
    //Your toString in Warrior doesn't help us, what can you do?

    public static void main(String[]args){
	Warrior[]people;

	people = new Warrior[15];
	for(int i = 0; i < people.length; i++){
	    people[i] = new Warrior(""+(char)('A'+i)+"onan");
	}

	System.out.println(Arrays.toString(people));
	Arrays.sort(people);
	System.out.println(Arrays.toString(people));


    }

}
