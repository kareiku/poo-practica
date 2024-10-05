package project;

public class Iterator<E> {
    private Node<E> iterator;

    public Iterator(Node<E> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return iterator.getNext() != null;
    }

    public E next() {
        E value = iterator.getValue();
        iterator = iterator.getNext();
        return value;
    }
}
