package it.jaschke.alexandria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Olga on 12/5/2015.
 * Referred to https://github.com/dm77/barcodescanner
 */
public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler{

    private final String LOG_TAG = getClass().getSimpleName();
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    //@olga On result, stop the camera and pass the captured isbn to the calling fragment
    @Override
    public void handleResult(Result result) {
        String isbn = result.toString();
        mScannerView.stopCamera();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AddBook.ISBN_KEY, isbn);
        setResult(MainActivity.RESULT_OK, resultIntent);
        finish();
    }
}
