package com.telezone.model;

import java.util.ArrayList;
import java.util.List;

public class CardReader {

    private Integer cardReaderId;

    private List<Integer> cardIdArr = new ArrayList<>();

    public Integer getCardReaderId() {
        return cardReaderId;
    }

    public void setCardReaderId(Integer cardReaderId) {
        this.cardReaderId = cardReaderId;
    }

    public List<Integer> getCardIdArr() {
        return cardIdArr;
    }

    public void setCardIdArr(List<Integer> cardIdArr) {
        this.cardIdArr = cardIdArr;
    }
}
