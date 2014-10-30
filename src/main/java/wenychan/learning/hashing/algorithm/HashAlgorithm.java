package wenychan.learning.hashing.algorithm;

public interface HashAlgorithm<T> {
	public int hash(T item);
	public int getBucketLen();
	public void setBucketLen(int len);
}
