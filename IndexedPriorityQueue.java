package datastructer;

public class IndexedPriorityQueue {

	private int sz; // current number of elements
	private int N; // maximum number of elements
	private int[] child, parent; // get child/parent indexes of node
	public int[] pm; // Position map, show node of ki, pm[ki] = node index
	public int[] im; // Inverse map, show ki of node, im[node index] = ki
	public int[] a; // heap value

	public IndexedPriorityQueue(int maxSize) {
		N = maxSize;
		pm = new int[N];
		im = new int[N];
		child = new int[N];
		parent = new int[N];
		a = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = (i - 1) / 2;
			child[i] = i * 2 + 1;
			pm[i] = im[i] = -1;
		}
	}

	public int size() {
		return sz;
	}

	public boolean isEmpty() {
		return sz == 0;
	}

	public boolean contains(int ki) {
		return pm[ki] != -1;
	}

	// swap 2 node with index i, j
	private void swap(int i, int j) {
		pm[im[j]] = i;
		pm[im[i]] = j;

		int temp = im[i];
		im[i] = im[j];
		im[j] = temp;
	}

	private boolean less(int i, int j) {
		if (a[im[i]] < a[im[j]])
			return true;
		return false;
	}

	private void swim(int i) {
		while (less(i, parent[i])) {
			swap(i, parent[i]);
			i = parent[i];
		}
	}

	// Top down node sink, O(log(n))
	private void sink(int i) {
		int heapSize = size();
		while (true) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int smallest = left;

			// Find which is smaller left or right
			if (right < heapSize && less(right, left))
				smallest = right;

			// Stop if we're outside the bounds of the tree
			// or stop early if we cannot sink k anymore
			if (left >= heapSize || less(i, smallest))
				break;

			swap(i, smallest);
			i = smallest;
		}
	}

	public void insert(int ki, int value) {

		pm[ki] = sz;
		im[sz] = ki;
		a[ki] = value;
		swim(sz);
		sz++;
	}

	public int remove(int ki) {

		int i = pm[ki];
		sz--;
		swap(i, sz);

		int value = a[ki];
		a[ki] = -1; // dummy
		pm[ki] = -1;
		im[sz] = -1;

		sink(i);
		swim(i);

		return value;
	}

	public int update(int ki, int value) {
		int i = pm[ki];
		int oldValue = a[ki];
		a[ki] = value;
		sink(i);
		swim(i);

		return oldValue;

	}

	public void decrease(int ki, int value) {
		if (value < a[ki]) {
			a[ki] = value;
			swim(pm[ki]);
		}
	}

	public void increase(int ki, int value) {
		if (value > a[ki]) {
			a[ki] = value;
			sink(pm[ki]);
		}
	}

	public int peekMinKeyIndex() {
		return im[0];
	}

	public int pollMinKeyIndex() {
		int minki = peekMinKeyIndex();
		remove(minki);
		return minki;
	}

	public int peekMinValue() {
		return a[im[0]];
	}

	public int pollMinValue() {
		int minValue = peekMinValue();
		remove(peekMinKeyIndex());
		return minValue;
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
		if (left < heapSize && less(left, k))
			return false;
		if (right < heapSize && less(right, k))
			return false;

		return isMinHeap(left) && isMinHeap(right);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Value: [");
		for (int i = 0; i < sz; i++) {
			sb.append(a[im[i]]);

			if (i < sz - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		sb.append("\n");

		sb.append("Pm   : [");
		for (int i = 0; i < sz; i++) {
			sb.append(pm[i]);

			if (i < sz - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");

		sb.append("\n");
		sb.append("Im   : [");
		for (int i = 0; i < sz; i++) {
			sb.append(im[i]);

			if (i < sz - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");

		return sb.toString();
	}

	public static void main(String[] args) {
		IndexedPriorityQueue heap = new IndexedPriorityQueue(17);

		heap.insert(0, 3);
		heap.insert(1, 15);
		heap.insert(2, 11);
		heap.insert(3, 17);
		heap.insert(4, 7);
		heap.insert(5, 9);
		heap.insert(6, 2);
		heap.insert(7, 1);
		heap.insert(8, 6);
		heap.insert(9, 5);
		heap.insert(10, 16);
		heap.insert(11, 4);

		System.out.println(heap.toString());
		System.out.println(heap.isMinHeap(0));

		heap.insert(12, 2);

		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		System.out.println(heap.pollMinValue());
		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		System.out.println(heap.remove(11));
		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		System.out.println(heap.update(2, 1));
		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		heap.decrease(10, 0);
		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

		heap.increase(10, 16);
		System.out.println(heap.toString());
		System.out.println("Min heap: " + heap.isMinHeap(0));

	}

}
