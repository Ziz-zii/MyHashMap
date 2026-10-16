public class MyHashMap <K,V>{


    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] buckets;
    private int size;
    private int threshold;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Node[INITIAL_CAPACITY];
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    private int index(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(K key, V value) {
        int i = index(key);
        Node<K, V> current = buckets[i];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[i];
        buckets[i] = newNode;
        size++;

        if (size > threshold) {
            resize();
        }
    }
    public V get(K key) {
        int i = index(key);
        Node<K, V> current = buckets[i];
        while (current != null) {
            if (keysEqual(current.key, key)) return current.value;
            current = current.next;
        }
        return null;
    }
    public V remove(K key) {
        int i = index(key);
        Node<K, V> current = buckets[i];
        Node<K, V> prev = null;

        while (current != null) {
            if (keysEqual(current.key, key)) {
                if (prev == null) buckets[i] = current.next;
                else prev.next = current.next;
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        int newCapacity = oldBuckets.length * 2;

        buckets = new Node[newCapacity]; // присваиваем НОВЫЙ массив
        threshold = (int) (newCapacity * LOAD_FACTOR);

        for (Node<K, V> head : oldBuckets) {
            while (head != null) {
                Node<K, V> next = head.next;      // запоминаем next до перезаписи
                int newIndex = Math.abs(
                        head.key == null ? 0 : head.key.hashCode()
                ) % newCapacity;

                head.next = buckets[newIndex];    // вставляем в голову нового бакета
                buckets[newIndex] = head;

                head = next;
            }
        }
    }

    private boolean keysEqual(K a, K b) {
        if (a == null) return b == null;
        return a.equals(b);
    }

    public int size() { return size; }
}


