package Web;
import com.denemebase.controller.TestController;
import com.trendyolpages.pages.HomePage;
import jdk.jfr.Description;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class TrendyolUITest extends TestController {

    @Description("Butik link statüs kodu ve response timelara bakılması.")
    @Test
    @Parameters("browser")
    public void linkBroken(@Optional("chrome") String browser) throws IOException {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit().linkControl();

    }

    @Description("Bşarılı login girişi yapıldığının görülmesi")
    @Test
    @Parameters("browser")
    public void BasariliUyeGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .uyeGirisiYap("e_ebru15@hotmail.com", "Ebka1990");
    }

    @Description("Hatali şifre  girişi yapıldığında doğru hata mesajının alındığının görülmesi.")
    @Test
    @Parameters("browser")
    public void HataliSifreGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .uyeGirisiYap("e_ebru15@hotmail.com", "deneme")
                .controlWarningMessage();

    }
    @Description("Geçersiz eposta girişi yapıldığında doğru hata mesajının alındığının görülmesi.")
    @Test
    @Parameters("browser")
    public void GecersizEpostaGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .uyeGirisiYap("e_ebru15", "deneme")
                .controlWarningMessage();

    }

    @Description("Boş şifre  girişi yapıldığında doğru hata mesajının alındığının görülmesi.")
    @Test
    @Parameters("browser")
    public void BosSifreGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .uyeGirisiYap("e_ebru15@hotmail.com", "")
                .controlWarningMessage();

    }

    @Description("Boş eposta  girişi yapıldığında doğru hata mesajının alındığının görülmesi.")
    @Test
    @Parameters("browser")
    public void BosEpostaGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .uyeGirisiYap("", "dede")
                .controlWarningMessage();

    }
    @Description("Facebook ile giriş yapıldığında Facebook login sayfasının açıldığının görülmesi")
    @Test
    @Parameters("browser")
    public void FacebookGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .facebookGirisi();


    }
    @Description("Google ile giriş yapıldığında Google login sayfasının açıldığının görülmesi")
    @Test
    @Parameters("browser")
    public void GoogleGirisi(@Optional("chrome") String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .googleGirisi();


    }


    }
