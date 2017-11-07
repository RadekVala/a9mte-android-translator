package cz.utb.fai.androidtranslator.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by student on 07.11.2017.
 */

public class ResponseData {

    @SerializedName("translatedText")
    private String translatedText;
    @SerializedName("match")
    private float matchPercentage;

    public float getMatchPercentage() {
        return matchPercentage;
    }
    public void setMatchPercentage(float matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
    public String getTranslatedText() {
        return translatedText;
    }
    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

}
