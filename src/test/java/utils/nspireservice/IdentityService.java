package utils.nspireservice;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.utils.Base64Coder;
import core.http.HttpClient;
import utils.model.UserToken;

import java.util.Map;

public class IdentityService {
    private static final String IDENTITY_SERVICE_BASE_URL = "https://identity-stage.spireon.com/identity/";

    /*Unirest.setTimeouts(0, 0);
    HttpResponse<String> response = Unirest.get("https://identity-stage.spireon.com/identity/token")
            .header("X-Nspire-AppToken", "f07740dc-1252-48f3-9165-c5263bbf373c")
            .header("Authorization", "Basic dHZfcnNfYWRtaW46UGFzc3dvcmRAMQ==")
            .asString();*/

    /*Unirest.setTimeouts(0, 0);
    HttpResponse<String> response = Unirest.post("https://platformapi-stage.spireon.com/v0/rest/internal/test/userAuth/tv_rs_17")
            .header("x-nspire-usertoken", "eyJraWQiOiJuc3BpcmUiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0dl9yc19hZG1pbiIsIm5zOmFzIjoiZmxlZXRMb2NhdGVUcmFuc3BvcnRhdGlvbiIsIm5zOnNnaSI6W10sIm5zOnNjIjoiZmxlZXRMb2NhdGVUcmFuc3BvcnRhdGlvbiIsImlzcyI6Imh0dHBzOlwvXC9zcGlyZW9uLmNvbSIsIm5zOmFwcCI6ImYwNzc0MGRjLTEyNTItNDhmMy05MTY1LWM1MjYzYmJmMzczYyIsIm5zOnQiOiJ0dl9yc19hZG1pbitmMDc3NDBkYy0xMjUyLTQ4ZjMtOTE2NS1jNTI2M2JiZjM3M2MiLCJuczpzIjoiQUNDT1VOVF9VU0VSX1NDT1BFIiwibnM6diI6InYxIiwibnM6dSI6ImY2YWJhYjhiLWRmOTMtNGFjOS05YTkwLTAyYmZjMTk0NzY5MyIsIm5zOmFiIjoiZmxlZXRMb2NhdGVUcmFuc3BvcnRhdGlvbiIsIm5zOnJvbGUiOlsiUk9MRV9BVElfQURNSU4iLCJST0xFX0FUSV9EUklWRVIiLCJST0xFX0lOVEVHUkFUSU9OX1RFU1RfQUNUT1IiLCJST0xFX0lOVEVMTElTQ0FOX0FETUlOIiwiUk9MRV9MRUFTRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJleHAiOjE2OTE3MzI4NjYsIm5zOnBvbCI6IjU3Y2VlNzgwMWE4YzY1NTAxNDAwMDAwNyIsIm5zOmIiOiJmbGVldExvY2F0ZVRyYW5zcG9ydGF0aW9uIiwibnM6YSI6IjE2ODcyNTQzMjY4ODc0UjBFQ0hQIn0.mADT-OCypyqaIcwKtDypgLXtfdEZcj-Xd4NdMWNn30hYcb9TOXu9h6pmy04HsnHjAN76zdm--9QuFDLEc1ZdeXNvrtBRNL0AeRH8k5L_qpGQMYSY70ijLViWK02CQkV6e-pgiQTDiLTr_EQk-wM8qc5DO6bhf6nWMv1h8DxjbWYvZTczZcQURytX0WHVzQYVDGKcvJPTbFlD8XXj7EF1Lmej0gIL1J9EsSIPhbmjIZpITJTculvj30vxWaARJans8CTrMXg3wtPOU_r0BmL-KsgqIOO_MboubxomUTiIuQ4-o_3E7U397DCXdMy54tTefZ-kK6g37cJUk5XJFxkyBw")
            .header("Content-Type", "application/json")
            .body("{\n    \"authProviderCode\":\"nspire\",\n    \"targetAuthProviderCode\": \"ati-cognito\",\n    \"migrateByDate\": \"2024-07-21T12:00:00.947Z\"\n}")
            .asString();*/
    public static UserToken getUserToken(Map<String, String> headers, String username, String password) {
        /**
         * HashMap<String, String> headers = new HashMap<String, String>();
         * headers.put("X-Nspire-AppToken", "f07740dc-1252-48f3-9165-c5263bbf373c")
         */
        try {
            Unirest.setTimeouts(60000L, 60000L);
            String finalUrl = IDENTITY_SERVICE_BASE_URL + "token";
            headers.put("Authorization", "Basic " + Base64Coder.encodeString(username + ":" + password));
            HttpClient client = new HttpClient();
            return client.get(finalUrl, null, null, headers, UserToken.class).getParsedObject();
        }catch (Exception e){
            return null;
        }
    }
}
