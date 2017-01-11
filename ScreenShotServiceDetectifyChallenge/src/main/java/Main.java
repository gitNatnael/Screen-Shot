import org.apache.commons.io.FileUtils;
import org.omg.SendingContext.RunTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by natna on 1/8/2017.
 */
public class Main {
    //file name for url list
    private final static String URL_LIST = "C:\\Users\\natna\\IdeaProjects\\ScreenShotServiceDetectifyChallenge\\src\\main\\resources\\URL_LIST\\url list.txt";
    private static List<String> listOFURL = new ArrayList();


    public static void main(String[] args) throws Exception {


        File file = new File("C:\\Users\\natna\\IdeaProjects\\ScreenShotServiceDetectifyChallenge\\src\\main\\resources\\URL_LIST\\ReadMe.txt");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);

        String urlAddress = JOptionPane.showInputDialog("Please enter url to start screen shot:");

        //initiate database connection and save the entity in database
        DatabaseManipulation dbM = new DatabaseManipulation();
        //create screenshot class object
        ScreenShotService screenShotService = new ScreenShotService();
        //take screenshoot method return file name to save the file in database
        String fileName = screenShotService.takeScreenShoot(urlAddress);
        Thread.sleep(1000);  //give  sometime till the screenshot take
        //get screenshot picture entity
        ScreenShotPicture screenShotPicture = new ScreenShotPicture(urlAddress, fileName, new Date());
        //save the screenshotpicture object in ObjectDB
        dbM.saveFile(screenShotPicture);


        ///uncomment comment 2 run the program to read url from file
       /*  comment 2---to read url from file

        List<String> url = Main.readURLFromFile(URL_LIST);

        for (int u = 0; u < url.size(); u++) {
            //call Screen shot method
            String fileName = screenShotService.takeScreenShoot(url.get(u));
            Thread.sleep(1000);  //give  sometime till the screenshot take
            //get screenshot picture entity
            ScreenShotPicture screenShotPicture = new ScreenShotPicture(url.get(u), fileName, new Date());
            //After getting screen shot save the picture information on objectDB
            dbM.saveFile(screenShotPicture);
        }  */

        //uncomment the following   codes to search file by name file name and display the image

        //---comment 3-- String fileNameToSearch=JOptionPane.showInputDialog("Please enter file name that u want to search and view:");
        //-- comment 3 --ScreenShotPicture scp = dbM.getScreenShotImageBYFileName(fileNameToSearch); //search file by name
        //--comment 3-- String getFile = "C:\\Users\\natna\\IdeaProjects\\ScreenShotServiceDetectifyChallenge\\src\\main\\resources\\File\\" + scp.getFileName() + ".png"; //get file name
        //--comment --3  displayPictures(getFile);  //Display the image in JFrame

    }  //end of main method


    //displaying image in JFrame after search from file
    private static void displayPictures(final String filename) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame editorFrame = new JFrame("Screenshot Image");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(filename));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });
    }


    //reading  all url from file and return list of url
    private static List readURLFromFile(String pathFile) throws IOException {

        Scanner scanner = null;
        String line = null;

        try {
            scanner = new Scanner(new File(URL_LIST)).useDelimiter(",");  //)(",");
            //reading url from the file ,it is separed by comma
            while (scanner.hasNext()) {
                listOFURL.add(scanner.next());
            }

        } finally {

            scanner.close();
        }

        return listOFURL;
    }
} //end of main class