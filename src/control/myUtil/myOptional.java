package control.myUtil;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/** 
 * Class created to provide a Optional class Serializable
 * This class contains just the main method ( Not all )
 * 
 * 
 * @author lorenzo
 *
 * @param <E>
 */

public class myOptional <E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4092864802291780152L;
	private E element = null;
	
	/**
	 * static Method that returns a empty optional Object
	 * @return
	 */
	public static <E> myOptional<E> empty(){
		myOptional<E> opt = new myOptional<>();
		opt.setElement(null);
		return opt;
	}
	/**
	 * static method that provide an istance of myOptional setted with non_null value
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <E> myOptional<E> of(E value) throws IllegalArgumentException {
		if(value == null){
			throw new IllegalArgumentException();
		}
		myOptional<E> opt = new myOptional<>();
		opt.setElement(value);
		return opt;
	}
	/**
	 * Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
	 * @param value
	 * @return
	 */
	
	public static <E> myOptional<E> ofNullable(E value){
		if(value == null){
			myOptional<E> opt = new myOptional<>();
			opt.setElement(null);
			return opt;
		}else{
			myOptional<E> opt = new myOptional<>();
			opt.setElement(value);
			return opt;
		}
	}
	/**
	 * Returns the element if it is a non-Null value. Otherwise NoSucheElementException
	 * @return
	 * @throws NoSuchElementException
	 */
	
	public E get() throws NoSuchElementException{
		if(this.element == null){
			throw new NoSuchElementException();
		}else{
			return this.element;
		}
	}
	/**
	 * Return true if the element is a non-Null value
	 * @return
	 */
	public boolean isPresent(){
		return this.element.equals(null);
	}
	/**
	 * If the value is present returns the value otherwise returns other
	 * @param other
	 * @return
	 */
	
	public E orElse(E other){
		if(this.isPresent()){
			return this.element;
		}else{
			return other;
		}
	}
	/**
	 * Returns a String about the object
	 */
	public String toString(){
		String r_v = "Optional[ "
				+ this.element.toString() 
				+ " ] ";
		return r_v;
	}
	
	/**
	 * If a value is present, and the value matches the given predicate, return an Optional describing the value, 
	 * otherwise return an empty Optional.
	 * @param predicate
	 * @return
	 */
	
	public boolean filter(Predicate<? super E> predicate){
		return predicate.test(this.element);
	}
	/*
	 * Private class to set the element
	 */
	private void setElement(E elem){
		this.element = elem;
	}
	
	

}
