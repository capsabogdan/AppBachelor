package com.licenta.petitepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TopupDialog.TopupDialogListener, CardsDialog.CardsDialogListener {

    private CardView payCardView;
    private Button btnTopUp;
    protected TextView txtAmount;
    public static final String CURRENT_AMOUNT = "CURRENT_AMOUNT";
    Toolbar toolbar;
    Stack<String> currentAmounts = new Stack<>();
    Double oldAmount, scannedAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAmount = findViewById(R.id.txt_amount);
        payCardView = findViewById(R.id.card_view_pay);
        btnTopUp = findViewById(R.id.btn_top_up);
        toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        Intent intentFromScan = getIntent();
        String textAmountScanned = intentFromScan.getStringExtra(ScanActivity.AMOUNT_SCANNED);
        txtAmount.setText("0.00");

        if(textAmountScanned !=  null){
            scannedAmount = Double.valueOf(textAmountScanned);
            Intent intentforScan = new Intent(MainActivity.this, ScanActivity.class);
            String currentAmount = Double.toString(scannedAmount - Double.valueOf(currentAmounts.pop()));
            currentAmounts.add(currentAmount);
            txtAmount.setText(currentAmount);
            intentforScan.putExtra(CURRENT_AMOUNT, currentAmounts.get(currentAmounts.size()-1));
        } else {
            txtAmount.setText("0.00");
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        payCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanActivity.class));
            }
        });

        btnTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopUpDialog();
            }
        });
    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    private void openTopUpDialog() {
        TopupDialog topupDialog = new TopupDialog();
        topupDialog.show(getSupportFragmentManager(), "topup dialog");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
        if (id == R.id.nav_cards) {
            openCardsDialog();
        } else if (id == R.id.nav_payments) {
            handleSelection("Payments");
        } else if (id == R.id.nav_share) {
            handleSelection("Share");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openCardsDialog() {
        CardsDialog cardsDialog = new CardsDialog();
        cardsDialog.show(getSupportFragmentManager(),"cards dialog");
    }

    private void handleSelection(String message) {
        Toast.makeText(getApplicationContext(), message + " will be impelemnted soon...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void applyTexts(String amount) {
        oldAmount = Double.valueOf(txtAmount.getText().toString());
        txtAmount.setText(Double.toString(oldAmount + Double.valueOf(amount)));
    }
}
