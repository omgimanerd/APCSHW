public class demo {
    public static void main(String[] args) {
        PersonalGreeter greeter = new PersonalGreeter();
        greeter.greet();
        greeter.setName("Alvin");
        greeter.setGreeting("Salutations");
        greeter.greet();
    }
}
