public class PersonalGreeter {
    
    private String greeting_;

    private String name_;

    public PersonalGreeter() {
        this.setName("Bob");
        this.setGreeting("Hello");
    }

    public PersonalGreeter(String name) {
        this.setName(name);
    }

    public PersonalGreeter(String name, String greeting) {
        this.setName(name);
        this.setGreeting(greeting);
    }

    public void greet() {
        System.out.println(this.greeting_ + " " + this.name_);
    }

    public void setGreeting(String greeting) {
        this.greeting_ = greeting;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public String getGreeting() {
        return this.greeting_;
    }

    public String getName() {
        return this.name_;
    }
}
