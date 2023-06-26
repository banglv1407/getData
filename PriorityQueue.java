package datastructer;

public class PriorityQueue {

	private int[] a = null;
	private int size;

	public PriorityQueue(int maxSize) {
		a = new int[maxSize];
		size = 0;
	}

	// Returns true/false depending on if the priority queue is empty O(1)
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns true/false depending on if the priority queue is full O(1)
	public boolean isFull() {
		return size == a.length;
	}

	// Returns size of priority queue O(1)
	public int size() {
		return size;
	}

	// Returns the value of the element with the lowest priority in this priority
	// queue O(1)
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("PQ is empty");
		}

		return a[0];
	}

	// Removes the root of the heap, O(log(n))
	public int poll() {
		return removeAt(0);
	}

	// Adds an element to the priority queue O(log(n))
	public void add(int element) {
		if (isFull()) {
			throw new RuntimeException("PQ is full");
		}

		a[size] = element;
		size++;
		swim(size - 1);
	}

	// Test if an element is in heap, O(n)
	public boolean contains(int element) {
		for (int i = 0; i < size(); i++) {
			if (a[i] == element)
				return true;
		}

		return false;
	}

	// Removes a particular element in the heap, O(n)
	public boolean remove(int element) {
		for (int i = 0; i < size(); i++) {
			if (a[i] == element) {
				removeAt(i);
				return true;
			}
		}

		return false;
	}

	private void swap(int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	// Removes a node at particular index, O(log(n))
	private int removeAt(int i) {
		if (isEmpty()) {
			throw new RuntimeException("PQ is empty");
		}

		int lastIndex = size() - 1;
		int removedData = a[i];
		swap(i, lastIndex);

		size--;

		if (i == lastIndex)
			return removedData;

		int element = a[i];

		sink(i);

		// If sinking did not work try swimming
		if (a[i] == element)
			swim(i);

		return removedData;
	}

	// Perform bottom up node swim, O(log(n))
	private void swim(int k) {
		int parent = (k - 1) / 2;

		// Keep swimming while we have not reached the
		// root and while we're less than our parent
		while (k > 0 && a[k] < a[parent]) {
			swap(k, parent);
			k = parent;
			parent = (k - 1) / 2;
		}
	}

	// Top down node sink, O(log(n))
	private void sink(int k) {

		int heapSize = size();
		while (true) {
			int left = 2 * k + 1;
			int right = 2 * k + 2;
			int smallest = left;

			// Find which is smaller left or right
			if (right < heapSize && a[right] < a[left])
				smallest = right;

			// Stop if we're outside the bounds of the tree
			// or stop early if we cannot sink k anymore
			if (left >= heapSize || a[k] < a[smallest])
				break;

			swap(k, smallest);
			k = smallest;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(a[i]);

			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");

		return sb.toString();
	}

	// Recursively checks if this heap is a min heap
	public boolean isMinHeap(int k) {
		// If we are outside the bounds of the heap return true
		int heapSize = size();
		if (k >= heapSize)
			return true;

		int left = 2 * k + 1;
		int right = 2 * k + 2;

		// Make sure that the current node k is less than
		// both of its children left, and right if they exist
		// return false otherwise to indicate an invalid heap
		if (left < heapSize && a[k] > a[left])
			return false;
		if (right < heapSize && a[k] > a[right])
			return false;

		return isMinHeap(left) && isMinHeap(right);
	}

	public static void main(String[] args) {

		PriorityQueue heap = new PriorityQueue(17);

		heap.add(13);
		heap.add(8);
		heap.add(12);
		heap.add(6);
		heap.add(7);
		heap.add(11);
		heap.add(8);
		heap.add(7);
		heap.add(6);
		heap.add(5);

		System.out.println(heap.toString());
		// System.out.println(heap.size());
		System.out.println(heap.isMinHeap(0));

		heap.add(1);
		heap.add(13);
		heap.add(4);
		heap.add(0);
		heap.add(10);

		System.out.println(heap.toString());
		// System.out.println(heap.size());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		System.out.println(heap.peek()); // 0
		System.out.println(heap.poll()); // 0
		System.out.println(heap.remove(12));
		System.out.println(heap.remove(3));
		System.out.println(heap.poll());
		System.out.println(heap.remove(6));

		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		System.out.println(heap.contains(6)); // true
		System.out.println(heap.contains(100)); // false
	}

}
