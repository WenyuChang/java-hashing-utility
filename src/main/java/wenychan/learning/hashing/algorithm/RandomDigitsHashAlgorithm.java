package wenychan.learning.hashing.algorithm;

import java.util.Random;

public class RandomDigitsHashAlgorithm<T> implements HashAlgorithm<T> {
	private int bucketLen = -1;
	
	public int hash(T item) {
		if(bucketLen < 0) {
			return -1;
		} else {
			String itemStr = item.toString();
			int randomIdx = new Random().nextInt(itemStr.length());
			int hashCode = ((int) itemStr.charAt(randomIdx)) % bucketLen;
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
