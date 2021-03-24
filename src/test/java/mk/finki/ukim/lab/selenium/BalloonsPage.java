package mk.finki.ukim.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends AbstractPage {

    public BalloonsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".balloon-items")
    private List<WebElement> itemNum;

    @FindBy(css = ".delete-balloon")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".edit-balloon")
    private List<WebElement> editButtons;

    @FindBy(className = "submit-order")
    private List<WebElement> submitButton;

    @FindBy(className = "add-new-balloon")
    private List<WebElement> addNewButton;

    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public void assertElements(int itemNum, int deleteButtons, int editButtons, int submitButton, int addButtons) {
        Assert.assertEquals("items do not match", itemNum, this.getItemNum().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("submit do not match", submitButton, this.getSubmitButton().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddNewButton().size());
    }
}
