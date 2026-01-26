package buoi10;

import java.util.IllegalFormatCodePointException;

public class Person {

    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return this.id == other.id &&
               this.name.equals(other.name) &&
               this.age == other.age;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
