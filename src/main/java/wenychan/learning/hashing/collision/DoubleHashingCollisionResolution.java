package wenychan.learning.hashing.collision;

import wenychan.learning.hashing.algorithm.HashAlgorithm;
import wenychan.learning.hashing.algorithm.IntegerHashAlgorithm;

public class DoubleHashingCollisionResolution implements CollisionResolution {
	private int bucketLen = -1;
	private final HashAlgorithm<Integer> alg = new IntegerHashAlgorithm<Integer>();
	
	public int next(int current, int retryTimes) {
		if(bucketLen < 0) {
			return -1;
		}
		
		return (current+retryTimes*alg.hash(retryTimes))%bucketLen;
	}

	public int getBucketLen() {
		return bucketLen;
	}

	public void setBucketLen(int len) {
		alg.setBucketLen(bucketLen);
		bucketLen = len;
	}

}
