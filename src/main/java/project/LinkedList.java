package project;

public class LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(E value) {
        boolean flag = false;
        Node<E> temp = head;

        int i = 0;
        while (!flag && i < size) {
            if (temp.getValue() == value) {
                flag = true;
            }
            temp = temp.getNext();
            i++;
        }

        return flag;
    }

    public void add(E value) {
        Node<E> aux = new Node<>(value, null);

        if (this.isEmpty()) {
            head = aux;
        } else {
            tail.setNext(aux);
        }

        tail = aux;
        size++;
    }

    public void remove(E value) {
        Node<E> current = head;
        Node<E> previous = head;

        while (current != null && current.getValue() != value) {
            previous = current;
            current = current.getNext();
        }

        if (current != null) {
            if (current == head) {
                head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }

            if (current == tail) {
                tail = previous;
            }
            size--;
        }
    }

    public E get(int index) {
        assert index >= 0 && index < size;

        Node<E> temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp.getValue();
    }

    public void set(int index, E value) {
        assert index >= 0 && index < size;

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        temp.setValue(value);
    }

    public int indexOf(E value) {
        assert !this.isEmpty();

        Node<E> temp = head;

        int i = 0;
        while (temp.getValue() != value && i < size) {
            i++;
        }

        assert i != size;

        return i;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public Iterator<E> getIterator() {
        return new Iterator<>(head);
    }
}
