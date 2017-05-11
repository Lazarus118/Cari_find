package co.aulatech.cari_find;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView electricity, visit_times, current_weather, area_code, currency;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private char[] alphabetlist;
    TickerView tickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CariFind");
        setSupportActionBar(toolbar);
        /*******************************************************/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*******************************************************/
        // -------------------------------------------
        checkAndRequestPermissions();
        // -------------------------------------------
        tickerView = (TickerView) findViewById(R.id.tickerView);
        setRandomText();
        // -------------------------------------------
        electricity = (TextView) findViewById(R.id.electricity_data);
        visit_times = (TextView) findViewById(R.id.visit_times_data);
        current_weather = (TextView) findViewById(R.id.current_weather_data);
        area_code = (TextView) findViewById(R.id.area_code_data);
        currency = (TextView) findViewById(R.id.currency_data);
        // -------------------------------------------
        ImageView dominica = (ImageView) findViewById(R.id.dominica);
        dominica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dominica = new Intent(ActivityNavigation.this, ActivityMain.class);
                ActivityNavigation.this.startActivity(dominica);
                // -------------------------------------------
                TextView dt = (TextView) findViewById(R.id.dominica_txt);
                dt.setTextColor(Color.parseColor("#9fc9d6"));
            }
        });
        dominica.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                electricity.setText("240V/60hz");
                visit_times.setText("SPRING");
                current_weather.setText("30C/99F");
                area_code.setText("767");
                currency.setText("1 XCD = 2.7 USD");
                return false;
            }
        });
        // -------------------------------------------
        ImageView guadeloupe = (ImageView) findViewById(R.id.guadeloupe);
        guadeloupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView gt = (TextView) findViewById(R.id.guadeloupe_txt);
                gt.setTextColor(Color.parseColor("#9fc9d6"));
            }
        });
        guadeloupe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                electricity.setText("110V/50hz");
                visit_times.setText("SUMMER");
                current_weather.setText("30C/99F");
                area_code.setText("590");
                currency.setText("1 EUR = 1.09 USD");
                return false;
            }
        });
        // -------------------------------------------
        ImageView martinique = (ImageView) findViewById(R.id.martinique);
        martinique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mt = (TextView) findViewById(R.id.martinique_txt);
                mt.setTextColor(Color.parseColor("#9fc9d6"));
            }
        });
        martinique.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                electricity.setText("110V/50hz");
                visit_times.setText("WINTER");
                current_weather.setText("30C/99F");
                area_code.setText("590");
                currency.setText("1 EUR = 1.09 USD");
                return false;
            }
        });
        // -------------------------------------------
        ImageView st_lucia = (ImageView) findViewById(R.id.st_lucia);
        st_lucia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mt = (TextView) findViewById(R.id.st_lucia_txt);
                mt.setTextColor(Color.parseColor("#9fc9d6"));
            }
        });
        st_lucia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                electricity.setText("240V/60hz");
                visit_times.setText("AUTUMN");
                current_weather.setText("30C/99F");
                area_code.setText("758");
                currency.setText("1 XCD = 2.7 USD");
                return false;
            }
        });
    }

    /**********************************************************************************
     * TICKER LOGIC
     *********************************************************************************/
    public void setRandomText() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alphabetlist = new char[53];
                        alphabetlist[0] = TickerUtils.EMPTY_CHAR;
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 26; j++) {
                                // Add all lowercase characters first, then add the uppercase characters.
                                alphabetlist[1 + i * 26 + j] = (char) ((i == 0) ? j + 97 : j + 65);
                            }
                        }
                        tickerView.setCharacterList(alphabetlist);
                        String[] ticker_content = {"Bahamas opposition wins landslide election victory", "Guyana tourism hub potential is at Ogle not Cheddi Jagan airport, study reveals", "Royal Caribbean opens first year-long program to Cuba"};
                        int idx = new Random().nextInt(ticker_content.length);
                        Random r = new Random();
                        tickerView.setText(generateChars(r, alphabetlist, 0) + ticker_content[idx]);
                        handler.postDelayed(this, 2000);
                    }
                });
            }
        }, 2000);
    }

    private String generateChars(Random random, char[] list, int numDigits) {
        final char[] result = new char[numDigits];
        for (int i = 0; i < numDigits; i++) {
            result[i] = list[random.nextInt(list.length)];
        }
        return new String(result);
    }


    /**********************************************************************************
     * ASK FOR PERMISSION LOGIC
     *********************************************************************************/
    private boolean checkAndRequestPermissions() {
        int loc = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    /**********************************************************************************
     * CONTACT US CLICK LOGIC
     *********************************************************************************/
    public void email_click(View view) {
        String to_email = "info@budgeat.co";
        String subject = "new Support message from BudgEat Business";
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        // need this to prompts email client only
        // --------------------------------------
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
    public void whatsapp_click(View view) {
        int number = 2557815;
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
    public void call_click(View view) {
        String PhoneNum = "255-7815";
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+Uri.encode(PhoneNum.trim())));
        startActivity(callIntent);
    }

    /**********************************************************************************
     * ABOUT DIALOG
     *********************************************************************************/
    public void about_dialog() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.activity_about_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityNavigation.this);

        // set activity_prompt_name.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                // result.setText(userInput.getText());
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            // Handle the camera action
            about_dialog();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
