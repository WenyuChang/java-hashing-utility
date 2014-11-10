Java Based Hash Functionality Implementation
==============
__Supported Hashing Types:__

1. Open Addressing Hash
2. Separate Chainning Hash

__Supported Hash Algorithms__

1. MD5 Hash Algorithm
2. Simple Integer Hash Algorithm
3. Random Digits Hash Algorithm

__Supported Hash Collision Approaches:__

1. Double Hashing Collision Resolution
2. Linear Probing Collision Resolution
3. Quadratic Probing Collision Resolution

__Support Hash Resize Approaches:__

1. Double Resize
2. Dynamic Resize  

__Usage:__
``` java
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
```
 
