package mk.finki.ukim.lab.selenium;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.service.BalloonService;
import mk.finki.ukim.lab.service.LoginService;
import mk.finki.ukim.lab.service.ManufacturerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    LoginService loginService;

    private HtmlUnitDriver driver;

    private static Balloon b1;
    private static Manufacturer m1;
    private static Balloon b2;
    private static Manufacturer m2;
    private static String user = "user";
    private static String admin = "admin";
    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if(!dataInitialized) {
            m1 = manufacturerService.save("m1", "m1", "m1").get();
            m2 = manufacturerService.save("m2", "m2", "m2").get();

            b1 = balloonService.save("b1", "b1", m1.getId()).get();
            b2 = balloonService.save("b2", "b2", m2.getId()).get();

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(2,0, 0, 0, 0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, admin, admin);
        balloonsPage.assertElements(2,2, 2, 1, 1);

        balloonsPage = AddOrEditBalloonPage.addBalloon(this.driver, b1.getName(), b1.getDescription(), m2.getName());
        balloonsPage.assertElements(3,3, 3, 1, 1);

        balloonsPage = AddOrEditBalloonPage.addBalloon(this.driver, b2.getName(), b2.getDescription(), m1.getName());
        balloonsPage.assertElements(4,4, 4, 1, 1);
    }
}
