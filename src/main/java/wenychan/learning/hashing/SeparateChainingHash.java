package wenychan.learning.hashing;

import java.lang.reflect.Array;

import wenychan.learning.hashing.algorithm.HashAlgorithm;

class Bucket<T> {
	public Item<T> link;
	public Item<T> last;
}

class Item<T> {
	public T item;
	public Item<T> next;
	public Item<T> pre;
	public Bucket<T> hole;
}


public class SeparateChainingHash<T> implements Hash<T> {
	private final int bucketLen;
	private int currCount;
	private final Bucket<T>[] bucket;
	private final HashAlgorithm<T> alg;
	
	@SuppressWarnings("unchecked")
	public SeparateChainingHash(int bucketLen, HashAlgorithm<T> alg) {
		this.bucketLen = bucketLen;
		this.alg = alg;
		this.alg.setBucketLen(bucketLen);
		
		bucket = (Bucket[]) Array.newInstance(Bucket.class, bucketLen);
		for(int i=0; i<bucketLen; i++) {
			bucket[i] = new Bucket<T>();
		}
	}
	
	synchronized public boolean insert(T item) {
		Item<T> newItem = new Item<T>();
		newItem.item = item;
		
		int hashCode = alg.hash(item);
		if(bucket[hashCode].last == null) {
			bucket[hashCode].link = newItem;
			bucket[hashCode].last = newItem;
			newItem.hole = bucket[hashCode];
		} else {
			Item<T> node = bucket[hashCode].link;
			while(node.next != null) {
				if(node.item.equals(item)) {
					System.out.println("Item is already exists.");
					return false;
				}
				node = node.next;
			}	
			newItem.pre = bucket[hashCode].last;
			bucket[hashCode].last.next = newItem;
			bucket[hashCode].last = newItem;
			newItem.hole = bucket[hashCode];
		}
		currCount++;
		return true;
	}

	synchronized public T search(T item) {
		if(bucket==null || bucketLen==0) {
			return null;
		}
		
		Item<T> find = findNode(item);
		if(find!=null && find.item!=null && find.item==item) {
			return find.item;
		} else {
			return null;
		}
	}

	synchronized public boolean delete(T item) {
		if(bucket==null || bucketLen==0) {
			return false;
		}
		
		Item<T> find = findNode(item);
		if(find != null) {
			if(find.next==null && find.pre!=null) {
				find.pre.next = null;
			} else if(find.pre==null && find.next!=null) {
				find.hole.last = find.next;
			} else if(find.pre!=null && find.next!=null) {
				find.pre.next = find.next;
			}
			find.item = null;
			find = null;
			currCount--;
			return true;
		} else {
			return false;
		}
	}

	synchronized public boolean clear() {
		if(bucket == null) {
			return false;
		} else if(currCount <= 0) {
			return true;
		} else {
			for(int i=0; i<bucketLen; i++) {
				bucket[i] = new Bucket<T>();
			}
			currCount = 0;
			return true;
		}
	}
	
	private Item<T> findNode(T item) {
		if(bucket==null || bucketLen==0) {
			return null;
		}
		
		int hashCode = alg.hash(item);
		Item<T> node = bucket[hashCode].link;
		if(node == null) {
			return null;
		}
		while(node != null) {
			if(node.item.equals(item)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

}
