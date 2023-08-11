package utils.nspireservice;

import com.mashape.unirest.http.Unirest;
import core.http.HttpClient;
import core.http.Response;

import java.util.Map;

public class AtiAvsService {

    private static String atiAvsBaseUrl;

    public static void setAtiAvsBaseUrl(String baseUrl) {
        atiAvsBaseUrl = baseUrl;
    }
    public static <T> T getAssetList(Map<String, String> headers, Class<T> clazz) {
        /**
         * HashMap<String, String> headers = new HashMap<String, String>();
         *             headers.put("x-nspire-usertoken", userTokenResponse.getToken()); // received from Identity service
         *             headers.put("Content-Type", "application/json");
         */
        try{
            Unirest.setTimeouts(60000L, 60000L);
            HttpClient client = new HttpClient();
            String finalUrl =  atiAvsBaseUrl + "/assets?limit=50&offset=0&sortBy=name&sortOrder=ASC";
            Response<T> response = client.get(finalUrl, null, null, headers, clazz);
            return response.getParsedObject();
        }catch (Exception e){
            return null;
        }

    }


}
