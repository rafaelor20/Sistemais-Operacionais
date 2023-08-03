import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeArrayList<E> implements MyList<E> {
    private final List<E> list;

    public ThreadSafeArrayList() {
        list = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public boolean add(E element) {
        return list.add(element);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public boolean remove(Object obj) {
        return list.remove(obj);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
