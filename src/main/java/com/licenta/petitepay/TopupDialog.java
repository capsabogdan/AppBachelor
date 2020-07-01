package com.licenta.petitepay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class TopupDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    private EditText editTextAmountIntroduced;
    private TopupDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_topup_dialog,null);

        Spinner spinnerCreditCards = view.findViewById(R.id.spinner_topup_cards);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.credit_cards_numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCreditCards.setAdapter(adapter);
        spinnerCreditCards.setOnItemSelectedListener(this);

        builder.setView(view)
                .setTitle("Alimenteaza cont")
                .setNegativeButton("Anuleaza", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Alimenteaza cont", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String amount = editTextAmountIntroduced.getText().toString();
                        listener.applyTexts(String.format("%.2f", Double.parseDouble(amount)));
                    }
                });
        editTextAmountIntroduced = view.findViewById(R.id.edit_topup);
                return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (TopupDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement TopupDialogListener");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface TopupDialogListener{
        void applyTexts(String amount);
    }
}
