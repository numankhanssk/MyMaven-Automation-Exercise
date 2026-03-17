package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();

        // Create explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // -------------------------------
        // Step 1: Click on Products link
        // -------------------------------
        WebElement productsLink = driver.findElement(By.cssSelector("a[href='/products']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsLink);
        Thread.sleep(2000); // 2 sec delay to see it

        // -------------------------------
        // Step 2: Click Add to Cart button
        // -------------------------------
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.add-to-cart")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
        Thread.sleep(2000); // 2 sec delay to see the modal

        // -------------------------------
        // Step 3: Close modal popup
        // -------------------------------
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.close-modal")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
        Thread.sleep(2000); // 2 sec delay to see it close

        // Optional: close browser
        driver.quit();
    }
}
