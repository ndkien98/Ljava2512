package buoi2;

public class Person {

    private String name;

    public void displayInfo(){
        System.out.println("Name: " + name);
    }

    // getter và setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
