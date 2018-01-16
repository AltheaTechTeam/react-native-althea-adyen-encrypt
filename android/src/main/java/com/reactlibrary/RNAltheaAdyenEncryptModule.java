
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

import com.adyencse.encrypter.exception.EncrypterException;
import com.adyencse.pojo.Card;

public class RNAltheaAdyenEncryptModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

	private final ReactApplicationContext reactContext;
	private Card card;

	public RNAltheaAdyenEncryptModule(ReactApplicationContext reactContext) {
		super(reactContext);
		this.reactContext = reactContext;
        initAdyenCse();
	}

	private void initAdyenCse() {
		card = new Card();
	}

  	@ReactMethod
    public void encrypt(ReadableMap params, Callback callback) {
        ReadableMapKeySetIterator iterator  = params.keySetIterator();

        while (iterator.hasNextKey()) {

            String key = iterator.nextKey();

            switch (key) {

                case "card_holder_name":
                    card.setCardHolderName(params.getString(key));
                    break;
                case "cvc":
                    card.setCvc(params.getString(key));
                    break;
                case "expiry_month":
                    card.setExpiryMonth(params.getString(key));
                    break;
                case "expiry_year":
                    card.setExpiryYear(params.getString(key));
                    break;
                case "number":
                    card.setNumber(params.getString(key));
                    break;
                default:
                    // do nothing
            }
        }

        card.setGenerationTime(new Date());

        JSONObject jsonObj = new JSONObject();

        try {

            jsonObj.put("encrypted_data", card.serialize(BuildConfig.ADYEN_PUBLIC_KEY));
        } catch (EncrypterException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        callback.invoke(RNUtils.jsonToWritableMap(jsonObj));
    }

	@Override
	public String getName() {
		return "RNAltheaAdyenEncrypt";
	}

    @Override
    public void onHostResume() {
        initAdyenCse();
    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {

    }
}