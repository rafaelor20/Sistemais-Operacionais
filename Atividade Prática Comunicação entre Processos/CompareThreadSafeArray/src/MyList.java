public interface MyList<E> {
    boolean add(E element);

    E get(int index);

    boolean remove(Object obj);

    int size();

    boolean isEmpty();
}
