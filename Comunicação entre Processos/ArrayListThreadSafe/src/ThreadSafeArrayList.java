import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ThreadSafeArrayList<E> implements Iterable<E> {

    private List<E> list;

    public ThreadSafeArrayList() {
        list = new ArrayList<>();
        list = Collections.synchronizedList(list);
    }

    public boolean add(E e) {
        return list.add(e);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    // Método necessário para implementar a interface Iterable
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public static void main(String[] args) {
        ThreadSafeArrayList<String> threadSafeList = new ThreadSafeArrayList<>();

        threadSafeList.add("Elemento 1");
        threadSafeList.add("Elemento 2");

        // Agora o loop for-each funcionará corretamente
        synchronized (threadSafeList) {
            for (String element : threadSafeList) {
                System.out.println(element);
            }
        }

        // Outras operações com a lista...
    }
}
