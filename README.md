TRENDYOL PROJECT

* BUG kayıtları, test senaryoları ve analiz ile ilgili çalışmalar projenin içine eklenmiştir.

* Test otomasyonunda Java+Selenium+TestNG kullanılmıştır.

* Raporlamalar için allure report kullanılmıştır.

* Rapor çıktıları testresultscreeshot klasörünün altındadır.

* Testleri çalıştığında allure report hatası verirse aşağıdaki adımlar işletilmelidir.

1.Brew Yükleme

       Terminal ekranınızı açın ve aşağ~~~~ıdaki kodu çalıştırın.

       ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)

2.Allure Yükleme

     Terminalde aşağıdaki kodu çalıştırın.

      brew install allure

3.Allure Çalıştırma

    Projenin dizinindeyken aşağıdaki kodu çalıştırdığınızda Allure açılmış olacak.

    allure serve
