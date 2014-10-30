package wenychan.learning.hashing;

public interface Hash<T> {
	public boolean insert(T item);
	public T search(T item);
	public boolean delete(T item);
	public boolean clear();
}
