public class greeter {
    
    private String greeting;
    private int counter;

    public greeter() {
        greeting = "Hello";
        counter = 0;
    }

    public greeter(String args) {
        greeting = args;
        counter = 0;
    }

    public void greeter() {
        System.out.println(greeting);
        System.out.println(counter);
        counter++;
    }

    public void setCounter(int num) {
        counter = num;
    }
}
