package helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class ScreenshotUtils {

	private WebDriver driver;

	public ScreenshotUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void captureWebPage(String desPath) {
		saveScreenshot(capturePage(), desPath);
	}

	public void captureWebElement(WebElement element, String desPath) {
		Point start = element.getLocation();
		Dimension size = element.getSize();

		File image = cropImage(start, size, fileToImage(capturePage()));
		saveScreenshot(image, desPath);
	}

	public byte[] captureWebPageAsByteArray() throws IOException {
		return FileUtils.readFileToByteArray(capturePage());
	}

	public byte[] captureWebElementAsByteArray(WebElement element) throws IOException {
		File image = cropImage(element.getLocation(), element.getSize(), fileToImage(capturePage()));
		return FileUtils.readFileToByteArray(image);
	}

	private File cropImage(Point start, Dimension size, BufferedImage image) {
		int width = size.getWidth();
		int height = size.getHeight();
		File tempImg = null;

		try {
			tempImg = File.createTempFile("screenshot", ".png");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		if (width < 1) {
			width = 1;
		}
		if (height < 1) {
			height = 1;
		}

		BufferedImage croppedImage = image.getSubimage(start.getX(), start.getY(), width, height);

		try {
			ImageIO.write(croppedImage, "png", tempImg);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return tempImg;
	}

	private File capturePage() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	private void saveScreenshot(File srcFile, String desPath) {
		try {
			FileUtils.moveFile(srcFile, new File(desPath));
			logger.info("saveScreenshot: screenshot saved at {}", desPath);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private BufferedImage fileToImage(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return image;
	}
}
