public class Puppy {
    private String name;
    private int age;

    // Constructor
    public Puppy(String name) {
        this.name = name;
        System.out.println("Puppy " + name + " is born.");
    }

    // Setter method for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Main
    public static void main(String[] args) {
        Puppy myPuppy = new Puppy("Tommy");

        // Set age using setter method
        myPuppy.setAge(3);

        // Get age using getter method
        int age = myPuppy.getAge();
        System.out.println("Puppy " + myPuppy.getName() + " is " + age + " years old.");

        // directly access age variable
        System.out.println("Puppy " + myPuppy.getName() + " is " + myPuppy.age + " years old.");
    }

}
