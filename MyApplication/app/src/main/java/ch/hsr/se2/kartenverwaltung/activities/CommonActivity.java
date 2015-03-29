package ch.hsr.se2.kartenverwaltung.activities;

import roboguice.activity.RoboActivity;
import android.view.Menu;
/**
 * Created by roberto on 29.03.15.
 */
public abstract class CommonActivity extends RoboActivity{


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
