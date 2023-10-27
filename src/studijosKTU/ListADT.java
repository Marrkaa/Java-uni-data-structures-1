/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 * Koreguota: Aleksas Riškus, MIK, 2016
 *
 * Tai interfeisas, apibrėžiantis kelias pagrindines vienkrypčio sąrašo operacijas.
 *
 * Užduotis: 1. Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 *			 2. Papildyti naujais (pasirinktais iš List interfeiso) metodais. 
 *****************************************************************************
 */
package studijosKTU;

public interface ListADT<E> {

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e element to be appended to this list
	 * @return true, if operation is Ok
	 */
	boolean add(E e);

	/**
	 * Returns the number of elements in this list.
	 */
	int size();

	/**
	 * @return true if this list contains no elements.
	 */
	boolean isEmpty();

	/**
	 * Removes all of the elements from this list.
	 */
	void clear();

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param k index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	E get(int k);

	/**
	 * Atitinka iteratoriaus metodą next (List tokio metodo neturi)
	 * @return kitą reikšmę.
	 */
	E getNext();
	
	/**
	 * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
	 * @return an array containing all of the elements
	 */
	Object[] toArray();
        
        /**
         * 
         * @param k index of the element to set
         * @param e element which will replace the selected element
         * @return 
         */
        E set(int k, E e);
        
        /**
         * 
         * @param k index of the element to remove
         * @return the element at the specified position in this list
         */
        E remove(int k);
        
        /**
         * 
         * @param e element for which we will be searching
         * @return true or false
         */
        boolean contains(E e);
        
        /**
         * 
         * @param e element of which index we will be searching
         * @return index
         */
        int indexOf(E e);
        
        /**
         * 
         * @param k index in which place we need to insert
         * @param e element which we will be inserting
         */
        void add(int k, E e);
}
