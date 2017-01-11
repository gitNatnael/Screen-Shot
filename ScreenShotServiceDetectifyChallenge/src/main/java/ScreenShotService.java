import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by natna on 1/9/2017.
 */
public class ScreenShotService {

    //take screenshot image
    public String takeScreenShoot(String url) {

        String fileName = url.replaceAll("[^\\p{Alnum}\\s]", "_");
        // String fileName = JOptionPane.showInputDialog("Please give file name to save in to the db:");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\natna\\IdeaProjects\\ScreenShotServiceDetectifyChallenge\\src\\main\\resources\\Driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();   //open the page using firfox
        driver.get(url);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            //save file
            FileUtils.copyFile(scrFile, new File("C:\\Users\\natna\\IdeaProjects\\ScreenShotServiceDetectifyChallenge\\src\\main\\resources\\File\\" + fileName + ".png"));
        } catch (IOException e) {

            e.printStackTrace();
        }

        return fileName;
    }

}
