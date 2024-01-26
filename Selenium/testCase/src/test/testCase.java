package test;
//import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;

import java.util.Objects;


public class testCase implements TestManager {
    private WebDriver driver;

    public static void main(String[] args) {
        testCase test = new testCase();
        test.runTest();
    }


    public void runTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/centrum-gut-and-immunity-probiotics-15g-x-30pcs/p/321265");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/mannings-honey-moist-honey-lip-balm-45g/p/723924");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/lazartigue-nourish-conditioner-150ml/p/425488");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/royal-medic-urate-control-60pcs/p/282368");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/mannings-essential-aloe-vera-gel-350ml/p/560318");





        double total = getTotalPrice("https://hb-np-mnhk-cdn-ms.azureedge.net/cart");
        System.out.printf("%.2f\n", total);

        double totalDisplay = getDisplayTotalPrice("https://hb-np-mnhk-cdn-ms.azureedge.net/cart");
        if (total == totalDisplay) {
            System.out.println("Total Price Test Case Passed");
        } else {
            System.out.println("Total Price Test Case Failed");
        }

        clickBanner("https://www.mannings.com.hk");

    }

    @Override
    public void addProductToCart(String productURL) {
        driver.navigate().to(productURL);
        driver.findElement(By.id("addToCartButton")).click();
    }

    @Override
    public double getTotalPrice(String cartUrl) {
        driver.navigate().to(cartUrl);
        double total = 0.0;

        String itemsCount = driver.findElement(By.xpath("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[7]/div[1]/div[2]")).getText();
        int itemCount = Integer.parseInt(itemsCount.replaceAll("\\D+", ""));

        for (int i = 1; i <= itemCount; i++) {

            String xpathPriceA = String.format("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[7]/ul/div[%d]/div[1]/div[1]/div[2]/span[1]", i); //PreProd
            String xpathPriceB = String.format("/html/body/main/div[2]/div[11]/div[4]/div[3]/div[1]/div[5]/ul/div[%d]/div[1]/div[1]/div[2]/span[1]", i); //Mannings
            String FreeGiftXPath = String.format("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[7]/ul/div[%d]/div[1]/div[1]/div[2]/span[1]", i);

            String FreeGift = driver.findElement(By.xpath(FreeGiftXPath)).getText();

            if (Objects.equals(FreeGift, "FREE"))
                continue;
            else
                try {
                    String price = driver.findElement(By.xpath(xpathPriceA)).getText();
                    total += Double.parseDouble(price.replace("$", ""));
                } catch (NoSuchElementException e) {
                    String price = driver.findElement(By.xpath(xpathPriceB)).getText();
                    total += Double.parseDouble(price.replace("$", ""));
                }

        }

        return total;
    }

    @Override
    public double getDisplayTotalPrice(String cartUrl) {
        driver.navigate().to(cartUrl);
        String displayPrice = driver.findElement(By.xpath("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[7]/div[5]/div[1]/div/div[1]/div[2]/div[8]")).getText();
        String displayTotalPrice = displayPrice.replace("$", "");
        return Double.parseDouble(displayTotalPrice);
    }

    @Override
    public void clickBanner(String homeUrl) {

        System.out.println("Banner Name: ");

        for (int i = 1; i <= 6; i++) {
            driver.navigate().to(homeUrl);

            String bannerXpath = String.format("/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]/div", i);
            // "/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]"
            // "/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]/div"

            driver.findElement(By.xpath(bannerXpath)).click();
            String bannerName = driver.findElement(By.xpath("/html/body/main/div[2]/div[11]/div[2]/ol/li[2]/span")).getText();
            try {
                System.out.println(bannerName);
            } catch (NoSuchElementException e) {
                System.out.println("Banner Test Case Failed");
            }

        }
        for (int i = 1; i <= 6; i++) {
            driver.navigate().to(homeUrl);

            String bannerXpath = String.format("/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]/div", i);
            // "/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]"
            // "/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]/div"

            driver.findElement(By.xpath(bannerXpath)).click();
            String bannerName = driver.findElement(By.xpath("/html/body/main/div[2]/div[11]/div[2]/ol/li[2]/span")).getText();
            try {
                System.out.println(bannerName);
            } catch (NoSuchElementException e) {
                System.out.println("Banner Test Case Failed");
            }

        }

        for (int i = 1; i <= 4; i++) {
            driver.navigate().to(homeUrl);

            String bannerXpath = String.format("/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[2]/a/div[2]/div", i);

            //"/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[1]/div/div/div[2]/a/div[2]/div"

            driver.findElement(By.xpath(bannerXpath)).click();
            String bannerName = driver.findElement(By.xpath("/html/body/main/div[2]/div[11]/div[2]/ol/li[2]/span")).getText();
            try {
                System.out.println(bannerName);
            } catch (NoSuchElementException e) {
                System.out.println("Banner Test Case Failed");
            }

        }
    }
}