package com.cocobeen.Utils.struct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TradeInfo {
    private String MerchantID = "";
    private String RespondType = "JSON";
    private long TimeStamp = System.currentTimeMillis() / 1000L;
    private String Version = "2.0";
    private String MerchantOrderNo = String.valueOf(System.currentTimeMillis() / 1000L);
    private int Amt = 30;
    private String ItemDesc = "";
    private int TradeLimit = 0;
    private String ExpireDate = "";
    private String Email = "seer67875@gmail.com";
    private String OrderComment = "TEST";
    private boolean VACC = true;
    private boolean CVS = true;

    public TradeInfo(String MerchantID, int amt){
        this.MerchantID = MerchantID;
        this.Amt = amt;
        LocalDate date = LocalDate.now();
        this.ExpireDate = date.plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public String getRespondType() {
        return RespondType;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public String getVersion() {
        return Version;
    }

    public String getMerchantOrderNo() {
        return MerchantOrderNo;
    }

    public int getAmt() {
        return Amt;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public int getTradeLimit() {
        return TradeLimit;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public String getEmail() {
        return Email;
    }

    public String getOrderComment() {
        return OrderComment;
    }

    public boolean UseVACC() {
        return VACC;
    }

    public boolean UseCVS() {
        return CVS;
    }

    public String getTradeInfoData(){
        String result = "MerchantID=" + this.MerchantID + "&" +
                "TimeStamp=" + this.TimeStamp + "&" +
                "Version=" + this.Version + "&RespondType=Stri" +
                "ng&MerchantOrderNo=Vanespl_ec_1663040304&Amt=30&NotifyURL=https%3A%2F%2" +
                "Fwebhook.site%2Fd4db5ad1-2278-466a-9d66-" +
                "78585c0dbadb&ReturnURL=&ItemDesc=test";
        /*
        String result = "MerchantID=" + this.MerchantID + "&" +
                "RespondType=" + this.RespondType + "&" +
                "TimeStamp=" + this.TimeStamp + "&" +
                "Version=" + this.Version + "&" +
                "MerchantOrderNo=" + this.MerchantOrderNo + "&" +
                "Amt=" + this.Amt + "&" +
                "ItemDesc=" + this.ItemDesc + "&" +
                "TradeLimit=" + this.TradeLimit + "&" +
                "ExpireDate=" + this.ExpireDate + "&" +
                "Email=" + this.Email + "&" +
                "OrderComment=" + this.OrderComment + "&" +
                "VACC=" + paymentState(this.VACC) + "&" +
                "CVS=" + paymentState(this.CVS);

         */

        return result;
    }

    private int paymentState(boolean state){
        return state ? 1 : 0;
    }
}
