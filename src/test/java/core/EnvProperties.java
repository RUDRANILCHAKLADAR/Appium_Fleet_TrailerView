package core;

import org.json.simple.JSONObject;
import org.testng.ITestContext;

public class EnvProperties {
    private ITestContext testContext;

    public EnvProperties(ITestContext ctx) {
        testContext = ctx;
    }

    public String getPlatformBaseUrl() {
        return (String) ((JSONObject)testContext.getAttribute(Constants.ENVIRONMENT_CONFIG)).get("platformBaseUrl");
    }

    public String getIdentityBaseUrl() {
        return (String) ((JSONObject)testContext.getAttribute(Constants.ENVIRONMENT_CONFIG)).get("identityServiceBaseUrl");
    }

}
