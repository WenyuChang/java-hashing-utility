package wenychan.learning.hashing;

import wenychan.learning.hashing.algorithm.HashAlgorithm;
import wenychan.learning.hashing.collision.CollisionResolution;

public class OpenAddressingHash<T> implements Hash<T> {
	private int bucketLen;
	private int currCount;
	private T[] bucket;
	
	private final HashAlgorithm<T> alg;
	private final CollisionResolution collisionResolution;
	private float loadFactorThreshold;
	
	
	@SuppressWarnings("unchecked")
	public OpenAddressingHash(int bucketLen, HashAlgorithm<T> alg, CollisionResolution collisionResolution, float loadFactorThreshold) {
		this.bucketLen = bucketLen;
		this.currCount = 0;
		this.alg = alg;
		this.alg.setBucketLen(bucketLen);
		this.collisionResolution = collisionResolution;
		this.collisionResolution.setBucketLen(bucketLen);
		this.loadFactorThreshold = loadFactorThreshold;
		
		this.bucket = (T[]) new Object[bucketLen];
	}

	synchronized public boolean insert(T item) {
		int initHashIdx = alg.hash(item);
		if(initHashIdx < 0) {
			// Failed to get hash index
			return false;
		}
		
		int hashIdx = initHashIdx;
		int retry = 0;
		while(bucket[hashIdx]!=null) {
			retry++;
			if(bucket[hashIdx].equals(item)) {
				System.out.println("Item is already exists.");
				return false;
			} else {
				hashIdx = collisionResolution.next(hashIdx, retry);
				if(hashIdx < 0) {
					// Failed to get next index
					return false;
				}
				if(hashIdx == initHashIdx) {
					System.out.println("Hash bucket is full.");
					return false;
				}
			}
		}
		bucket[hashIdx] = item;
		currCount++;
		
		if(needResize()) {
			return resize();
		} else {
			return true;
		}
	}

	synchronized public T search(T item) {
		if(bucket==null || item==null) {
			return null;
		}
		
		int idx = contain(item);
		if(idx < 0) {
			return null;
		} else {
			return bucket[idx];
		}
	}

	synchronized public boolean delete(T item) {
		if(bucket==null || item==null) {
			return false;
		}
		
		int idx = contain(item);
		if(idx < 0) {
			return false;
		} else {
			bucket[idx] = null;
			currCount--;
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	synchronized public boolean clear() {
		if(bucket==null) {
			return false;
		} else if(currCount <= 0) {
			return true;
		} else {
			this.bucket = (T[]) new Object[bucketLen];
			currCount = 0;
			return true;
		}
	}

	private int contain(T item) {
		int initHashIdx = alg.hash(item);
		int hashIdx = initHashIdx;
		int retry = 0;
		while(bucket[hashIdx]!=null) {
			retry++;
			if(bucket[hashIdx].equals(item)) {
				return hashIdx;
			} else {
				hashIdx = collisionResolution.next(hashIdx, retry);
				if(hashIdx == initHashIdx) {
					return -1;
				}
			}
		}
		return -1;
	}
	
	private boolean needResize() {
		return (float)currCount/bucketLen >= loadFactorThreshold;
	}
	
	@SuppressWarnings("unchecked")
	private boolean resize() {
		int newLen = bucketLen*2;
		T[] newBucket = (T[]) new Object[newLen];
		alg.setBucketLen(newLen);
		collisionResolution.setBucketLen(newLen);
		for(T item : bucket) {
			int initHashIdx = alg.hash(item);
			int hashIdx = initHashIdx;
			int retry = 0;
			while(newBucket[hashIdx]!=null) {
				retry++;
				if(newBucket[hashIdx].equals(item)) {
					return false;
				} else {
					hashIdx = collisionResolution.next(hashIdx, retry);
					if(hashIdx == initHashIdx) {
						return false;
					}
				}
			}
			newBucket[hashIdx] = item;
		}
		bucket = newBucket;
		bucketLen = newLen;
		return true;
	}
}
