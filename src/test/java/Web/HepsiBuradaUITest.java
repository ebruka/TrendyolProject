package Web;
import com.hepsiburadabase.controller.TestController;
import com.hepsiburadapages.pages.HomePage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class HepsiBuradaUITest extends TestController {


    @Test
    @Parameters("browser")
    public void UrunDegerlendir(@Optional("chrome")String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .aramaYap("iphone")
                .uruneGit(1)
                .urunDegerlendir()
                .kontrolYorum();


    }


    @Test
    @Parameters("browser")
    public void SiralaFiltreDegerKontrolu(@Optional("chrome")String browser) {
        startTest(new HomePage(getDriver(browser)))
                .anaSayfayaGit()
                .aramaYap("iphone")
                .uruneGit(1)
                .urunDegerlendir()
                .kontrolSiralaFltresi();


    }
}
