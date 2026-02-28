package com.example.Fillu;

import com.example.Fillu.Service.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.awt.image.BufferedImage;

@SpringBootApplication
public class FilluApplication implements CommandLineRunner {
	private Utility utility;

	@Autowired
	public FilluApplication(Utility utility) {
		this.utility = utility;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilluApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.setProperty("java.awt.headless", "false");
		Robot robot = new Robot();

		Color targetGreenPixel = new Color(0, 214, 103);
		Color targetGreenLine = new Color(0, 249, 135);
		Color targetWhiteLine = new Color(220, 234, 235);

		int x1;
		int y1;

		int x2 = 0;
		int y2 = 0;

		int x3 = 0;
		int y3 = 0;

		boolean yes = false;

		Thread.sleep(5000);

		while (true) {
			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage image = robot.createScreenCapture(rectangle);
			//Thread.sleep(20);

			for (x1 = 0; x1 < image.getWidth(); x1++) {
				for (y1 = 0; y1 < image.getHeight(); y1++) {
					if (image.getRGB(x1, y1) == targetGreenPixel.getRGB()) {
						System.out.println("Detect green pixel");

						x3 = x1;
						y3 = y1;
						yes = true;
						break;
					}
				}
			}

			image = robot.createScreenCapture(rectangle);

			for (x1 = 0; x1 < image.getWidth(); x1++) {
				for (y1 = 0; y1 < image.getHeight(); y1++) {
					if (image.getRGB(x1, y1) == targetGreenLine.getRGB()) {
						x2 = x1;
						y2 = y1;
						System.out.println("Detect green line");
					}
				}
			}

			image = robot.createScreenCapture(rectangle);

			for (x1 = 0; x1 < image.getWidth(); x1++) {
				for (y1 = 0; y1 < image.getHeight(); y1++) {
					if (image.getRGB(x1, y1) == targetWhiteLine.getRGB()) {
						yes = true;
						System.out.println("Detect white line");
					}
				}
			}

			if (yes == true && utility.getPixelColor(x2, y2, 0, 249, 135) == false) {
				utility.clickWithoutCoord();
				x2 = 0;
				y2 = 0;
				yes = false;
				Thread.sleep(400);

			} else if (utility.getPixelColor(x3, y3, 0, 214, 103) == false) {
				utility.clickWithoutCoord();
				Thread.sleep(30000);
			}
		}

	}
}