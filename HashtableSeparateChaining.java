package datastructer;

import java.util.LinkedList;

public class HashtableSeparateChaining {
	private static class HashNode {
		String key;
		String data;

		public HashNode(String key, String data) {
			this.key = key;
			this.data = data;
		}
	}

	int capacity; // max size of hash table
	LinkedList<HashNode>[] tb;

	@SuppressWarnings("unchecked")
	public HashtableSeparateChaining(int capacity) {
		this.capacity = capacity;
		tb = new LinkedList[capacity];

		for (int i = 0; i < capacity; i++) {
			tb[i] = new LinkedList<HashNode>();
		}
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

		for (HashNode node : tb[h]) {
			if (node.key.compareTo(key) == 0)
				return false;
		}

		tb[h].add(new HashNode(key, data));
		return true;
	}

	// Contains
	public String contains(String key) {
		int h = hash(key);

		for (HashNode node : tb[h]) {
			if (node.key.compareTo(key) == 0)
				return node.data;
		}

		return null;
	}

	// Remove
	public String remove(String key) {
		int h = hash(key);
		for (HashNode node : tb[h]) {
			if (node.key.compareTo(key) == 0) {
				String temp = node.data;
				tb[h].remove(node);
				return temp;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < capacity; i++) {
			sb.append(i + ": ");

			for (HashNode node : tb[i]) {
				sb.append("(" + node.key + "," + node.data + ")");
				sb.append("--->");
			}

			sb.append("\n");

		}

		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashtableSeparateChaining hash = new HashtableSeparateChaining(10);

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
