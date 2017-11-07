package cz.utb.fai.androidtranslator.api;

import cz.utb.fai.androidtranslator.pojo.ResponseTranslator;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by student on 07.11.2017.
 */

public interface ApiInterface {
    /**
     *
     * @param searchedString Searched string
     * @param langPair e.g. cs|en
     * @return
     */
    @GET("get")
    Call<ResponseTranslator> getTranslation(
            @Query("q") String searchedString,
            @Query("langpair") String langPair
    );
}
