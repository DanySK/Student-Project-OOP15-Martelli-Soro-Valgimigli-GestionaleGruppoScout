package extra.sito;

import java.net.MalformedURLException;
import java.net.URL;

import model.Excursion;

public interface ExcursionOnline extends Excursion{
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

}
