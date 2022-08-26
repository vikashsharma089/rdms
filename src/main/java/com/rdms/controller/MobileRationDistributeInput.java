package com.rdms.controller;

import java.util.List;

public class MobileRationDistributeInput {

    private String stockid;
    private String cardid;
    private String total_amount;
    private List<MobilestockItems> items;

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public List<MobilestockItems> getItems() {
        return items;
    }

    public void setItems(List<MobilestockItems> items) {
        this.items = items;
    }
}
