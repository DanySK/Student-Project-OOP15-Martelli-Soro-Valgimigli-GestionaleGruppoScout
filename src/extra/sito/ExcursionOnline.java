package extra.sito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import model.Excursion;

public interface ExcursionOnline extends Excursion {
	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	URL getMapLink() throws MalformedURLException;

	/**
	 * 
	 * @return
	 */
	URL getPiccoleOrmeUrl();

	/**
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * 
	 */
	void openPiccoleOrmeUrl() throws IOException, URISyntaxException;

}
