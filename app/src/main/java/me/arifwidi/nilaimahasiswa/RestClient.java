package me.arifwidi.nilaimahasiswa;
import com.loopj.android.http.*;

/**
 * Created by arifwidipratomo on 2/5/17.
 */

public class RestClient {
    private static final String BASE_URL = "http://58965e7d39c968120016aa9b.mockapi.io/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
