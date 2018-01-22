package si.cit.clothingorigin.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import si.cit.clothingorigin.R;

/**
 *
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
        if(alertDialog!=null){
            alertDialog.dismiss();
            alertDialog=null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        hideLoadingDialog();
        hideAlertDialog();
        super.onDestroy();
    }

    public void showLoadingDialog(String message, boolean canDismiss){
        if(!isFinishing()) {
            progressDialog = new ProgressDialog(this);
            if (message != null) {
                progressDialog.setMessage(message);
            } else {
                progressDialog.setMessage(getString(R.string.default_loading_dialog_message));
            }
            progressDialog.setCanceledOnTouchOutside(canDismiss);
            progressDialog.show();
        }
    }

    public void hideLoadingDialog(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showAlertDialog(int resId){
        if(!isFinishing()) {
            alertDialog = new AlertDialog.Builder(this)
                    .setMessage(resId)
                    .setPositiveButton("Ok", null)
                    .create();
            alertDialog.show();
        }
    }

    public void showAlertDialog(String message){
        if(!isFinishing()) {
            alertDialog = new AlertDialog.Builder(this)
                    .setMessage(message)
                    .setPositiveButton("Ok",null)
                    .create();
            alertDialog.show();
        }
    }

    public void showAlertDialog(String title, String message){
        if(!isFinishing()) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Ok",null)
                    .create();
            alertDialog.show();
        }
    }

    public void showAlertDialog(AlertDialog dialog){
        if(!isFinishing()) {
            alertDialog = dialog;
            alertDialog.show();
        }
    }

    public void hideAlertDialog(){
        if(alertDialog!=null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
