package wenychan.learning.hashing.collision;

public class QuadraticProbingCollisionResolution implements CollisionResolution {
	private int bucketLen = -1;
	
	public int next(int current, int retryTimes) {
		if(bucketLen < 0) {
			return -1;
		}
		
		return (current+retryTimes)%bucketLen;
	}

	public int getBucketLen() {
		return bucketLen;
	}

	public void setBucketLen(int len) {
		bucketLen = len;
	}

}
