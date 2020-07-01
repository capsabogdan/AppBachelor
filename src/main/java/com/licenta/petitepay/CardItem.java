package com.licenta.petitepay;

public class CardItem {
    private String creditCardName;
    private int creditCardImage;

    public CardItem(String creditCardName, int creditCardImage) {
        this.creditCardName = creditCardName;
        this.creditCardImage = creditCardImage;
    }

    public String getCreditCardName(){
        return creditCardName;
    }

    public int getCreditCardImage(){
        return creditCardImage;
    }


}
