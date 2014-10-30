package wenychan.learning.hashing.collision;

public class LinearProbingCollisionResolution implements CollisionResolution {
	private int bucketLen = -1;
	
	public int next(int current, int retryTimes) {
		if(bucketLen < 0) {
			return -1;
		}
		
		return (current+1)%bucketLen;
	}

	public int getBucketLen() {
		return bucketLen;
	}

	public void setBucketLen(int len) {
		bucketLen = len;
	}

}
