package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RobotActions
{

    public static void robotPressKey(int keyEvent)
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(keyEvent);
        robot.keyRelease(keyEvent);
    }

    public static void robotLongPressKey(int keyEvent)
    {
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        bot.setAutoDelay(50);

        int duration = 3000;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < duration) {
            bot.keyPress(keyEvent);
        }
        bot.keyRelease(keyEvent);
    }

    public static void robotPressTwoKeys(int keyEvent, int keyEvent2)
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(keyEvent);
        robot.keyPress(keyEvent2);
        robot.keyRelease(keyEvent2);
        robot.keyRelease(keyEvent);
    }

    public static void robotPressMouseWithoutRelease()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public static void robotReleaseMousePress()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }


    public static void robotMouseDoubleClick()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void robotMouseClick()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void robotMouseWheel(int wheelAmt)
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseWheel(wheelAmt);
    }

    public static void robotMouseMove(int x, int y)
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseMove(x, y);
    }

    public static void mouseRightClick()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void grabScreenshot(String name)
    {
        try {
            Robot robot = new Robot();
            String format = "png";

            String fileName = System.getProperty("user.dir") + "/build/reports/tests/" + name + System.currentTimeMillis() + ".png";
            Rectangle captureRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            Logger.log("Screenshot saved at: " + fileName);
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    public static String grabScreenshotPartialScreen(String name, String folder)
    {
        try {
            Robot robot = new Robot();
            String format = "png";

            String fileName = folder + name + System.currentTimeMillis() + ".png";
            Toolkit t = Toolkit.getDefaultToolkit();
            Dimension d = t.getScreenSize();
            Rectangle captureRect = new Rectangle(0, 0, d.width / 4, d.height / 4);
            BufferedImage screenPartialImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenPartialImage, format, new File(fileName));
            Logger.log("Screenshot saved at: " + fileName);
            return fileName;
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
            Logger.log("Failed to take partial screenshot");
            return null;
        }
    }
}
