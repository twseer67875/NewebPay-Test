package com.cocobeen.Utils.struct;

public class PostData {
    private String MerchantID = "";
    private String TradeInfo = "";
    private String TradeSha = "";
    private String Version = "2.0";

    public PostData(String MerchantID, String TradeInfo, String TradeSha){
        this.MerchantID = MerchantID;
        this.TradeInfo = TradeInfo;
        this.TradeSha = TradeSha;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public String getTradeInfo() {
        return TradeInfo;
    }

    public String getTradeSha() {
        return TradeSha;
    }

    public String getVersion() {
        return Version;
    }
}
