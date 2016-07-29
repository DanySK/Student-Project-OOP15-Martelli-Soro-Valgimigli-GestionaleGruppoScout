package model;

public interface Uscita extends Excursion{
	/**
	 * 
	 * @return
	 */
	public Reparto getReparto();

	/**
	 * 
	 * @param reparto
	 */
	public void setReparto(Reparto reparto);
}
