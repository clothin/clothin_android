package si.cit.clothingorigin.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import si.cit.clothingorigin.R;
import timber.log.Timber;

public class CodeScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    public static final int ACTIVITY_REQUEST_CODE_SCAN = 1;

    public static final int APP_CAMERA_PERMISSION_REQUEST_CODE = 0;

    private ZXingScannerView mScannerView;

    private boolean ending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);
        List<BarcodeFormat> formatList = new ArrayList<>();
        formatList.add(BarcodeFormat.QR_CODE);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        mScannerView.setAutoFocus(true);
        mScannerView.setFormats(formatList);
        setContentView(mScannerView);                // Set the scanner view as the content view

        setTitle("Product code scanner");
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!ending) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        String codeValue = rawResult.getText();

        Timber.i(rawResult.getText()); // Prints scan results
        Timber.i(rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Intent scanResult = new Intent();
        scanResult.putExtra("scan_data",codeValue);

        setResult(RESULT_OK,scanResult);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.code_scanner_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_fake_item_scan){
            ending = true;
            Intent scanResult = new Intent();
            scanResult.putExtra("scan_data","CLO_0");
            setResult(RESULT_OK,scanResult);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
