package si.cit.clothingorigin;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.google.gson.Gson;

import si.cit.clothingorigin.Blockchain.BlockchainConnector;
import si.cit.clothingorigin.Interfaces.ContractResultListener;
import si.cit.clothingorigin.Interfaces.ObjectDataChangeListener;
import si.cit.clothingorigin.Objects.Product;
import timber.log.Timber;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Main application class
 */
public class CitApp extends Application{

    private static final String ApiUserAgentBase = "cit-";

    private static CitApp instance = null;
    private BlockchainConnector mBlockchainConnector = null;

    @Override
    public void onCreate() {
        super.onCreate();

        setInstance(this);

        Timber.plant(new Timber.DebugTree());

        Timber.i("onCreate");

        try {
            mBlockchainConnector = new BlockchainConnector(this);
            String bcClientVersion = mBlockchainConnector.getRemoteClientVersion();
            Timber.i("Blockchain client: " + bcClientVersion);

            mBlockchainConnector.getProduct(3,new ContractResultListener() {
                @Override
                public void onContractResult(Object resultObject, boolean success) {
                    if(success) {
                        final Product product = (Product)resultObject;
                        product.fetchProductionChain(new ObjectDataChangeListener() {
                            @Override
                            public void onDataChange(Object object) {
                                Gson gson = new Gson();
                                Timber.i("Product chain result: " + gson.toJson(product));
                            }
                        });
                        Gson gson = new Gson();
                        Timber.i("Contract result: " + gson.toJson(product));
                    }else{
                        Timber.i("Contract result failed!");
                    }
                }
            });
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }
    }

    public static CitApp getInstance() {
        return instance;
    }

    public BlockchainConnector getBlockchainConnector(){
        return mBlockchainConnector;
    }

    public static void setInstance(CitApp instance){
        CitApp.instance=instance;
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
