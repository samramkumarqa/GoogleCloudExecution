package google_cloud;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ExecutionGoogleCloud {

    @Test
    public void googleCloudRunner() throws InterruptedException {
        int threadCount = 5;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    String remoteUrl = "http://" + "ramkumarsamqa" + ":" + "Zalenium2020" + "@" + "34.71.217.184" + "/wd/hub";

                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    WebDriverManager.chromedriver().setup();
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

                    WebDriver driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

                    // Open Google
                    driver.get("https://www.google.com");
                    // Print Page Title
                    System.out.println("Page Title: " + driver.getTitle());

                    // Close browser
                    driver.quit();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        // Ensure all threads complete before the test method exits
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
