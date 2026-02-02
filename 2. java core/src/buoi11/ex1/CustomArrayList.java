package buoi11.ex1;

import buoi2.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomArrayList <E> implements CustomList<E> {

    private ArrayList<E> elements;

    public CustomArrayList() {
        this.elements = new ArrayList<>();
    }

    @Override
    public Optional<E> get(int index) {
        if (index < 0 || index >= this.elements.size()){
            return Optional.empty();
        }
        return Optional.ofNullable(elements.get(index));
    }

    @Override
    public void add(E element) {
        elements.add(element);
    }

    @Override
    public Optional<List<E>> getAll() {
        return Optional.of(elements);
    }

    @Override
    public boolean remove(E element) {
        return elements.remove(element);
    }

    @Override
    public void showAll() {
        for(E element : elements){
            if (element instanceof Person){
                ((Person) element).displayInfo();
                continue;
            }
            System.out.println(element);
        }
    }


}
