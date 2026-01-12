package buoi7.demo1;

public class Object {

    public void makeSound() {
        System.out.println("Object makes a sound");
    }

    public static void main(String[] args) {
        Object object = new Person();
        object.makeSound();
        object = new Dog();
        object.makeSound();
        object = new Cat();
        object.makeSound();
        object = new Duck();
        object.makeSound();


    }
}
