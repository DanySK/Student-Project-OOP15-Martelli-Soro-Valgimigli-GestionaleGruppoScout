package extra.sito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import model.escursioni.Excursion;

public interface ExcursionOnline extends Excursion {
	/**
	 * 
	 * @return the google map link to the place of the event
	 * @throws MalformedURLException 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	URL getMapLink() throws MalformedURLException, IOException, URISyntaxException;

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
/**
 * @throws URISyntaxException 
 * @throws IOException 
 * @throws MalformedURLException 
 * 
 */
	void openMapLink() throws MalformedURLException, IOException, URISyntaxException;

}
