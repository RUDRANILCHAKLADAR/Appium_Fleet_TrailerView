package utils.nspireservice;

import com.mashape.unirest.http.Unirest;
import core.http.HttpClient;
import core.http.Response;

import java.util.Map;

public class CognitoService {

    private static final String PLATFORM_BASE_URL = "https://platformapi-stage.spireon.com/v0/rest/";

    public boolean changeTargetAuth(Map<String, String> headers, Object body, String userNameToChangeTargetAuthProvider) {
        /**
         * HashMap<String, String> headers = new HashMap<String, String>();
         *             headers.put("x-nspire-usertoken", userTokenResponse.getToken()); // received from Identity service
         *             headers.put("Content-Type", "application/json");
         */

        /**
         * JSONObject body=new JSONObject();
         *             body.put("authProviderCode", "nspire");
         *             body.put("targetAuthProviderCode", "ati-cognito");
         *             body.put("migrateByDate", "2024-07-21T12:00:00.947Z");
         */
        try{
            Unirest.setTimeouts(60000L, 60000L);
            HttpClient client = new HttpClient();
            String finalUrl = PLATFORM_BASE_URL + "internal/test/userAuth/" + userNameToChangeTargetAuthProvider;
            Response<Object> response = client.post(finalUrl, null,null,headers, body);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
