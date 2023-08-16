package utils;

import core.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.testng.ITestResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static HashMap<String, String> parseStringXML(InputStream file) throws Exception{
        HashMap<String, String> stringMap = new HashMap<String, String>();
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(file);

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();

        //Get all elements
        NodeList nList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                // Store each element key value in map
                stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringMap;
    }

    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void startVideo(boolean shouldCaptureVideo, AppiumDriver driver) {
        if (shouldCaptureVideo) {
            System.out.println("Video recording started...");
            ((CanRecordScreen)driver).startRecordingScreen();
        }
    }

    public static void stopVideo(Method method, ITestResult result, boolean shouldCaptureVideo, boolean shouldCaptureVideoOnlyFailure, AppiumDriver driver) throws IOException {

        if (shouldCaptureVideo) {
            if (shouldCaptureVideoOnlyFailure && result.getStatus() == ITestResult.SUCCESS) {
                // Stop video recording
                ((CanRecordScreen)driver).stopRecordingScreen();
                return;
            }
            System.out.println("Video recording stopped... " + method.getName() + "result : " + result.getStatus());
            String base64String = ((CanRecordScreen)driver).stopRecordingScreen();
            byte[] data = Base64.getDecoder().decode(base64String);

            Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
            String destinationPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
                    + File.separator + Utils.dateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();


            if (!Files.isDirectory(Path.of(destinationPath))) {
                Files.createDirectories(Path.of(destinationPath));
            }
            destinationPath = destinationPath + "/";
            if (method.getName() == null || method.getName().isEmpty()) {
                destinationPath = destinationPath + BaseTest.DefaultTestVideoFileName();
            }else {
                destinationPath= destinationPath + method.getName();
            }
            destinationPath = destinationPath + ".mp4";
            Path path = Paths.get(destinationPath);
            try {
                Files.write(path, data);
            } catch (Exception e) {
                System.out.println("Exception : " + e);
            }
        }

    }
}
