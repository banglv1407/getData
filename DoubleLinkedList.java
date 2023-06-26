package datastructer;

public class DoubleLinkedList<T> {

	// Node of linked list
	private static class Node<T> {
		private T data;
		private Node<T> prev, next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", prev=" + prev + ", next=" + next + "]";
		}

	}

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	// Empty linked list O(n)
	public void clear() {
		Node<T> temp = head;

		while (temp != null) {
			Node<T> next = temp.next;

			temp.prev = temp.next = null;
			temp.data = null;

			temp = next;
		}

		size = 0;
		head = tail = temp = null;
	}

	// O(1)
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add an element to the tail of the linked list, O(1)
	public void add(T element) {
		addLast(element);
	}

	// Add a node to the tail of the linked list, O(1)
	public void addLast(T element) {
		if (isEmpty()) {
			head = tail = new Node<T>(element, null, null);
		} else {
			tail.next = new Node<T>(element, tail, null);
			tail = tail.next;
		}

		size++;
	}

	// Add an element to the beginning of this linked list, O(1)
	public void addFirst(T element) {
		if (isEmpty()) {
			head = tail = new Node<T>(element, null, null);
		} else {
			head.prev = new Node<T>(element, null, head);
			head = head.prev;
		}

		size++;
	}

	// Add an element at a specified index O(n)
	public void addAt(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}

		if (index == 0) {
			addFirst(element);
			return;
		}

		if (index == size) {
			addLast(element);
			return;
		}

		Node<T> temp = head;

		for (int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}

		Node<T> newNode = new Node<T>(element, temp, temp.next);
		temp.next.prev = newNode;
		temp.next = newNode;

		size++;
	}

	// Check the value of the first node if it exists, O(1)
	public T peekFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty list");
		return head.data;
	}

	// Check the value of the last node if it exists, O(1)
	public T peekLast() {
		if (isEmpty())
			throw new RuntimeException("Empty list");
		return tail.data;
	}

	// Remove the first value at the head of the linked list, O(1)
	public T removeFirst() {
		if (isEmpty())
			throw new RuntimeException("Empty list");

		T data = head.data;
		head = head.next;
		size--;

		if (isEmpty()) {
			tail = null;
		} else {
			head.prev = null;
		}

		return data;
	}

	// Remove the last value at the tail of the linked list, O(1)
	public T removeLast() {
		if (isEmpty())
			throw new RuntimeException("Empty list");

		T data = tail.data;
		tail = tail.prev;
		size--;

		if (isEmpty()) {
			head = null;
		} else {
			tail.next = null;
		}

		return data;

	}

	// Remove an arbitrary node from the linked list, O(1)
	public T remove(Node<T> node) {
		if (node.prev == null)
			return removeFirst();
		if (node.next == null)
			return removeLast();

		node.next.prev = node.prev;
		node.prev.next = node.next;

		T data = node.data;

		node = node.next = node.prev = null;

		return data;

	}

	// Remove a node at a particular index, O(n)
	public T removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}

		int i;
		Node<T> temp;

		// Search from front

		if (index < size / 2) {
			for (i = 0, temp = head; i != index; i++) {
				temp = temp.next;
			}
		} else {
			// Search from back

			for (i = size - 1, temp = tail; i != index; i--) {
				temp = temp.prev;
			}
		}

		return remove(temp);
	}

	// Remove a particular value in the linked list, O(n)
	public boolean remove(T element) {
		Node<T> temp = head;

		if (element == null) {
			for (temp = head; temp != null; temp = temp.next) {
				if (temp.data == null) {
					remove(temp);
					return true;
				}
			}
		}

		for (temp = head; temp != null; temp = temp.next) {
			if (temp.data.equals(element)) {
				remove(temp);
				return true;
			}
		}

		return false;
	}

	// Find the index of a particular value in the linked list, O(n)
	public int indexOf(T element) {
		int index = 0;
		Node<T> temp;

		if (element == null) {
			for (temp = head; temp != null; temp = temp.next, index++) {
				if (temp.data == null) {
					return index;
				}
			}

		}

		for (temp = head; temp != null; temp = temp.next, index++) {
			if (temp.data.equals(element)) {
				return index;
			}
		}

		return -1;
	}

	// Check is a value is contained within the linked list O(n)
	public boolean contains(T element) {
		return indexOf(element) != -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> temp = head;
		while (temp != null) {
			sb.append(temp.data);
			if (temp.next != null) {
				sb.append(", ");
			}
			temp = temp.next;
		}
		sb.append(" ]");
		return sb.toString();
	}

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

		list.add(5);
		list.add(23);
		list.add(7);
		list.add(13);

		System.out.println(list.toString());
		System.out.println(list.peekFirst()); // 5
		System.out.println(list.peekLast()); // 13

		list.addAt(2, 11);
		System.out.println(list.toString());
		list.removeAt(1);
		System.out.println(list.toString());

		System.out.println(list.contains(11)); // true
		System.out.println(list.contains(4)); // false
	}

}
