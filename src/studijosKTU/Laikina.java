///**
// * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
// * Koreguota: Aleksas Riškus, MIK, 2016
// * Tai pirmoji duomenų struktūros klasė, kuri leidžia apjungti
// *   atskirus objektus į vieną visumą - sąrašą.
// * Objektai, kurie bus dedami į sąrašą, turi įdiegti interfeisą Comparable<E>.
// *
// * Užduotis: 1. Peržiūrėkite ir išsiaiškinkite pateiktus metodus. 
// *			 2. Papildykite klasę naujais metodais, kuriuos aprašėte interfeise ListADT.
// * ****************************************************************************
// */
//package studijosKTU;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Iterator;
//
///**
// * Pagrindinių sąrašo operacijų klasė.
// *
// * @param <E> Sąrašo elementų tipas (klasė)
// */
//public class ListKTU<E extends Comparable<E>>
//		implements ListADT<E>, Iterable<E>, Cloneable {
//
//	private Node<E> first;   // rodyklė į pirmą mazgą
//	private Node<E> last;    // rodyklė į paskutinį mazgą
//	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama metode getNext
//	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius
//
//	/**
//	 * Metodas add įdeda elementą į sąrašo pabaigą
//	 *
//	 * @param e - tai įdedamas elementas (jis negali būti null)
//	 * @return true, jei operacija atlikta sėkmingai
//	 */
//	@Override
//	public boolean add(E e) {
//		if (e == null) {
//			return false;   // nuliniai objektai nepriimami
//		}
//		if (first == null) {
//			first = new Node<>(e, first);
//			last = first;
//		} else {
//			Node<E> e1 = new Node(e, null);
//			last.next = e1;
//			last = e1;
//		}
//		size++;
//		return true;
//	}
//
//	/**
//	 *
//	 * @return sąrašo dydis (elementų kiekis)
//	 */
//	@Override
//	public int size() {
//		return size;
//	}
//
//	/**
//	 * Patikrina ar sąrašas yra tuščias
//	 *
//	 * @return true, jei tuščias
//	 */
//	@Override
//	public boolean isEmpty() {
//		return first == null;
//	}
//
//	/**
//	 * Išvalo sąrašą
//	 */
//	@Override
//	public void clear() {
//		size = 0;
//		first = null;
//		last = null;
//		current = null;
//	}
//
//	/**
//	 * Grąžina elementą pagal jo indeksą (pradinis indeksas 0)
//	 *
//	 * @param k indeksas
//	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
//	 */
//	@Override
//	public E get(int k) {
//		if (k < 0 || k >= size) {
//			return null;
//		}
//		current = first.findNode(k);
//		return current.element;
//	}
//
//	/**
//	 * Pereina prie kitos reikšmės ir ją grąžina
//	 *
//	 * @return kita reikšmė
//	 */
//	@Override
//	public E getNext() {
//		if (current == null) {
//			return null;
//		}
//		current = current.next;
//		if (current == null) {
//			return null;
//		}
//		return current.element;
//	}
//
//	/**
//	 * Sukuria sąrašo kopiją.
//	 * @return sąrašo kopiją
//	 */
//	@Override
//	public ListKTU<E> clone() {
//		ListKTU<E> cl = null;
//		try {
//			cl = (ListKTU<E>) super.clone();
//		} catch (CloneNotSupportedException e) {
//			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
//			System.exit(1);
//		}
//		if (first == null) {
//			return cl;
//		}
//		cl.first = new Node<>(this.first.element, null);
//		Node<E> e2 = cl.first;
//		for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
//			e2.next = new Node<>(e1.element, null);
//			e2 = e2.next;
//		}
//		cl.last = e2;
//		cl.size = this.size;
//		return cl;
//	}
//	
//	/**
//	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti), 
//	 *   kur surašomi sąrašo elementai
//	 *
//	 * @return sąrašo elementų masyvas
//	 */
//	@Override
//	public Object[] toArray() {
//		Object[] a = new Object[size];
//		int i = 0;
//		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
//			a[i++] = e1.element;
//		}
//		return a;
//	}
//
//	/**
//	 * Masyvo rikiavimas Arrays klasės metodu sort
//	 */
//	public void sortJava() {
//		Object[] a = this.toArray();
//		Arrays.sort(a);
//		int i = 0;
//		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
//			e1.element = (E) a[i++];
//		}
//	}
//
//	/**
//	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
//	 *
//	 * @param c komparatorius
//	 */
//	public void sortJava(Comparator<E> c) {
//		Object[] a = this.toArray();
//		Arrays.sort(a, (Comparator) c);
//		int i = 0;
//		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
//			e1.element = (E) a[i++];
//		}
//	}
//
//	/**
//	 * Sąrašo rikiavimas burbuliuko metodu
//	 */
//	public void sortBuble() {
//		if (first == null) {
//			return;
//		}
//		for (;;) {
//			boolean jauGerai = true;
//			Node<E> e1 = first;
//			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
//				if (e1.element.compareTo(e2.element) > 0) {
//					E t = e1.element;
//					e1.element = e2.element;
//					e2.element = t;
//					jauGerai = false;
//				}
//				e1 = e2;
//			}
//			if (jauGerai) {
//				return;
//			}
//		}
//	}
//
//	/**
//	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
//	 *
//	 * @param c komparatorius
//	 */
//	public void sortBuble(Comparator<E> c) {
//		if (first == null) {
//			return;
//		}
//		for (;;) {
//			boolean jauGerai = true;
//			Node<E> e1 = first;
//			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
//				if (c.compare(e1.element, e2.element) > 0) {
//					E t = e1.element;
//					e1.element = e2.element;
//					e2.element = t;
//					jauGerai = false;
//				}
//				e1 = e2;
//			}
//			if (jauGerai) {
//				return;
//			}
//		}
//	}
//
//	/**
//	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
//	 *
//	 * @return iteratoriaus objektą
//	 */
//	@Override
//	public Iterator<E> iterator() {
//		return new ListIteratorKTU();
//	}
//
//	/**
//	 * Iteratoriaus metodų klasė
//	 */
//	private class ListIteratorKTU implements Iterator<E> {
//
//		private Node<E> iterPosition;
//
//		ListIteratorKTU() {
//			iterPosition = first;
//		}
//
//		@Override
//		public boolean hasNext() {
//			return iterPosition != null;
//		}
//
//		@Override
//		public E next() {
//			E d = iterPosition.element;
//			iterPosition = iterPosition.next;
//			return d;
//		}
//	}
//        
//        @Override
//        public E set(int k, E e){
//            if (e == null) return null;
//            if(k < 0 || k >= size) return null;
//            Node<E> current = first.findNode(k);
//            current.element = e;
//            return current.element;
//        }
//        
//        @Override
//        public E remove (int k) {
//            if (k < 0 || k >= size) return null;
//            Node<E> actual = null;
//            if (k == 0) {
//                actual=first; first = actual.next;
//                if (first == null) last = null;
//            } 
//            else {
//                Node<E> previous = first.findNode(k - 1);
//                actual = previous.next;
//                previous.next = actual.next;
//                if (actual.next == null) last = previous;   
//            }
//            size--;
//            return actual.element;
//        }
//        
//        @Override
//        public int indexOf(E e){
//            int index = -1;
//            for(Node<E> temp = first; temp != null; temp = temp.next){
//                index++;
//                if (temp.element == e) {
//                    break;
//                }
//            }
//            return index;
//        }
//        
//        @Override
//        public boolean contains(E e){
//            for(Node<E> temp = first; temp != null; temp = temp.next){
//                if (temp.element == e) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        
//        /**
//         * Inserts an element behind the selected index
//         * @param k index of element behind which we will insert
//         * @param e the element which we will insert
//         */
//        @Override
//        public void add(int k, E e){
//            Node<E> temp = new Node<E>(e, null);
//            if (first == null) first = temp;
//            else if (k == 0){
//                temp.next = first;
//                first = temp;
//            }
//            else{
//                Node<E> temp2 = first.findNode(k);
//                Node<E> temp3 = first.findNode(k - 1);
//                temp.next = temp2;
//                temp3.next = temp;
//            }
//            size++;
//        }
//        
//        public void mergeSort(){
//            if (size <= 1) {
//                return; // Nothing to sort if there's only one element or none.
//            }
//            // Split the list into two halves
//            ListKTU<E> leftHalf = new ListKTU<>();
//            ListKTU<E> rightHalf = new ListKTU<>();
//
//            int middle = size / 2;
//            Node<E> current = first;
//
//            for (int i = 0; i < middle; i++) {
//                leftHalf.add(current.element);
//                current = current.next;
//            }
//
//            for (int i = middle; i < size; i++) {
//                rightHalf.add(current.element);
//                current = current.next;
//            }
//
//            // Recursively sort the halves
//            leftHalf.mergeSort();
//            rightHalf.mergeSort();
//
//            // Merge the sorted halves
//            first = merge(leftHalf.first, rightHalf.first);
//        }
//        
//        private Node<E> merge(Node<E> left, Node<E> right){
//            Node<E> merged = new Node<>(null, null);
//            Node<E> current = merged;
//
//            while (left != null && right != null) {
//                if (left.element.compareTo(right.element) <= 0) {
//                    current.next = left;
//                    left = left.next;
//                } else {
//                    current.next = right;
//                    right = right.next;
//                }
//                current = current.next;
//            }
//
//            if (left != null) {
//                current.next = left;
//            } else if (right != null) {
//                current.next = right;
//            }
//    
//            return merged.next;
//        }
//        
//	/**
//	 * Vidinė mazgo klasė
//	 *
//	 * @param <E> mazgo duomenų tipas
//	 */
//	private static class Node<E> {
//
//		private E element;    // ji nematoma už klasės ListKTU ribų
//		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą
//
//		Node(E e, Node<E> next) { //mazgo konstruktorius
//			this.element = e;
//			this.next = next;
//		}
//
//		/**
//		 * Suranda sąraše k-ąjį mazgą
//		 *
//		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
//		 * @return surastas mazgas
//		 */
//		public Node<E> findNode(int k) {
//			Node<E> e = this;
//			for (int i = 0; i < k; i++) {
//				e = e.next;
//			}
//			return e;
//		}
//	} // klasės Node pabaiga
//}