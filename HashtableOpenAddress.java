package datastructer;

public class HashtableOpenAddress {

	private static class HashNode {
		String key;
		String data;

		public HashNode(String key, String data) {
			this.key = key;
			this.data = data;
		}
	}

	int capacity; // max size of hash table
	HashNode[] tb; // hash table

	public HashtableOpenAddress(int capacity) {
		this.capacity = capacity;
		tb = new HashNode[capacity];
	}

	// Hash function
	private int hash(String str) {
		int hash = 5381;

		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			hash = hash * 33 + c;
		}

		if (hash < 0)
			hash = hash * -1;

		return hash % capacity;
	}

	// Add
	public boolean add(String key, String data) {
		int h = hash(key);

		while (tb[h] != null) {
			// Key is existed
			if (tb[h].key.equals(key)) {
				return false;
			}

			h = (h + 1) % capacity; // Open addressing
		}
		tb[h] = new HashNode(key, data);
		tb[h].key = key;
		tb[h].data = data;

		return true;
	}

	// Contains
	public String contains(String key) {
		int h = hash(key);
		int cnt = capacity;

		while (tb[h] != null && tb[h].key != null && (--cnt) != 0) {
			if (tb[h].key.equals(key)) {
				return tb[h].data;
			}

			h = (h + 1) % capacity; // Open addressing
		}

		return null;
	}

	// Remove
	public String remove(String key) {
		int h = hash(key);
		int cnt = capacity;

		while (tb[h] != null && tb[h].key != null && (--cnt) != 0) {
			if (tb[h].key.equals(key)) {
				String temp = tb[h].data;
				tb[h] = null;
				return temp;
			}

			h = (h + 1) % capacity; // Open addressing
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < capacity; i++) {
			sb.append(i + ": ");
			if (tb[i] == null)
				sb.append("null");
			else {
				sb.append(tb[i].key + "=>" + tb[i].data);
			}

			sb.append("\n");

		}

		return sb.toString();
	}

	public static void main(String[] args) {
		HashtableOpenAddress hash = new HashtableOpenAddress(10);

		System.out.println(hash.add("Will", "21")); // true
		System.out.println(hash.add("Leah", "18")); // true
		System.out.println(hash.add("Rick", "61")); // true
		System.out.println(hash.add("Rai", "25")); // true
		System.out.println(hash.add("Lara", "34")); // true
		System.out.println(hash.add("Ryan", "56"));// true
		System.out.println(hash.add("Lara", "34")); // false
		System.out.println(hash.add("Finn", "21"));// true
		System.out.println(hash.add("Mark", "10"));// true

		System.out.println(hash.toString());

		System.out.println(hash.contains("minh")); // null
		System.out.println(hash.contains("Mark")); // 10

		System.out.println(hash.add("aMrk", "20"));// true
		System.out.println(hash.add("illW", "111")); // true
		System.out.println(hash.toString());

		System.out.println(hash.remove("minh")); // null
		System.out.println(hash.remove("illW")); // 111
		System.out.println(hash.remove("Lara")); // 34
		System.out.println(hash.toString());
	}

}
