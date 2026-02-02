package buoi11.ex1;

import java.util.List;
import java.util.Optional;

/**
 E: thể hiện kiểu dữ liệu của danh sách các phần tử
 */
public interface CustomList<E> {

    Optional<E> get(int index);

    void add(E element);

    Optional<List<E>> getAll();

    boolean remove(E element);

    void showAll();
}
