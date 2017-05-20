package com.example.soomin.myassignment;

import android.graphics.drawable.Drawable;

/**
 * Created by SOOMIN on 2017-05-14.
 */

public class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String priceStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }
    public void setTitle(String title){
        titleStr = title;
    }
    public void setPrice(String price){
        priceStr = price;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public String getPrice(){
        return this.priceStr;
    }
}
