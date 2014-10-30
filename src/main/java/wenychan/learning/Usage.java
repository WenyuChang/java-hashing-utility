package wenychan.learning;

import java.util.Scanner;

import wenychan.learning.hashing.Hash;
import wenychan.learning.hashing.OpenAddressingHash;
import wenychan.learning.hashing.SeparateChainingHash;
import wenychan.learning.hashing.algorithm.HashAlgorithm;
import wenychan.learning.hashing.algorithm.MD5HashAlgorithm;
import wenychan.learning.hashing.collision.CollisionResolution;
import wenychan.learning.hashing.collision.DoubleHashingCollisionResolution;
import wenychan.learning.hashing.collision.LinearProbingCollisionResolution;
import wenychan.learning.hashing.collision.QuadraticProbingCollisionResolution;

public class Usage {

	public static Hash<Integer> getHash() {
		int defaultBucketLen = 3;
		float defaultLoadFactor = 0.75f;
		HashAlgorithm<Integer> alg = new MD5HashAlgorithm<Integer>();
		// CollisionResolution resolution = new LinearProbingCollisionResolution();
		// CollisionResolution resolution = new QuadraticProbingCollisionResolution();
		CollisionResolution resolution = new DoubleHashingCollisionResolution();
		
		// Hash<Integer> hash = new OpenAddressingHash<Integer>(defaultBucketLen, alg, resolution, defaultLoadFactor);
		Hash<Integer> hash = new SeparateChainingHash<Integer>(defaultBucketLen, alg);
		
		return hash;
	}
	
	public static void main(String[] args) {
		Hash<Integer> hash = getHash();
		
		while(true) {
			System.out.println("insert <num>|get <num>|delete <num>|clear|exit");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			String cmd = input.split(" ")[0];
			if(input.split(" ").length==2) {
				Integer num = Integer.valueOf(input.split(" ")[1]);
				if(cmd.toLowerCase().equals("insert")) {
					boolean result = hash.insert(num);
					if(result) {
						System.out.println("Succeed to insert...");
					} else {
						System.out.println("Failed to insert...");
					}
				} else if(cmd.toLowerCase().equals("get")) {
					Integer result = hash.search(num);
					if(result == null) {
						System.out.println("No such item...");
					} else {
						System.out.println("Find such item...");
					}
				} else if(cmd.toLowerCase().equals("delete")) {
					boolean result = hash.delete(num);
					if(result) {
						System.out.println("Succeed to delete...");
					} else {
						System.out.println("Failed to delete...");
					}
				}
			} else if(cmd.toLowerCase().equals("clear")) {
				boolean result = hash.clear();
				if(result) {
					System.out.println("Succeed to clear...");
				} else {
					System.out.println("Failed to clear...");
				}
			} else if(cmd.toLowerCase().equals("exit")) {
				break;
			}
		}
	}

}
