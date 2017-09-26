package lab3;

public interface OrderedSet<T extends Comparable<T>>
{
    public boolean insert(T element);

    public boolean contains(T  element);

    public boolean remove(T  element);

    public T get(int index);

    public int size();

    public OrderedSet<T> first(int k);

    public OrderedSet<T> last(int k);

    public OrderedSet<T> union(OrderedSet<T> set);

    public OrderedSet<T> inter(OrderedSet<T> set);

    public OrderedSet<T> diff(OrderedSet<T> set);
}