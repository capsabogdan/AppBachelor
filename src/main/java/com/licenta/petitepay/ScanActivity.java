
package com.licenta.petitepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import androidx.annotation.NonNull;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private TextView resultData;
    private Button btnConfirm;
    public static final String AMOUNT_SCANNED = "AMOUNT_SCANNED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        btnConfirm = findViewById(R.id.btn_confirm);
        scannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, scannerView);
        resultData = findViewById(R.id.txt_result);
        resultData.setText("");

            codeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultData.setText("Confirmi plata sumei de " + result.getText() + " lei?");
                        }
                    });
                }
            });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountScanned = resultData.getText().toString();
                Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                intent.putExtra(AMOUNT_SCANNED, amountScanned);
                startActivity(intent);
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

}
