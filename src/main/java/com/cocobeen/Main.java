package com.cocobeen;

import com.cocobeen.Utils.DataEncryptionUtils;
import com.cocobeen.Utils.FileManager;
import com.cocobeen.Utils.struct.ConfigData;
import com.cocobeen.Utils.struct.PostData;
import okhttp3.*;

import java.io.IOException;

public class Main {
    public static ConfigData config = null;

    public static void main(String[] args) {
        config = FileManager.initConfigFile();

        PostData data = DataEncryptionUtils.EncryptData(config.getMerchantID(), 30, config.getHashKey(), config.getHashIV());

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String requestBody = "MerchantID=" + data.getMerchantID() + "&" +
                "Version=" + data.getVersion() + "&" +
                "TradeInfo=" + data.getTradeInfo() + "&" +
                "TradeSha=" + data.getTradeSha();

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url("https://ccore.newebpay.com/MPG/mpg_gateway")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("Response: " + responseBody);
            } else {
                System.out.println("Request failed: " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
