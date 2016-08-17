package extra.sito;

import java.net.MalformedURLException;
import java.net.URL;

public enum Regioni { 
	
	NAZIONALE("http://buonacaccia.net/Events.aspx&CID=11"),
	ABRUZZO("http://buonacaccia.net/Events.aspx?RID=B&CID=11"), 
	BASILICATA("http://buonacaccia.net/Events.aspx?RID=C&CID=11"), 
	CALABRIA ("http://buonacaccia.net/Events.aspx?RID=D&CID=11"),
	CAMPANIA("http://buonacaccia.net/Events.aspx?RID=E&CID=11"), 
	EMILIA_ROMAGNA("http://buonacaccia.net/Events.aspx?RID=F&CID=11"), 
	FRIULI("http://buonacaccia.net/Events.aspx?RID=G&CID=11"), 
	LAZIO("http://buonacaccia.net/Events.aspx?RID=H&CID=11"), 
	LIGURIA("http://buonacaccia.net/Events.aspx?RID=I&CID=11"), 
	LOMBARDIA("http://buonacaccia.net/Events.aspx?RID=L&CID=11"), 
	MARCHE("http://buonacaccia.net/Events.aspx?RID=M&CID=11"), 
	MOLISE("http://buonacaccia.net/Events.aspx?RID=N&CID=11"), 
	PIEMONTE("http://buonacaccia.net/Events.aspx?RID=O&CID=11"), 
	PUGLIA("http://buonacaccia.net/Events.aspx?RID=P&CID=11"), 
	SARDEGNA("http://buonacaccia.net/Events.aspx?RID=Q&CID=11"), 
	SICILIA("http://buonacaccia.net/Events.aspx?RID=R&CID=11"), 
	TOSCANA("http://buonacaccia.net/Events.aspx?RID=S&CID=11"), 
	TRENTINO("http://buonacaccia.net/Events.aspx?RID=T&CID=11"),
	UMBRIA("http://buonacaccia.net/Events.aspx?RID=U&CID=11"), 
	VALLE_D_AOSTA("http://buonacaccia.net/Events.aspx?RID=V&CID=11"),
	VENETO("http://buonacaccia.net/Events.aspx?RID=Z&CID=11");
	
	private String link;
	
	private Regioni(final String link){
		this.link=link;
	}
	public final URL getUrl() throws MalformedURLException{
		return new URL(this.link);
	}
}
