public class GTUHashMap<K, V> {
    private Entry<K, V>[] table;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 101;
    private static final double LOAD_FACTOR = 0.75;

    public GTUHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public GTUHashMap(int initialCapacity) {
        this.capacity = nextPrime(initialCapacity);
        this.table = new Entry[this.capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private int quadraticProbe(int hash, int i) {
        return (hash + i * i) % capacity;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int nextPrime(int n) {
        while (!isPrime(n)) n++;
        return n;
    }

    private void rehash() {
        int newCapacity = nextPrime(2 * capacity);
        Entry<K, V>[] oldTable = table;
        table = new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        if ((double) size / capacity >= LOAD_FACTOR) rehash();
    
        int hash = hash(key);
        int i = 0;
        int index;
    
        do {
            index = quadraticProbe(hash, i);
            if (table[index] == null || table[index].isDeleted) {
                table[index] = new Entry<>(key, value);
                size++;
                return;
            } else if (table[index].key.equals(key)) {
                table[index].value = value;
                return;
            }
            i++;
        } while (i < capacity);
    
        rehash();
        put(key, value);
    }

    public V get(K key) {
        int hash = hash(key);
        int i = 0;
        int index;
    
        do {
            index = quadraticProbe(hash, i);
            if (table[index] == null) break;
            if (!table[index].isDeleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            i++;
        } while (i < capacity);
    
        return null;
    }

    public void remove(K key) {
        int hash = hash(key);
        int i = 0;
        int index = quadraticProbe(hash, i);

        while (table[index] != null) {
            if (!table[index].isDeleted && table[index].key.equals(key)) {
                table[index].isDeleted = true;
                size--;
                return;
            }
            i++;
            index = quadraticProbe(hash, i);
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public String[] getAll() {
        String[] arr = new String[size];
        int j = 0;
        for(int i = 0; i < table.length; ++i) {
            if(table[i] != null) {
                if(!table[i].isDeleted) {
                    arr[j++] = table[i].key.toString();
                }
            }
        }
        return arr;
    }
}
