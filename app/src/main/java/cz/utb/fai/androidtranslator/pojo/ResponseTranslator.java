package cz.utb.fai.androidtranslator.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by student on 07.11.2017.
 */

public class ResponseTranslator {

    @SerializedName("responseData")
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
