package control.myUtil;

import java.io.Serializable;
/**
 * Classic Pair class
 * @author Valgio
 *
 * @param <X>
 * @param <Y>
 */

public class Pair<X, Y> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2192122196852934451L;
	private X x;
	private Y y;
	
	public Pair(final X x, final Y y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(X x){
		this.x = x;
	}
	
	public void setY(Y y){
		this.y = y;
	}
	
	public X getX(){
		return this.x;
	}
	
	public Y getY(){
		return this.y;
	}
}
