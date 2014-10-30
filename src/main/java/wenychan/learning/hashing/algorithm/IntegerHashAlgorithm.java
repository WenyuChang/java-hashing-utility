package wenychan.learning.hashing.algorithm;

public class IntegerHashAlgorithm<T> implements HashAlgorithm<T> {
	private int bucketLen = -1;
	
	public int hash(T item) {
		if(bucketLen<0 || !(item instanceof Integer) ) {
			return -1;
		} else {
			int hashCode = ((Integer)item)%bucketLen;
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
