package com.cocobeen.Utils.struct;

import org.json.JSONObject;

public class ConfigData {
    private String MerchantID = "";
    private String HashKey = "";
    private String HashIV = "";

    public ConfigData(JSONObject data){
        this.MerchantID = data.getString("ShopCode");
        this.HashKey = data.getString("HashKey");
        this.HashIV = data.getString("HashIV");
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public String getHashKey() {
        return HashKey;
    }

    public String getHashIV() {
        return HashIV;
    }
}
