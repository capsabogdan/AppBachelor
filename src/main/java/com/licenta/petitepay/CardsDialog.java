package com.licenta.petitepay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CardsDialog extends AppCompatDialogFragment {

    private EditText edCardName;
    private EditText edCardNumber;
    private EditText edCardCvv;
    private EditText edCardExpirationDate;
    private CardsDialogListener listener;
    public static final String CARD_NUMBER = "CARD_NUMBER";

    List<String> cardData;

    private ArrayList<CardItem> creditCardList;
    private CreditCardAdapter adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_cards_dialog, null);

        builder.setView(view);
        Spinner spinnerCreditCards = view.findViewById(R.id.spinner_cards);
        initList();
        adapter = new CreditCardAdapter(getActivity().getBaseContext(), creditCardList);

        spinnerCreditCards.setAdapter(adapter);
        spinnerCreditCards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CardItem clickedItem = (CardItem) parent.getItemAtPosition(position);
                String clickedCardName = clickedItem.getCreditCardName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setTitle("Introduceți date card")
                .setNegativeButton("Anulați", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Introduceți card", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cardName = edCardName.getText().toString();
                        String cardNumber = edCardNumber.getText().toString();
                        String cardCvv = edCardCvv.getText().toString();
                        String cardExpiryDate = edCardExpirationDate.getText().toString();
                        cardData = new ArrayList<>();
                        cardData.add(cardName);
                        cardData.add(cardNumber);
                        cardData.add(cardCvv);
                        cardData.add(cardExpiryDate);
                        Intent intent = new Intent(getActivity().getBaseContext(), TopupDialog.class);
                        intent.putExtra(CARD_NUMBER, cardNumber);
                        Toast.makeText(getActivity().getBaseContext(),"Cardul dvs. a fost adăugat cu succes!",Toast.LENGTH_SHORT).show();
                    }
                });

        edCardName = view.findViewById(R.id.ed_card_name);
        edCardNumber = view.findViewById(R.id.ed_card_number);
        edCardCvv = view.findViewById(R.id.ed_cvv);
        edCardExpirationDate = view.findViewById(R.id.ed_card_expiry);

        return builder.create();
    }

    private void initList() {
        creditCardList = new ArrayList<>();
        creditCardList.add(new CardItem("Master Card", R.drawable.master_card));
        creditCardList.add(new CardItem("Visa", R.drawable.visa_card));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CardsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement CardsDialogListener");
        }
    }

    public interface CardsDialogListener {
        void applyTexts(String amount);
    }

}

