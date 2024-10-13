package project.manager;

public class LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList(LinkedList<E> list) {
        this();

        assert list != null;

        Iterator<E> iterator = list.listIterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
    }

    public boolean contains(E value) {
        return this.indexOf(value) != -1;
    }

    public int size() {
        return size;
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

    public void clear() {
        head = tail = null;
        size = 0;
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
        Node<E> temp = head;

        int i = 0;
        while (temp != null && temp.getValue() != value) {
            temp = temp.getNext();
            i++;
        }

        return temp != null ? i : -1;
    }

    public Iterator<E> listIterator() {
        return new Iterator<>(head);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String aux = "";
        Node<E> temp = head;

        while (temp != null) {
            aux = aux.concat(temp.getValue() + "->");
            temp = temp.getNext();
        }

        return aux;
    }
}
