import java.util.Arrays;

public class Demo2 {
  public static void main(String[] args) {
    //lets look at how we can sort things with the built in sort:
    String[] words = { "fish","garbage","onions","Feet","sewage","apes"};

    //To use the built in sort we need to import java.util.Arrays

    //This gives us many useful array related methods:
    //toString
    //sort
    //equals
    //and more! 
    //These are things you must be able to write at this point.

    //Sorting requires that you call sort on an array:
    //Arrays.sort( <Array of Comparables> );
    //remember you can only sort comparable things

    System.out.println( Arrays.toString(words) );//print it!
    Arrays.sort(words); //and sort it.
    System.out.println( Arrays.toString(words) );//print it again!
  }

}
