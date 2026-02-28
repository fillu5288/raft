package com.example.Fillu.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class Utility {
    private Utility utility;

    public void clickWithoutCoord() throws AWTException {
        System.setProperty("java.awt.headless", "false");
        Robot robot = new Robot();
        robot.setAutoDelay(10);

        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public boolean getPixelColor(int x, int y, int r, int g, int b) throws AWTException, InterruptedException {
        System.setProperty("java.awt.headless", "false");
        Robot robot = new Robot();
        robot.setAutoDelay(10);

        Color c = robot.getPixelColor(x, y);

        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if (r == red && g == green && b == blue) {
            System.out.println("true");
            return true;
        }

        System.out.println("Pixel color: " + c);
        System.out.println("false");
        return false;
    }
}

