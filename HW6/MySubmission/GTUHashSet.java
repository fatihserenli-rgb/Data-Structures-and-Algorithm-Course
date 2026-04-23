public class GTUHashSet<E> {
    private static final Object PRESENT = new Object();
    private GTUHashMap<E, Object> map;

    public GTUHashSet() {
        map = new GTUHashMap<>();
    }

    public void add(E element) {
        map.put(element, PRESENT);
    }

    public void remove(E element) {
        map.remove(element);
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public int size() {
        return map.size();
    }

    public String[] getAll() {
        return map.getAll();
    }
}
