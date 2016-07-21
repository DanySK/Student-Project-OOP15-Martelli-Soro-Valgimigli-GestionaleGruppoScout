package model;
import java.util.Date;
/**
 * @author riccardo
 *
 */
/**
 * An interface modelling a excursion
 *
 */
public interface Excursion {
	/**
	 * 
	 * @return an Integer contained the price of the excursion
	 */
	public Integer getPrize();
	/**
	 * 
	 * @return a String contained the place of the excursion
	 */
	public String getPlace();
	/**
	 * 
	 * @return a Date contained the start date excursion
	 */
	public Date getDateStart();
	/**
	 * 
	 * @return a Date contained the end date excursion
	 */
	public Date getDateEnd();
	/**
	 * set the price of the excursion
	 * @param prize
	 */
	public void setPrice(Integer prize);
	/**
	 * set the place of the excursion
	 * @param place
	 */
	public void setPlace(String place);
	/**
	 * set the start date excursion
	 * @param dateStart
	 */
	public void setDateStart(Date dateStart);
	/**
	 * set the end date excursion
	 * @param dateEnd
	 */
	public void setDateEnd(Date dateEnd);
	public Integer get
}


