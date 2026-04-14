package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Jenkins/Linux safe mode
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new ChromeDriver(options);

            driver.get("https://automationexercise.com/");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // -------------------------------
            // Step 1: Click Products
            // -------------------------------
            WebElement productsLink = driver.findElement(By.cssSelector("a[href='/products']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsLink);
            Thread.sleep(2000);

            // -------------------------------
            // Step 2: Add to cart
            // -------------------------------
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
            Thread.sleep(2000);

            // -------------------------------
            // Step 3: Close modal
            // -------------------------------
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".close-modal"))
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
            Thread.sleep(2000);

            System.out.println("TITLE: " + driver.getTitle());

            driver.quit();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
