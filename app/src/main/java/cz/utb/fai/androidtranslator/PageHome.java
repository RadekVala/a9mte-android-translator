package cz.utb.fai.androidtranslator;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cz.utb.fai.androidtranslator.api.ApiClient;
import cz.utb.fai.androidtranslator.api.ApiInterface;
import cz.utb.fai.androidtranslator.pojo.ResponseData;
import cz.utb.fai.androidtranslator.pojo.ResponseTranslator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by student on 07.11.2017.
 */

public class PageHome implements View.OnClickListener {
    private Activity mActivity;
    private TextView mResult;
    private Button mTranslate;
    private Spinner mInputSpinner;
    private Spinner mOutputSpinner;
    private String[] mAbbrs;
    private EditText mEditText;

    public PageHome(Activity activity){
        mActivity = activity;
        mTranslate = (Button) activity.findViewById(R.id.btnTranslate);
        mTranslate.setOnClickListener(this);
        mInputSpinner = (Spinner)activity.findViewById(R.id.inputSpinner);
// set default value - Czech
        mInputSpinner.setSelection(0);
        mOutputSpinner = (Spinner)activity.findViewById(R.id.outputSpinner);
// set default value - English
        mOutputSpinner.setSelection(1);
        mAbbrs = activity.getResources().getStringArray(R.array.languages_abbr);
        mResult = (TextView)activity.findViewById(R.id.tvResult);
        mEditText = (EditText) activity.findViewById(R.id.etTranslate);
    }

    @Override
    public void onClick(View view) {
        int inputPos = mInputSpinner.getSelectedItemPosition();
        int outPos = mOutputSpinner.getSelectedItemPosition();
        String translatedText = mEditText.getText().toString();
        if(translatedText.isEmpty()){
            // empty text
        } else {
            // we have translated text from user input
            Log.v("transApp",translatedText);

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseTranslator> call = apiService.getTranslation(translatedText,
                    mAbbrs[inputPos]+'|'+mAbbrs[outPos]);
            call.enqueue(new MyCall(translatedText));
        }
    }

    private class MyCall implements Callback<ResponseTranslator>
    {
        private String mTranslatedText;

        private MyCall(String translatedText) {
            mTranslatedText = translatedText;
        }
        @Override
        public void onResponse(Call<ResponseTranslator> call,
                               Response<ResponseTranslator> response) {
            System.out.println("onResponse");
            if (mResult != null){
                ResponseData data = response.body().getResponseData();
                mResult.setText("Překlad: " + data.getTranslatedText());
                mResult.setVisibility(View.VISIBLE);
            }
        }
        @Override
        public void onFailure(Call<ResponseTranslator> call, Throwable t) {
            System.out.println("onFailure");
            t.printStackTrace();
        }
    }
}


