package studijosKTU;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Pagrindinių sąrašo operacijų klasė.
 *
 * @param <E> Sąrašo elementų tipas (klasė)
 */
public class ListKTU<E extends Comparable<E>>
		implements ListADT<E>, Iterable<E>, Cloneable {

	private Node<E> first;   // rodyklė į pirmą mazgą
	private Node<E> last;    // rodyklė į paskutinį mazgą
	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama metode getNext
	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius

	/**
	 * Metodas add įdeda elementą į sąrašo pabaigą
	 *
	 * @param e - tai įdedamas elementas (jis negali būti null)
	 * @return true, jei operacija atlikta sėkmingai
	 */
	@Override
	public boolean add(E e) {
            if (e == null) {
                return false;
            }
            Node<E> newNode = new Node<>(e, null, last);
            if (last == null) {
                first = newNode;
            } else {
                last.next = newNode;
            }
            last = newNode;
            size++;
            return true;
	}

	/**
	 *
	 * @return sąrašo dydis (elementų kiekis)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Patikrina ar sąrašas yra tuščias
	 *
	 * @return true, jei tuščias
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Išvalo sąrašą
	 */
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		current = null;
	}

	/**
	 * Grąžina elementą pagal jo indeksą (pradinis indeksas 0)
	 *
	 * @param k indeksas
	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
	 */
	@Override
	public E get(int k) {
            if (k < 0 || k >= size) {
                return null;
            }
            if (k < size / 2) {
                current = first;
                for (int i = 0; i < k; i++) {
                    current = current.next;
                }
            } else {
                current = last;
                for (int i = size - 1; i > k; i--) {
                    current = current.previous;
                }
            }
            return current.element;
	}

	/**
	 * Pereina prie kitos reikšmės ir ją grąžina
	 *
	 * @return kita reikšmė
	 */
	@Override
	public E getNext() {
		if (current == null) {
			return null;
		}
		current = current.next;
		if (current == null) {
			return null;
		}
		return current.element;
	}

	/**
	 * Sukuria sąrašo kopiją.
	 * @return sąrašo kopiją
	 */
	@Override
	public ListKTU<E> clone() {
            ListKTU<E> cl = null;
            try {
                    cl = (ListKTU<E>) super.clone();
            } catch (CloneNotSupportedException e) {
                    Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
                    System.exit(1);
            }
            if (first == null) {
                    return cl;
            }
            cl.first = new Node<>(this.first.element, null, null);
            Node<E> e2 = cl.first;
            for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
                    e2.next = new Node<>(e1.element, null, null);
                    e2 = e2.next;
            }
            cl.last = e2;
            cl.size = this.size;
            return cl;
	}
	
	/**
	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti), 
	 *   kur surašomi sąrašo elementai
	 *
	 * @return sąrašo elementų masyvas
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			a[i++] = e1.element;
		}
		return a;
	}

	/**
	 * Masyvo rikiavimas Arrays klasės metodu sort
	 */
	public void sortJava() {
		Object[] a = this.toArray();
		Arrays.sort(a);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortJava(Comparator<E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu
	 */
	public void sortBuble() {
            if (first == null || first.next == null) {
                return;  // Nothing to sort if there's only one element or none.
            }

            boolean swapped;
            do {
                swapped = false;
                Node<E> current = first;
                while (current.next != null) {
                    if (current.element.compareTo(current.next.element) > 0) {
                        E temp = current.element;
                        current.element = current.next.element;
                        current.next.element = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortBuble(Comparator<E> c) {
            if (first == null || first.next == null) {
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                Node<E> current = first;
                while (current.next != null) {
                    if (current.element.compareTo(current.next.element) > 0) {
                        E temp = current.element;
                        current.element = current.next.element;
                        current.next.element = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);
	}

	/**
	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
	 *
	 * @return iteratoriaus objektą
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIteratorKTU();
	}

	/**
	 * Iteratoriaus metodų klasė
	 */
	private class ListIteratorKTU implements Iterator<E> {

		private Node<E> iterPosition;

		ListIteratorKTU() {
			iterPosition = first;
		}

		@Override
		public boolean hasNext() {
			return iterPosition != null;
		}

		@Override
		public E next() {
			E d = iterPosition.element;
			iterPosition = iterPosition.next;
			return d;
		}
	}
        
        @Override
        public E set(int k, E e){
            if (e == null || k < 0 || k >= size) {
                return null;
            }
            Node<E> node = first;
            for (int i = 0; i < k; i++) {
                node = node.next;
            }
            E oldValue = node.element;
            node.element = e;
            return oldValue;
        }
        
        @Override
        public E remove (int k) {
            if (k < 0 || k >= size) {
                return null;
            }
            Node<E> node = first;
            for (int i = 0; i < k; i++) {
                node = node.next;
            }
            E removedValue = node.element;

            if (node.previous == null) {
                first = node.next;
            } else {
                node.previous.next = node.next;
            }

            if (node.next == null) {
                last = node.previous;
            } else {
                node.next.previous = node.previous;
            }

            size--;
            return removedValue;
        }
        
        @Override
        public int indexOf(E e){
            int index = 0;
            Node<E> node = first;
            while (node != null) {
                if (node.element.equals(e)) {
                    return index;
                }
            node = node.next;
            index++;
            }
            return -1;
        }
        
        @Override
        public boolean contains(E e){
            for(Node<E> temp = first; temp != null; temp = temp.next){
                if (temp.element == e) {
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Inserts an element behind the selected index
         * @param k index of element behind which we will insert
         * @param e the element which we will insert
         */
        @Override
        public void add(int k, E e){
            if (e == null || k < 0 || k > size) {
                return;
            } 
            Node<E> newNode = new Node<>(e, null, null);
            Node<E> node = first;
            if (k == size) {
                last.previous.next = newNode;
                newNode.next = last;
                return;
            }
            for (int i = 0; i < k; i++) {
                node = node.next;
            }
            if (node.previous == null) {
                newNode.next = node;
                node.previous = newNode;
                first = newNode;
            } else {
                newNode.next = node;
                newNode.previous = node.previous;
                node.previous.next = newNode;
                node.previous = newNode;
            }
            size++;
        }
        
        public void mergeSort(){
            if (size <= 1) {
                return;
            }
            ListKTU<E> leftHalf = new ListKTU<>();
            ListKTU<E> rightHalf = new ListKTU<>();

            int middle = size / 2;
            Node<E> current = first;

            for (int i = 0; i < middle; i++) {
                leftHalf.add(current.element);
                current = current.next;
            }

            for (int i = middle; i < size; i++) {
                rightHalf.add(current.element);
                current = current.next;
            }

            leftHalf.mergeSort();
            rightHalf.mergeSort();

            first = merge(leftHalf.first, rightHalf.first);
        }
        
        private Node<E> merge(Node<E> left, Node<E> right){
            Node<E> merged = new Node<>(null, null, null);
            Node<E> current = merged;

            while (left != null && right != null) {
                if (left.element.compareTo(right.element) <= 0) {
                    current.next = left;
                    left.previous = current;
                    left = left.next;
                } else {
                    current.next = right;
                    right.previous = current;
                    right = right.next;
                }
                current = current.next;
            }

            if (left != null) {
                current.next = left;
                left.previous = current;
            } else if (right != null) {
                current.next = right;
                right.previous = current;
            }

            return merged.next;
        }
        
	/**
	 * Vidinė mazgo klasė
	 *
	 * @param <E> mazgo duomenų tipas
	 */
	private static class Node<E> {

		private E element;    // ji nematoma už klasės ListKTU ribų
		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą
                private Node<E> previous;

		Node(E e, Node<E> next, Node<E> previous) { //mazgo konstruktorius
			this.element = e;
			this.next = next;
                        this.previous = previous;
		}

		/**
		 * Suranda sąraše k-ąjį mazgą
		 *
		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
		 * @return surastas mazgas
		 */
		public Node<E> findNode(int k) {
			Node<E> e = this;
			for (int i = 0; i < k; i++) {
				e = e.next;
			}
			return e;
		}
	} // klasės Node pabaiga
}