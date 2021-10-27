import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    public int n;        // number of elements on list
    public Node<T> head;     // sentinel before first item
    public Node<T> tail;    // sentinel after last item

    public DoublyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.previous = head;
    }

    // linked list node helper data type
    @SuppressWarnings("hiding")
    public static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> previous;
    }

    // add the item to the list
    public void add(T item) {
        Node<T> last = tail.previous;
        Node<T> x = new Node<>();
        x.data = item;
        x.next = tail;
        x.previous = last;
        tail.previous = x;
        last.next = x;
        n++;
    }
    @Override
    public ListIterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    public class DoublyLinkedListIterator implements ListIterator<T> {
        private Node<T> current = head.next; // the node that is returned by next()
        private Node<T> lastAccessed = null; // the last node to be returned by prev() or next()
        // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasNext() {
            return index < n;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        public int previousIndex() {
            return index - 1;
        }

        public int nextIndex() {
            return index;
        } //current

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastAccessed = current;
            T item = current.data;
            current = current.next;
            index++;
            return item;
        }

        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            current = current.previous;
            index--;
            lastAccessed = current;
            return current.data;
        }

        // replace the item of the element that was last accessed by next() or
        // previous()
        // condition: no calls to remove() or add() after last call to next() or
        // previous()
        public void set(T item) {
            if (lastAccessed == null)
                throw new IllegalStateException();
            lastAccessed.data = item;
        }

        // remove the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or
        // previous()
        public void remove() {
            if (lastAccessed == null)
                throw new IllegalStateException();
            Node<T> x = lastAccessed.previous;
            Node<T> y = lastAccessed.next;
            x.next = y;
            y.previous = x;
            n--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        // add element to list
        public void add(T item) {
            Node<T> x = current.previous;
            Node<T> y = new Node<>();
            Node<T> z = current;
            y.data = item;
            x.next = y;
            y.next = z;
            z.previous = y;
            y.previous = x;
            n++;
            index++;
            lastAccessed = null;
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item).append(" ");
        return s.toString();
    }
}
