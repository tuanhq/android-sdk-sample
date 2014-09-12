package com.bitpay.eordano.musicstore.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eordano on 9/9/14.
 */
public class Invoice implements Parcelable {

    public static ArrayList<Item> ITEMS = new ArrayList<Item>();
    static {
        ITEMS.add(new Item("Classical Guitar", 99.95));
        ITEMS.add(new Item("Electric Guitar", 400.00));
        ITEMS.add(new Item("Violin", 700.50));
        ITEMS.add(new Item("Guitar long cord", 12.99));
        ITEMS.add(new Item("Bass", 12.99));
        ITEMS.add(new Item("Drum set", 120.00));
        ITEMS.add(new Item("Huge drum set", 1200.00));
        ITEMS.add(new Item("Amplifier", 1200.00));
        ITEMS.add(new Item("Guitar pick", 1.99));
    }

    private final Map<Item, Integer> items = new HashMap<Item, Integer>();

    public void addItem(Item item) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public Integer get(Item item) {
        if (items.containsKey(item)) {
            return items.get(item);
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        for (Item item : ITEMS) {
            parcel.writeInt(get(item));
        }
    }
    public static final Parcelable.Creator<Invoice> CREATOR = new Parcelable.Creator<Invoice>() {
        public Invoice createFromParcel(Parcel in) {
            return Invoice.fromParcel(in);
        }

        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

    public static Invoice fromParcel(Parcel parcel) {
        Invoice newInvoice = new Invoice();
        for (Item item : ITEMS) {
            int amount = parcel.readInt();
            if (amount != 0) {
                newInvoice.items.put(item, amount);
            }
        }
        return newInvoice;
    }
}
