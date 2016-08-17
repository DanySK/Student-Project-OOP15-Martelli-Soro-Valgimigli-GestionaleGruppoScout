package extra.sito;

import java.time.LocalDate;

import model.ExcursionImpl;
import model.exception.IllegalDateException;

public class ExcursionOnlineImpl extends ExcursionImpl implements ExcursionOnline {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcursionOnlineImpl(final LocalDate dateStart, final String name, final LocalDate dateEnd,
			final Double prize, final String place) throws IllegalDateException {
		super(dateStart, name);
		this.setDateEnd(dateEnd);
		this.setPlace(place);
		this.setPrice(prize.intValue());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {

	}
	
	
}
