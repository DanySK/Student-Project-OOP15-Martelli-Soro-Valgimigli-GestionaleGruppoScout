package extra.sito;

import java.net.MalformedURLException;
import java.net.URL;
/**
 * 
 * @author Riccardo Soro
 * a simple enumeration that contain all the possible link option to the event of the site http://buonacassia.net
 */
public enum Regioni { 
	
	NAZIONALE       ("http://buonacaccia.net/Events.aspx?CID=20"),
	ABRUZZO         ("http://buonacaccia.net/Events.aspx?RID=B&CID=20"), 
	BASILICATA      ("http://buonacaccia.net/Events.aspx?RID=C&CID=20"), 
	CALABRIA        ("http://buonacaccia.net/Events.aspx?RID=D&CID=20"),
	CAMPANIA        ("http://buonacaccia.net/Events.aspx?RID=E&CID=20"), 
	EMILIA_ROMAGNA  ("http://buonacaccia.net/Events.aspx?RID=F&CID=20"), 
	FRIULI          ("http://buonacaccia.net/Events.aspx?RID=G&CID=20"), 
	LAZIO           ("http://buonacaccia.net/Events.aspx?RID=H&CID=20"), 
	LIGURIA         ("http://buonacaccia.net/Events.aspx?RID=I&CID=20"), 
	LOMBARDIA       ("http://buonacaccia.net/Events.aspx?RID=L&CID=20"), 
	MARCHE          ("http://buonacaccia.net/Events.aspx?RID=M&CID=20"), 
	MOLISE          ("http://buonacaccia.net/Events.aspx?RID=N&CID=20"), 
	PIEMONTE        ("http://buonacaccia.net/Events.aspx?RID=O&CID=20"), 
	PUGLIA          ("http://buonacaccia.net/Events.aspx?RID=P&CID=20"), 
	SARDEGNA        ("http://buonacaccia.net/Events.aspx?RID=Q&CID=20"), 
	SICILIA         ("http://buonacaccia.net/Events.aspx?RID=R&CID=20"), 
	TOSCANA         ("http://buonacaccia.net/Events.aspx?RID=S&CID=20"), 
	TRENTINO        ("http://buonacaccia.net/Events.aspx?RID=T&CID=20"),
	UMBRIA          ("http://buonacaccia.net/Events.aspx?RID=U&CID=20"), 
	VALLE_D_AOSTA   ("http://buonacaccia.net/Events.aspx?RID=V&CID=20"),
	VENETO          ("http://buonacaccia.net/Events.aspx?RID=Z&CID=20")
	;
	
	private String link;
	
	private Regioni(final String link){
		this.link=link;
	}
	/**
	 * 
	 * @return the URL to the page
	 * @throws MalformedURLException 
	 * if no protocol is specified, or an unknown protocol is found,or spec is null.
	 */
	public final URL getUrl() throws MalformedURLException{
		return new URL(this.link);
	}
}
