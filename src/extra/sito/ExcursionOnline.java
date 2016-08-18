package extra.sito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import model.Excursion;

public interface ExcursionOnline extends Excursion {
	/**
	 * 
	 * @return the google map link to the place of the event
	 * @throws MalformedURLException 
	 */
	URL getMapLink() throws MalformedURLException;

	/**
	 * 
	 * @return the link to the single event of the categori piccoleorme
	 */
	URL getPiccoleOrmeUrl();

	/**
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 */
	void openPiccoleOrmeUrl() throws IOException, URISyntaxException;

}
