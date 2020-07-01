package com.licenta.petitepay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CreditCardAdapter extends ArrayAdapter<CardItem> {
    public CreditCardAdapter(@NonNull Context context, ArrayList<CardItem> creditCardList) {
        super(context, 0, creditCardList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.cards_spinner_row, parent, false
            );
        }

        ImageView imageViewCreditCard = convertView.findViewById(R.id.image_view_card);
        TextView textViewCreditCardName = convertView.findViewById(R.id.text_view_creditcardname);
        CardItem currentItem = getItem(position);

        if(currentItem!= null) {
            imageViewCreditCard.setImageResource(currentItem.getCreditCardImage());
            textViewCreditCardName.setText(currentItem.getCreditCardName());
        }

        return convertView;
    }
}
