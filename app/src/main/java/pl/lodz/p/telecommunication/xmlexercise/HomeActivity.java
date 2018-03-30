package pl.lodz.p.telecommunication.xmlexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int year_to_search;// zmienna przechowująca wybrany rok
    private int month_to_search;// zmienna przechowująca wybrany rok
    private int day_to_search;// zmienna przechowująca wybrany rok
    private String[] month;// tablica miesiecy do wyboru w danym roku
    private String[] day;// tablica dni do wyboru w danym miesiacu
    private String[] year = {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015"};

    private TextView[] textView = new TextView[11];// TextView do wyswietlania danych z pliku xml we fragmencie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // powiazanie zmiennych typu TextView z TextView w pliku widoku fragmentu (fragment_search_results.xml)
        textView[0] = findViewById(R.id.text_fragment_1);
        textView[1] = findViewById(R.id.text_fragment_2);
        textView[2] = findViewById(R.id.text_fragment_3);
        textView[3] = findViewById(R.id.text_fragment_4);
        textView[4] = findViewById(R.id.text_fragment_5);
        textView[5] = findViewById(R.id.text_fragment_6);
        textView[6] = findViewById(R.id.text_fragment_7);
        textView[7] = findViewById(R.id.text_fragment_8);
        textView[8] = findViewById(R.id.text_fragment_9);
        textView[9] = findViewById(R.id.text_fragment_10);
        textView[10] = findViewById(R.id.text_fragment_11);

        // Spinner - rozwijana lista z latami do wyboru, powiazanie jej z lista w pliku widoku (activity_home.xml)
        Spinner spinner_year = (Spinner) findViewById(R.id.spinner_year);
        String[] year = {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015"};
        ArrayAdapter adapter_year = new ArrayAdapter(this, android.R.layout.simple_spinner_item, year);// adapter przechowujacy tablice lat dla spinnera
        spinner_year.setAdapter(adapter_year);// przypisanie adaptera do spinnera
        // metoda spinnera nasluchujaca zdarzen na spinnerze z latami
        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override// metoda wywolywana po wybraniu roku
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year_to_search = 0;
                month_to_search = 0;
                day_to_search = 0;
                month = new String[]{""};
                day = new String[]{""};
                switch ((int) l) {//position
                    case 0://1998
                        month = new String[]{"02", "04", "05", "07", "09", "10", "12"};
                        year_to_search = 1998;
                        break;
                    case 1://1999
                        month = new String[]{"01", "09", "11"};
                        year_to_search = 1999;
                        break;
                    case 2://2000
                        month = new String[]{"02", "08"};
                        year_to_search = 2000;
                        break;
                    case 3://2001
                        month = new String[]{"03", "06", "08", "10", "11", "12"};
                        year_to_search = 2001;
                        break;
                    case 4://2002
                        month = new String[]{"01", "04", "05", "06", "08", "09", "10", "11"};
                        year_to_search = 2002;
                        break;
                    case 5://2003
                        month = new String[]{"01", "02", "03", "04", "05", "06"};
                        year_to_search = 2003;
                        break;
                    case 6://2004
                        month = new String[]{"07", "08"};
                        year_to_search = 2004;
                        break;
                    case 7://2005
                        month = new String[]{"03", "04", "06", "07", "09"};
                        year_to_search = 2005;
                        break;
                    case 8://2006
                        month = new String[]{"02", "03"};
                        year_to_search = 2006;
                        break;
                    case 9://2007
                        month = new String[]{"04", "06", "08", "11"};
                        year_to_search = 2007;
                        break;
                    case 10://2008
                        month = new String[]{"01", "02", "03", "06", "11", "12"};
                        year_to_search = 2008;
                        break;
                    case 11://2009
                        month = new String[]{"01", "02", "03", "06"};
                        year_to_search = 2009;
                        break;
                    case 12://2010
                        month = new String[]{"01"};
                        year_to_search = 2010;
                        break;
                    case 13://2011
                        month = new String[]{"01", "04", "05", "06"};
                        year_to_search = 2011;
                        break;
                    case 14://2012
                        month = new String[]{"05", "11", "12"};
                        year_to_search = 2012;
                        break;
                    case 15://2013
                        month = new String[]{"01", "02", "03", "05", "06", "07"};
                        year_to_search = 2013;
                        break;
                    case 16://2014
                        month = new String[]{"10"};
                        year_to_search = 2014;
                        break;
                    case 17://2015
                        month = new String[]{"03"};
                        year_to_search = 2015;
                        break;
                }
                // Spinner - rozwijana lista z miesiacami do wyboru, powiazanie jej z lista w pliku widoku (activity_home.xml)
                Spinner spinner_month = (Spinner) findViewById(R.id.spinner_month);
                ArrayAdapter adapter_month = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, month);// adapter na miesiace
                spinner_month.setAdapter(adapter_month);// przypisanie adaptera do spinnera
                // metoda spinnera nasluchujaca zdarzen na spinnerze z miesiacami
                spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override // metoda wywolywana po wybraniu miesiaca
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        month_to_search = Integer.parseInt(month[(int) l]);
                        if ((year_to_search == 2004 && month_to_search == 7) || (year_to_search == 2001 && month_to_search == 3)) {
                            day = new String[]{"01", "29"};
                        } else {
                            day = new String[]{""};
                        }
                        // Spinner - rozwijana lista z dniami do wyboru, powiazanie jej z lista w pliku widoku (activity_home.xml)
                        Spinner spinner_day = (Spinner) findViewById(R.id.spinner_day);
                        ArrayAdapter adapter_day = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_spinner_item, day);//adapter na dni
                        spinner_day.setAdapter(adapter_day);// przypisanie adaptera do spinnera
                        // metoda spinnera nasluchujaca zdarzen na spinnerze z dmiami
                        spinner_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override// metoda wywolywana po wybraniu dnia
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (day[(int) l] != "")
                                    day_to_search = Integer.parseInt(day[(int) l]);
                                else
                                    day_to_search = 0;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_welcome_screen) {
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);//wywolywanie activity ekranu powitania po wyborze odpowiedniej opcji z menu
        } else if (id == R.id.nav_about_screen) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);//wywolywanie activity o programie po wyborze odpowiedniej opcji z menu
        } else if (id == R.id.nav_exit) {//zamykanie aplikacji
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onSearchButtonClick(View view) {
        Fragment fragment;// utworzenie obiektu fragmentu - czesci activity_home w ktorej wyswietlane sa wyniki
        FragmentManager fm = getFragmentManager();// Tworzenie obiektu fm klasy FragmentManager - obiekt ten sluzy do zarzadzania obiektem fragment
        FragmentTransaction ft = fm.beginTransaction();// Rozpoczecie tranzakcji - serii operacji na fragmencie wykonywanych poprzez FragmentManager
        fragment = new SearchResults();// Przypisanie do zmiennej fragment obiektu klasy SearchResults - klasa ta obsluguje widok fragmentu
        ft.replace(R.id.search_results, fragment);// zastapienie istniejacego fragmentu nowym;
        Parser parser = new Parser(textView, year_to_search, month_to_search, day_to_search);// Utowrzenie obiektu parser klasy Parser
        // Nastepuje w nim polaczenie z internetem, parsowanie pliku XML z danymi i zaktualizowanie TextView we fragmencie
        parser.execute(this);// "Uruchomienie" parsera - metoda doInBackground, przekazanie do tej metody odwolania do klast HomeActivity
        ft.commit(); //zatwierzenie zmian we fragmencie - wpisanie tekstow do TextView
    }
}
