package buoi2;

public class Person {

    private String name;

    public void displayInfo(){
        System.out.println("Name: " + name);
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    // getter và setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
