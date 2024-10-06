package project;

public class Iterator<E> {
    private Node<E> current;

    public Iterator(Node<E> current) {
        this.current = current;
    }

    public boolean hasNext() {
        return current != null;
    }

    public E next() {
        E value = current.getValue();
        current = current.getNext();
        return value;
    }
}
