
package control.myUtil;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Class created to provide a Optional class Serializable This class contains
 * just the main method ( Not all )
 * 
 * 
 * @author lorenzo
 *
 * @param <E>
 */

public class MyOptional<E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4092864802291780152L;
	private E element = null;
	private Boolean isPresentElem = false;

	/**
	 * static Method that returns a empty optional Object
	 * 
	 * @return
	 */
	public static <E> MyOptional<E> empty() {
		final MyOptional<E> opt = new MyOptional<>();
		opt.setElement(null);
		opt.isPresentElem = false;
		return opt;
	}

	/**
	 * static method that provide an istance of myOptional setted with non_null
	 * value
	 * 
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <E> MyOptional<E> of(final E value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		final MyOptional<E> opt = new MyOptional<>();
		opt.setElement(value);
		opt.isPresentElem = true;
		return opt;
	}

	/**
	 * Returns an Optional describing the specified value, if non-null,
	 * otherwise returns an empty Optional.
	 * 
	 * @param value
	 * @return
	 */

	public static <E> MyOptional<E> ofNullable(final E value) {
		if (value == null) {
			final MyOptional<E> opt = new MyOptional<>();
			opt.setElement(null);
			opt.isPresentElem = false;
			return opt;
		} else {
			final MyOptional<E> opt = new MyOptional<>();
			opt.setElement(value);
			opt.isPresentElem = true;
			return opt;
		}
	}

	/**
	 * Returns the element if it is a non-Null value. Otherwise
	 * NoSucheElementException
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */

	public E get() throws NoSuchElementException {
		if (!this.isPresentElem) {
			throw new NoSuchElementException();
		} else {
			return this.element;
		}
	}

	/**
	 * Return true if the element is a non-Null value
	 * 
	 * @return
	 */
	public boolean isPresent() {
		return this.isPresentElem;
	}

	/**
	 * If the value is present returns the value otherwise returns other
	 * 
	 * @param other
	 * @return
	 */

	public E orElse(final E other) {
		if (this.isPresent()) {
			return this.element;
		} else {
			return other;
		}
	}

	/**
	 * Returns a String about the object
	 */
	public String toString() {
		final String rv = "Optional[ " + this.element.toString() + " ] ";
		return rv;
	}

	/**
	 * If a value is present, and the value matches the given predicate, return
	 * an Optional describing the value, otherwise return an empty Optional.
	 * 
	 * @param predicate
	 * @return
	 */

	public boolean filter(final Predicate<? super E> predicate) {
		return predicate.test(this.element);
	}

	/*
	 * Private class to set the element
	 */
	private void setElement(final E elem) {
		this.element = elem;
	}

}
