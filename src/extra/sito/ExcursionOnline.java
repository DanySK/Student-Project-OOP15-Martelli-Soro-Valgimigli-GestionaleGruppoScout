package extra.sito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import model.escursioni.Excursion;

/**
 * 
 * @author Riccardo Soro
 *
 */
public interface ExcursionOnline extends Excursion {
	/**
	 * 
	 * @return the google map link to the place of the event
	 * @throws MalformedURLException
	 *             if the URL is not correct
	 */
	URL getMapLink() throws MalformedURLException;

	/**
	 * 
	 * @return the link to the single event
	 */
	URL getPiccoleOrmeUrl();

	/**
	 * open the default browser to the link of the single event
	 * 
	 * @throws URISyntaxException
	 *             if the link is not correct
	 * @throws IOException
	 *             if is impossible open the browser
	 * 
	 */
	void openPiccoleOrmeUrl() throws IOException, URISyntaxException;

	/**
	 * open the default browser to the gmap link
	 * 
	 * @throws URISyntaxException
	 *             if the link is not correct
	 * @throws IOException
	 *             if is impossible open the browser
	 * @throws MalformedURLException
	 *             if the link is not correct
	 * 
	 */
	void openMapLink() throws MalformedURLException, IOException, URISyntaxException;

}
