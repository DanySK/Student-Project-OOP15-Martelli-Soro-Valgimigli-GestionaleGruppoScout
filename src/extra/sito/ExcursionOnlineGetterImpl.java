package extra.sito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.exception.IllegalDateException;

public final class ExcursionOnlineGetterImpl implements ExcursionOnlineGetter{
	
	private ExcursionOnlineGetterImpl(){
		
	}
	
	public static List<ExcursionOnline> getExcursion(final Regioni regione) throws IllegalDateException, IOException,MalformedURLException {
		final List<ExcursionOnline> result = new ArrayList<>();
		final URL url = regione.getUrl();
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		final BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = read.readLine();
		String html = "";	
		while (line != null) {
			
			html += line;
			line = read.readLine();
		}
		read.close();
		connection.disconnect();
		String nome;
		LocalDate dataInizio;
		LocalDate dataFine;
		String luogo;
		Double prezzo;
		URL link;
		final List<String> tmp = Arrays.asList(html.split("\\?e="));
	
		for (int a = 1; a < tmp.size(); a++) {
			
			final List<String> tmp2 = Arrays.asList(tmp.get(a).substring(6).replaceAll("<([^<]*)>", "$1")
					.replaceAll("/a/tdtd style=\"width:([0-9]*)px;\" span id=\"MainContent_EventsGridView_Type_([0-9]*)", " ").replaceAll("PO", "")
					.split("\""));
			nome = tmp2.get(0);
			link=new URL("http://buonacaccia.net/event.aspx?e="+tmp.get(a).substring(0,4));
			String[] vet = tmp2.get(3).replaceAll("/tdtd style=", "").split("/");
			dataInizio = LocalDate.of(Integer.parseInt(vet[2]), Integer.parseInt(vet[1]), Integer.parseInt(vet[0]));
			final List<String> tmp3 = Arrays.asList((tmp.get(a).split(tmp2.get(3).replaceAll("/tdtd style=", ""),2)));
			tmp3.forEach(e->{
				System.out.println(tmp2.get(3).replaceAll("/tdtd style=", ""));
			});
			vet = tmp3.get(1).substring(29, 39).split("/");
			dataFine = LocalDate.of(Integer.parseInt(vet[2]), Integer.parseInt(vet[1]), Integer.parseInt(vet[0]));
			luogo = tmp3.get(1).replaceAll("<([^>]*)>", "    ").split("                  ")[1];
			if (tmp3.get(1).replaceAll("<([^>]*)>", "    ").split("                  ")[0].split("             ")[1]
					.equals("-")) {
				prezzo = 0.0;
			} else {
				prezzo = Double.parseDouble(tmp3.get(1).replaceAll("<([^>]*)>", "    ").split("                  ")[0]
						.split("             ")[1].substring(4).replace(',', '.'));
			}
			result.add(new ExcursionOnlineImpl(dataInizio, nome, dataFine, prezzo, luogo,link));
		}

		return result;
	}
}
