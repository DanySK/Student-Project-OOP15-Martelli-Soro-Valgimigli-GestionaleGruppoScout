package extra.sito;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;

import model.ExcursionImpl;
import model.exception.IllegalDateException;

public class ExcursionOnlineImpl extends ExcursionImpl implements ExcursionOnline {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final URL link;

	public ExcursionOnlineImpl(final LocalDate dateStart, final String name, final LocalDate dateEnd,
			final Double prize, final String place, final URL link) throws IllegalDateException {
		super(dateStart, name);
		this.setDateEnd(dateEnd);
		this.setPlace(place);
		this.setPrice(prize.intValue());
		this.link = link;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {

	}

	@Override
	public URL getMapLink() throws MalformedURLException {
		return new URL("https://www.google.it/maps/place/" +(this.getPlace().replace(" ", "+")));
	}

	@Override
	public void openMapLink() throws MalformedURLException, IOException, URISyntaxException {
		this.openLink(this.getMapLink());
	}

	@Override
	public URL getPiccoleOrmeUrl() {
		return this.link;
	}

	@Override
	public void openPiccoleOrmeUrl() throws IOException, URISyntaxException {
		this.openLink(this.link);
	}

	private void openLink(final URL link) throws IOException, URISyntaxException {
		if (Desktop.isDesktopSupported()) {
			final Desktop desktop = Desktop.getDesktop();
			desktop.browse(link.toURI());
		} else {
			final Runtime runtime = Runtime.getRuntime();
			runtime.exec("xdg-open " + link);
		}
	}
}
