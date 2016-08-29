# Arduino modelleri
> arduino modellərinin öyrənilməsi və numunələr

## mündəricat
- Təməl Arduino Funksiyonları
- [Seri Port](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#seri-port)
- [Analog Siqnal Oxuma](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#)
- [Servo](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#servo-motor)
- [LDR](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#ldr)
- [Arduino ilə Uzaqlıq Ölçmək](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#)
- [Solar Panel](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#solar-panel)

### Təməl Arduino Funksiyonları

 - `Setup()` funksiyonu <br>
		Setup funksiyonu, kod işləməyə başladığında Arduino'nun ilk olaraq oxuduğu kod bloqdur. Arduino bu qismi oxuduqdan sonra digər qisimləri oxumaqa başlayar. Bu qism sadəcə bir dəfə oxunur və program esnasında yeniden okunmaz. Bu alanda, pinlerin çalışma modları gibi önemli və bir dəfə edilməsi gərəkən bütün tənzimləmələr bu bloqda yerinə yetirilir. <br>
 - `Loop()` funksiyonu <br> 
		Setup funksiyonunsan sonra işləməyə başlayan funksuyondur. Bu bir ana funksiyondur və proqramın etməsini istədiyimiz proseslər buraya yazılır. Loop funksiyonu, sonsuz döngə şəklindədir, yəni buradaki proseslər bitdikdən sonra, proqram təkrar başa dönərək işləmləri yənidən təkrarlar. Bu döngə, Arduino işlədiyi müddətcə davam edər.
 - `PinMode()` <br>
		Arduino kartı üzərində bulunan pinləri çıxış və yaxud giriş olaraq istifadə edə bilərik. Giriş olaraq istifadə olunan pinlər arduinoya gələn məlumatları qəbul etmək üçün, çıxış olaraq istifadə olunan pinlərdə isə arduinodan gedən məlumatları istifadə etmək üçün istifadə olunur. Daha cox proqramda istifadə olunacaq pinləri `Setup()` funksiyasında qeyd etmək üçün istifadə olunur. 
	    nümunə üçün: <br>
```Arduino
		pinMode(12,OUTPUT); 	//12. pini cıxış olaraq qeyd edirik 
		pinMode(12,INPUT);  	//12. pini giriş olaraq qeyd edirik 
```
 - `DigitalWrite()`
 		Cıxış olaraq göstərilmiş pinlərə enerji vermək(`HIGH`) və ya enerjini kəsmək(`LOW`) üçün ustifadə olunur. 
```Arduino
 		digitalWrite(12,HIGH);      //12. pinə enerji vermək
 		digitalWrite(12,LOW);       //12. pindən enerjini kəsmək
```
 - `DigitalRead()`
 		Arduinonun digital pin girişlərindən məlumatları oxumaq üçün istifadə olunur. 
```Arduino
 		int digitalOxunan = digitalRead(12);      //12. pindən məlumatı oxumaq
``` 
 - `AnalogRead()`
 		Arduinonun analog pin girişlərindən(A0,A1,A2...) dəqiq məlumatlar oxumaq üçün istifadə olunur. 
```Arduino
 		int analogOxunan = digitalRead(A2);      //A2. pindən məlumatı oxumaq
``` 
 - `Delay()`
 		Arduinonun `Loop()` funksiyonundakı proseslerdeki döngə zamanını tənzimləmək üçün istifadə olunan funksiyondur.
```Arduino
 		delay(1000);     // ~1 saniyəlik döngənin zaman aralığlı üçün 1000 istifadə olunur
```
 
<br>
### Seri Port
Arduino'ya komando göndərmək və yaxud sensorlardakı dəyərləri göstərmək üçün seri xəbərləşmədən istifadə olunur. USB üzərindən kompyuterə məlumat göndərmək üçün seri xəbərləşmə protundan istifadə edəcəyik.

Arduino'nun 0 və 1 numaralı yani Rx və Tx pinleri seri xəbərlə sağlamaktadır. Bu pinler aynı zamanda Arduino'nun bilgisayarla xəbərləşmə sağlayan USB hattına da bağlıdır. 0 və 1 numaralı pinler başka bir yere bağlı olduğunda, Arduino bilgisayarla xəbərləşmə sağlayamamaktadır. Bu yüzden Arduino'ya kod atarken bu pinlerin bir yere bağlı olmamasına dikkat edilmelidir.

### Servo Motor

Servo motor 0 ilə 180 dərəcə arasında 1 dərəcə dəqiqliyi ilə dönəbilən motor çeşididir. Tam dövrə edə bilməz. Tam dövrə dönməsinə ehtiyac olmayan, xəssas dərəcəli yerlərdə istifadə olunur. Servo motor içərisində bir ədəd DC motor vardır. DC motorun ucuna bağlı dişli sisteminin köməyilə servo daha cox yük qaldıra bilməkdədir. Bu proses nəticəsində servonun dönmə sürətidə yavaşlamış olur. İstifadə olunan dişli sisteminə görə servo motorların qaldıra biləcəyi yük dəyişir. Servoların qaldıra biləcəyi yük torq gücü üzərindən ifadə edilir. Servo motorların torqu, motor miline bağlı 1 cm uzunluqundaki çubuğun qaldıra biləcəyi maksimum yük olaraq tarif edilir. Ümumi servolar 1,4 kgf.cm torqa sahibdir. Bu da deməkdir ki, motor milinizə bağlı 1 cm uzunluqunda bir çubuq varsa və bu çubuqun ucuna bağlı yük 1,4 kiloqramdan coxsa motorunuzun gücü mili döndürməyə catmaz. Əgər çubuqun uzunluqu 10 cm isə ən cox 140 qram qaldıra bilərsiniz.
<br>
*Arduino’da servo motor kontrolu üçün özəlləşdirilmiş PWM pinləri var. Bu pinlərin yanında (~) işareti qeyd olunmuşdur.* <br>

```Arduino
#include <Servo.h>  
Servo servoObj;  /* servo motor obyekti yaradıldı */

void setup()
{
  servoObj.attach(9);  /* Servo motor 9 nömrəli pinə bağlandı */
}
 
void loop()
{
  servoObj.write(100);  /* Motorun mili 100. dərəcəyə döndər */
  delay(1000);
  servoObj.write(20);   /* Motor mili 20. dərəcəyə döndər */
  delay(1000);
}
```
<br>
### LDR
Üzerine düşen ışık miktarına göre direnç değeri değişen elektronik devre elemanıdır. Ortam ışığının ölçülmesi gereken projelerde kullanılır. LDR'nin direnci eğer üzerine fazla ışık düşüyorsa sıfıra yakın, az ışık düşüyor vaya karanlık ortamda ise sonsuza yakın olmaktadır.

Yapacağımız projelerde sıklıkla kullanacağımız devre elemanlarını ve bu elemanların kullanım nedenini öğrendik.

Bu bölümde öğrenilen bilgiler, Arduino projelerinde kurulan devreleri anlamaya yardımcı olacaktır. Bu nedenle yeni başlayanlar için, bu bölümün zaman zaman tekrar edilmesi yararlı olacaktır.

### Solar Panel


<br>
-----------
**problem, xəta .vs [bildir](https://github.com/mahammad/CENG200_STAJ1/issues/new)** və ya :email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#arduino-modelleri)| [Ana Səhifə](/rm/az.md) |[CENG200 STAJ - 1](https://github.com/mahammad/CENG200_STAJ1#ceng200-staj---1)	     
 ---|----|----