package pl.lodz.p.telecommunication.xmlexercise;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser extends AsyncTask<AppCompatActivity, Void, String[]> {

    private AppCompatActivity act;// zmienna AppCompatActivity - uchwyt do HomeActivity
    private TextView[] textView;// tablica textView do wyswietlania danych we fragmencie
    private String[] data = new String[11];// tablica do ktorej wczytujemy dane z pliku
    private int year_to_search;// zmienna z rokiem do wyszukania w pliku xml
    private int month_to_search;// zmienna z miesiacem do wyszukania w pliku xml
    private int day_to_search;// zmienna z dniem do wyszukania w pliku xml
    private String year_to_compare;// zmienna przechowujaca rok z pliku xml
    private String month_to_compare;// zmienna przechowujaca miesiac z pliku xml
    private String day_to_compare;// zmienna przechowujaca dzien z pliku xml
    private String dateToCompare;// zmienna do ktorej pobieramy date z pliku xml
    private boolean stop_parsing;// zmienna logiczna zatrzymujaca parsowanie danych

    public Parser(TextView[] textView, int year_to_search, int month_to_search, int day_to_search) {
        this.textView = textView;// przypisanie textView z HomeActivity do textView z klasy Parser
        this.year_to_search = year_to_search;// przypisanie year_to_search z HomeActivity do year_to_search z klasy Parser
        this.month_to_search = month_to_search;// przypisanie month_to_search z HomeActivity do month_to_search z klasy Parser
        this.day_to_search = day_to_search;// przypisanie day_to_search z HomeActivity do day_to_search z klasy Parser
        this.stop_parsing = false;//przypisanie do zmiennej logicznej stop_parsing wartosci false, co umozliwia wyszukiwanie odpowiednich danych
        // w pliku
    }

    @Override
    protected String[] doInBackground(AppCompatActivity... voids) {
        act = voids[0];// przypisanie do zmiennej act pierwszego argumentu metody doInBackground - uchwyt do HomeActivity

        try {
            URL url = new URL("http://www.nbp.pl/xml/stopy_procentowe_archiwum.xml");//tworzenie obiektu klasy URL - wskazanie na plik xml
            //znajdujacy sie na stronie http://www.nbp.pl/xml/stopy_procentowe_archiwum.xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// umożliwia aplikacjom uzyskanie parsera generującego drzewa obiektów DOM z dokumentów XML
            DocumentBuilder db = dbf.newDocumentBuilder();// pozwala uzyskac objekt klasy Document z pliku XML
            Document doc = db.parse(new InputSource(url.openStream()));// tworzenie obiektu klasy Document - reprezentuje on plik XML
            doc.getDocumentElement().normalize();//"normalizuje" document, np: usuwa niepotrzebne spacje

            NodeList nodeList = doc.getElementsByTagName("pozycje");// wczytanie listy wezlow(NodeList) oznaczonych tagiem "pozycje"

            int i = 0;// licznik do wczytywania kolejnych wezlow oznaczonych tagiem "pozycje"

            while ((!stop_parsing) && (i<nodeList.getLength())) {//wykonuj dopoki nie znajdziesz odpowiednich danych zwiazanych
                //z wybrana data, lub dopoki nie przeszukasz calego pliku

                Node node = nodeList.item(i);// pobranie pojedynczego wezla oznaczonego tagiem "pozycje"
                Element fstElmt = (Element) node;// rzutowanie wezla node na element dokumentu xml

                dateToCompare = fstElmt.getAttribute("obowiazuje_od");// przypisanie do zmiennej dateToCompare atrybutu zawierajacego
                //date z ktorej pochodza dane

                year_to_compare = dateToCompare.substring(0, 4);// pobranie roku ze zmiennej dateToCompare
                month_to_compare = dateToCompare.substring(5, 7);// pobranie miesiaca ze zmiennej dateToCompare
                day_to_compare = dateToCompare.substring(8, 10);// pobranie dnia ze zmiennej dateToCompare

                // parsuj dane jesli sa one oznaczone tagiem "pozycje" ktorego atrybut "obowiazuje_od" jest rowny wybranej dacie
                if ((Integer.parseInt(year_to_compare) == year_to_search && Integer.parseInt(month_to_compare) == month_to_search && Integer.parseInt(day_to_compare) == day_to_search && (day_to_search == 29 || day_to_search == 1)) || (Integer.parseInt(year_to_compare) == year_to_search && Integer.parseInt(month_to_compare) == month_to_search && day_to_search == 0)) {

                    stop_parsing = true;// przypisanie do zmiennej stop_parsing wartosci tue - spowoduje to zatrzymanie szukania po parsowaniu
                    int k = 0;// licznik do wczytywania kolejnych wezlow oznaczonych tagiem "pozycja"
                    NodeList pozycjaList = fstElmt.getElementsByTagName("pozycja");// wczytanie listy wezlow(NodeList) oznaczonych tagiem "pozycja"
                    Element pozycjaElement;
                    data[0] = "Data:" + dateToCompare;
                    while(k<pozycjaList.getLength()){
                        pozycjaElement = (Element) pozycjaList.item(k);// rzutowanie wezla node na element dokumentu xml
                            data[2*k+1] = "Rodzaj stopy: " + pozycjaElement.getAttribute("id");// przypisanie do odpowiedniego elementu
                            //tablicy "data" wartosci atrybutu "oprocentowanie" z tagu "pozycja"
                            data[2*k+2] = "Oprocentowanie: " + pozycjaElement.getAttribute("oprocentowanie");// przypisanie do odpowiedniego elementu
                            //tablicy "data" wartosci atrybutu "oprocentowanie" z tagu "pozycja"
                        k++;
                    }
                }
                i++;// zwiekszenie licznika "i" o 1 - przejscie do kolejnego wezla tagu "pozycje"
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            act.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    //wyswietla "Toast", kiedy aplikacja nie uzyska polacenia z internetem
                    Toast.makeText(act,"Brak połączenia z internetem",Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(final String[] s) {
        super.onPostExecute(s);
        // wykonanie kody w watku glownym programu, czyli HomeActivity
        act.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    if (s[i] != null) {
                    textView[i].setText(s[i]);// przypisz do odpowiedniego elementu zmiennej textView tekst, jesli nie jest on pusty
                    } else {
                        textView[i].setText("");// jesli tekst jest pusty wyczysc odpowiadajacy mu textView
                    }
                }
            }
        });
    }
}