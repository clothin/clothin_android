package si.cit.clothingorigin;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import si.cit.clothingorigin.Activities.BaseActivity;
import si.cit.clothingorigin.Activities.CodeScannerActivity;
import timber.log.Timber;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Main application class
 */
public class CitApp extends Application{

    private static final String ApiUserAgentBase = "cit-";

    private static CitApp instance = null;

    private BaseActivity activeActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();

        setInstance(this);

        Timber.plant(new Timber.DebugTree());

        Timber.i("onCreate");
    }

    public static CitApp getInstance() {
        return instance;
    }

    public static void setInstance(CitApp instance){
        CitApp.instance=instance;
    }

    public void setActiveActivity(BaseActivity activity){
        activeActivity = activity;
    }

    public String getUserAgent(){
        try {
            int appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            return ApiUserAgentBase+"A"+String.valueOf(appVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean hasCameraPermissions(){
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PERMISSION_GRANTED;
    }
}
