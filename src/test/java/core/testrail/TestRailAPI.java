package core.testrail;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRailAPI {

//    Logger LOGGER = LoggerFactory.getLogger(TestRailAPI.class);
    public static final String TEST_RAIL_URL = "https://spireoncloud.testrail.io/"; //http://testrail.spireon.com/

    private final String TEST_RAIL_USER_NAME = "MobileQA@Spireon.com";
    private final String TEST_RAIL_USER_PASSWORD = "SpireonQA!";
    public static final int TEST_RAIL_PROJECT_ID = 58; // TestRail ATI driver project ID

    private APIClient client;

    /**
     * Post Test Rail Results.
     *
     * @param testRailId testcase ID
     * @param suiteId test suite ID
     * @param result test result
     */
    public void postTestRailResult(String  testRailId, Long suiteId, ITestResult result) {
        // Post To TestRail
        System.out.println("Test case Id [" + testRailId + "] with Status::" + result.getStatus());
        post(testRailId, suiteId, getTestRailMetaData(result));
    }

    /**
     * Create a Map object which contains the MetaData for the Test which needs to be updated in Test Rail.
     *
     * @param result testResult
     * @return Map
     */
    private Map<String, Object> getTestRailMetaData(ITestResult result) {
        //Set the status_id for the test Rail for Pass, Fail and Skip status.
        Map<String, Object> dataTestRail = new HashMap<>();
        if (result.getStatus() == ITestResult.SUCCESS) {
            dataTestRail.put("status_id", TestRailStatusID.PASS);
            dataTestRail.put("comment", "SUCCESS");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            dataTestRail.put("status_id", TestRailStatusID.FAIL);
            dataTestRail.put("comment", "FAILURE" + "\n" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            dataTestRail.put("status_id", TestRailStatusID.RETEST);
            dataTestRail.put("comment", "SKIPPED" + "\n" + result.getThrowable());
        }
        return dataTestRail;
    }

    /**
     * Call TestRail Client and Post Request.
     * Note: Update the Test Run ID properly or you will see 400 in Response.
     *
     * @param testRailId test case ID
     * @param suiteID test run suite ID
     * @param data data to update
     */
    private void post(String testRailId, Long suiteID, Map<String, Object> data) {
        try {
            getTestRailAPIClient().sendPost("add_result_for_case/" + suiteID + "/" + testRailId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the Test Rail Api Client.
     *
     * @return APIClient
     */
    private APIClient getTestRailAPIClient() {
        if(client == null) {
            client = new APIClient(TEST_RAIL_URL);
            client.setUser(TEST_RAIL_USER_NAME);
            client.setPassword(TEST_RAIL_USER_PASSWORD);
        }
        return client;
    }

    public void beforeTest(ITestContext ctx, Method testMethod) {
        if(testMethod.isAnnotationPresent(TestRailId.class)) {
            TestRailId ids = testMethod.getAnnotation(TestRailId.class);
            ctx.setAttribute("testId", ids.tags());
        }
    }

    public void afterTest(ITestContext context, ITestResult testResult, Method testMethod) {
        String[] testId = (String[]) context.getAttribute("testId");
        Long suiteId = (Long) context.getAttribute("suiteId");
        for (String id: testId) {
            postTestRailResult(id, suiteId, testResult);
        }
    }

    public void createTestRunSuite(ITestContext ctx) throws IOException, APIException{
        Map<String, Object> data = new HashMap<>();
        data.put("include_all", true);
        data.put("name", "Appium Test Run " + System.currentTimeMillis());
        JSONObject jsonObject = (JSONObject) getTestRailAPIClient().sendPost("add_run/" + TEST_RAIL_PROJECT_ID, data);
        Long suiteId = (Long) jsonObject.get("id");
        ctx.setAttribute("suiteId", suiteId);
    }

    public static class TestRailStatusID {
        public static int PASS = 1;
        public static int RETEST = 4;
        public static int FAIL = 5;
    }
}
