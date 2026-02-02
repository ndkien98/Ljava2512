package buoi11.ex1;

import buoi2.Person;

public class Main {

    public static void main(String[] args) {

        CustomList objectList = new CustomArrayList<>();
        objectList.add(new Person("Charlie"));
        objectList.add(10);
        objectList.add("Hello");
        objectList.showAll();

        CustomList<Integer> integerList = new CustomArrayList<>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(30);
        integerList.showAll();
        CustomList<String> stringList = new CustomArrayList<>();
        stringList.add("Java");
        stringList.add("Python");
        stringList.add("C++");
        integerList.showAll();

        CustomList<Person> personList = new CustomArrayList<>();
        personList.add(new Person("Alice"));
        personList.add(new Person("Bob"));
        personList.showAll();




    }
}
