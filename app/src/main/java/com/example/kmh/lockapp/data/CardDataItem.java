package com.example.kmh.lockapp.data;

public class CardDataItem{
    public CardDataItem(int cardId, String cardName, int cardLimit, boolean cardType, int balance, String imageUrl) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardLimit = cardLimit;
        this.cardType = cardType;
        this.balance = balance;
        this.imageUrl = imageUrl;
    }

    public int cardId;
    public String cardName;
    public int cardLimit;
    public  boolean cardType;
    public int balance;
    public String imageUrl;
}
