package com.velotrade.sdk.jsonobject;

import com.velotrade.sdk.entity.DebtorContact;

import java.util.List;

public class PaginationList {
    List<DebtorContact> data;

    public List<DebtorContact> getDebtorContacts() {
        return data;
    }

    public void setDebtorContacts(List<DebtorContact> debtorContacts) {
        this.data = debtorContacts;
    }
}
