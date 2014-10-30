package wenychan.learning.hashing.algorithm;

public class MD5HashAlgorithm<T> implements HashAlgorithm<T> {
	private int bucketLen = -1;
	
	public int hash(T item) {
		if(bucketLen < 0) {
			return -1;
		} else {
			int hashCode = item.hashCode()%bucketLen;
			return hashCode;
		}
	}

	public int getBucketLen() {
		return bucketLen;
	}

	public void setBucketLen(int len) {
		bucketLen = len;
	}

}
