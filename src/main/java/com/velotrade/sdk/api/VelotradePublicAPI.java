package com.velotrade.sdk.api;

import com.velotrade.sdk.entity.Attachment;
import com.velotrade.sdk.entity.Auction;
import com.velotrade.sdk.entity.DebtorContact;

import java.util.List;

public interface VelotradePublicAPI {
    /**
     *
     * @return list of DebtorContact Object
     * @throws Exception
     */
    List<DebtorContact> getDebtorContacts() throws Exception;

    /**
     *
     * @param id of debtorContact
     * @return DebtorContact Object
     * @throws Exception
     */
    DebtorContact getDebtorContact(String id) throws Exception;

    /**
     *
     * @param idAuction of Auction
     * @return true if auction already approve, if not return false
     * @throws Exception
     */
    boolean approveAuction(String idAuction) throws Exception;

    /**
     *
     * @param idAuction of Auction
     * @return true if Auction is reject, if not return false
     * @throws Exception
     */
    boolean rejectAuction(String idAuction) throws Exception;

    /**
     *
     * @param filePath of file
     * @return Attachment Object
     * @throws Exception
     */
    Attachment uploadAttachment(String filePath) throws Exception;

    /**
     *
     * @param idAuction of AuctionStatus
     * @return AuctionStatus Object
     * @throws Exception
     */
    String getAuctionStatus(String idAuction) throws Exception;

    /**
     *
     * @param auction Object
     * @return id of auction
     * @throws Exception
     */
    String createAuction(Auction auction) throws Exception;

    /**
     *
     * @param idAuciton
     * @return
     * @throws Exception
     */
    String getAuctionPhase(String idAuciton) throws Exception;
}
