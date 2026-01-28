package com.saucdemo.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {
    //1 Step (Always)
    private WebDriver driver; // обьвляет вебдрайдер

    public InventoryPage(WebDriver driver) { //создаем конструктор
        this.driver = driver;
        PageFactory.initElements(driver, this);// делаем PageFactory
    }

    //2Step - обявляем список
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemList;


    private final By inventoryButton = By.xpath((".//button[contains(@class,'btn_inventory')]"));

    public void selectItemByName(String itemName) {
        //  inventoryItemList.size();// View List Items
        for (int i = 0; i < inventoryItemList.size(); i++) { //идем по тексту и делаем проверку
            if (inventoryItemList.get(i).getText().contains(itemName))//получает текст и проверяем check if Item description has text
            {
                inventoryItemList.get(i).findElement(By.xpath(".//button[contains(@class,'btn_inventory')]")).click();

            }

        }
        System.out.println("Inventory page check DONE");
    }


}





