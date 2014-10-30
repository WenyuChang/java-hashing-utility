package wenychan.learning.hashing.collision;

public interface CollisionResolution {
	public int next(int current, int retryTimes);
	public int getBucketLen();
	public void setBucketLen(int len);
}
