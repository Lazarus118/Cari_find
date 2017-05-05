package co.aulatech.cari_find;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.asha.vrlib.MD360Director;
import com.asha.vrlib.MD360DirectorFactory;
import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.BarrelDistortionConfig;
import com.asha.vrlib.model.MDPinchConfig;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SurfaceHolder.Callback {

    public MDVRLibrary mVRLibrary;
    MediaPlayer media_player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CariFind | DOMINICA");
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
        getWindow().setFormat(PixelFormat.UNKNOWN);
        SurfaceView mPreview = (SurfaceView) findViewById(R.id.surface_view);
        SurfaceHolder surfaceHolder = mPreview.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(500, 800);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        media_player = MediaPlayer.create(this, R.raw.demo);
        media_player.setLooping(true);
        media_player.start();
        /*******************************************************/
        // init VR Library
        initVRLibrary();
        /*******************************************************/
        SearchView search = (SearchView) findViewById(R.id.search);
        search.setQueryHint("What can we find for you?");
        /*******************************************************/
        final ImageView mute_toggle = (ImageView) findViewById(R.id.mute);
        mute_toggle.setTag(1);
        media_player.setVolume(0,0);
        mute_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mute_toggle.getTag().equals(1)) {
                    mute_toggle.setImageResource(R.drawable.ic_un_mute);
                    media_player.setVolume(1,1);
                    mute_toggle.setTag(2);
                } else if (mute_toggle.getTag().equals(2)) {
                    mute_toggle.setImageResource(R.drawable.ic_mute);
                    media_player.setVolume(0,0);
                    mute_toggle.setTag(1);
                }
            }
        });
        /*******************************************************/
        final LinearLayout near_me = (LinearLayout) findViewById(R.id.near_me);
        final TextView near_me_txt = (TextView) findViewById(R.id.near_me_txt);
        final ColorStateList near_me_txt_default_color =  near_me_txt.getTextColors();
        near_me.setTag(1);
        near_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (near_me.getTag().equals(1)) {
                    near_me_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    near_me.setBackgroundColor(Color.parseColor("#F2D388"));
                    near_me.setTag(2);
                } else if (near_me.getTag().equals(2)) {
                    near_me_txt.setTextColor(near_me_txt_default_color);
                    near_me.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    near_me.setTag(1);
                }
            }
        });
        // ----------------------------------------------------------------
        final LinearLayout sites = (LinearLayout) findViewById(R.id.sites);
        final TextView sites_txt = (TextView) findViewById(R.id.sites_txt);
        final ColorStateList sites_txt_default_color =  sites_txt.getTextColors();
        sites.setTag(1);
        sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sites.getTag().equals(1)) {
                    sites_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    sites.setBackgroundColor(Color.parseColor("#F2D388"));
                    Intent sites_to_content_category = new Intent(ActivityMain.this, ActivityCategoryContent.class);
                    ActivityMain.this.startActivity(sites_to_content_category);
                    sites.setTag(2);
                } else if (sites.getTag().equals(2)) {
                    sites_txt.setTextColor(sites_txt_default_color);
                    sites.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    sites.setTag(1);
                }
            }
        });
        // ----------------------------------------------------------------
        final LinearLayout beaches = (LinearLayout) findViewById(R.id.beaches);
        final TextView beaches_txt = (TextView) findViewById(R.id.beaches_txt);
        final ColorStateList beaches_txt_default_color =  beaches_txt.getTextColors();
        beaches.setTag(1);
        beaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beaches.getTag().equals(1)) {
                    beaches_txt.setTextColor(Color.parseColor("#FFFFFF"));
                    beaches.setBackgroundColor(Color.parseColor("#F2D388"));
                    beaches.setTag(2);
                } else if (beaches.getTag().equals(2)) {
                    beaches_txt.setTextColor(beaches_txt_default_color);
                    beaches.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    beaches.setTag(1);
                }
            }
        });
        // ----------------------------------------------------------------
        final LinearLayout hotels = (LinearLayout) findViewById(R.id.hotels);
        hotels.setTag(1);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hotels.getTag().equals(1)) {
                    hotels.setBackgroundColor(Color.parseColor("#F2D388"));
                    hotels.setTag(2);
                } else if (hotels.getTag().equals(2)) {
                    hotels.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    hotels.setTag(1);
                }
            }
        });
        // ----------------------------------------------------------------
        final LinearLayout restaurants = (LinearLayout) findViewById(R.id.restaurants);
        restaurants.setTag(1);
        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (restaurants.getTag().equals(1)) {
                    restaurants.setBackgroundColor(Color.parseColor("#F2D388"));
                    restaurants.setTag(2);
                } else if (restaurants.getTag().equals(2)) {
                    restaurants.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    restaurants.setTag(1);
                }
            }
        });
        // ----------------------------------------------------------------
        final LinearLayout super_markets = (LinearLayout) findViewById(R.id.super_markets);
        super_markets.setTag(1);
        super_markets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (super_markets.getTag().equals(1)) {
                    super_markets.setBackgroundColor(Color.parseColor("#F2D388"));
                    super_markets.setTag(2);
                } else if (super_markets.getTag().equals(2)) {
                    super_markets.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    super_markets.setTag(1);
                }
            }
        });
    }

    /**********************************************************************************
     * 360 VR MODULE
     *********************************************************************************/
    private void initVRLibrary(){
        // new instance
        mVRLibrary = MDVRLibrary.with(this)
                .motionDelay(SensorManager.SENSOR_DELAY_GAME)
                .displayMode(MDVRLibrary.DISPLAY_MODE_NORMAL)
                .interactiveMode(MDVRLibrary.INTERACTIVE_MODE_MOTION)
                .asVideo(new MDVRLibrary.IOnSurfaceReadyCallback() {
                    @Override
                    public void onSurfaceReady(Surface surface) {
                        // IjkMediaPlayer or MediaPlayer
                        media_player.setSurface(surface);
                    }
                })
                .pinchConfig(new MDPinchConfig().setMin(1.0f).setMax(8.0f).setDefaultValue(0.1f))
                .pinchEnabled(true)
                .directorFactory(new MD360DirectorFactory() {
                    @Override
                    public MD360Director createDirector(int index) {
                        return MD360Director.builder().setPitch(90).build();
                    }
                })
                .barrelDistortionConfig(new BarrelDistortionConfig().setDefaultEnabled(false).setScale(0.95f))
                .build(R.id.surface_view);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        media_player.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        media_player.setDisplay(null);
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityMain.this);

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
