package co.aulatech.cari_find;

import android.content.res.Configuration;
import com.asha.vrlib.MDVRLibrary;

public class MediaPlayer extends MediaPlayerActivity {

    private MDVRLibrary mVRLibrary;

    public MediaPlayer(MDVRLibrary mVRLibrary) {
        this.mVRLibrary = mVRLibrary;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRLibrary.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRLibrary.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRLibrary.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mVRLibrary.onOrientationChanged(this);
    }
}
