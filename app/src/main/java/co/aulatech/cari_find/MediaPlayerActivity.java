package co.aulatech.cari_find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MediaPlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
        intent.setAction( Constants.ACTION_PLAY );
        startService( intent );
    }
}