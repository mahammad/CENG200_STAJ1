# Arduino modelleri
> arduino modellərinin öyrənilməsi və nümunələri.

## mündəricat
- Təməl Arduino Funksiyonları
- [Seri Port](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#seri-port)
- [Servo](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#servo-motor)
- [LDR](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#ldr)
- [Buzzer](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#buzzer)
- [Solar Panel](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#solar-panel)
- [Ultrasonic sensor](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#ultrasonic-sensor)
- [ikport pult](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#)

### Təməl Arduino Funksiyonları

 - `Setup()` funksiyonu <br>
		Setup funksiyonu, kod işləməyə başladığında Arduino'nun ilk olaraq oxuduğu kod bloqdur. Arduino bu qismi oxuduqdan sonra digər qisimləri oxumaqa başlayar. Bu qism sadəcə bir dəfə oxunur və program esnasında yeniden okunmaz. Bu alanda, pinlərin çalışma modları gibi önemli və bir dəfə edilməsi gərəkən bütün tənzimləmələr bu bloqda yerinə yetirilir. <br>
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
Arduino'ya komando göndərmək və yaxud sensorlardakı dəyərləri göstərmək üçün seri xəbərləşmədən istifadə olunur. `USB` üzərindən kompyuterə məlumat göndərmək üçün arduino'nun `0` və `1` nömrəli yəni `Rx` və `Tx` pinləri seri xəbərləşməni təşkil etməkdədirlər. Bu pinlər eyni zamanda Arduino'nun kompyuterlə xəbərləşməsini təşkil edən `USB` xəttinə də bağlıdır. 0 və 1 nömrəli pinlər başqa bir yerə bağlı olduğunda, Arduino kompyuterlə xəbərləşməsi mümkün olmur. *Bu pinlən Arduinonun özəl pinləridir və sadəcə xəbərləşmə üçün istifadə ounmalıdır.*

### Servo Motor

Servo motor 0 ilə 180 dərəcə arasında 1 dərəcə dəqiqliyi ilə dönəbilən motor çeşididir. Tam dövrə edə bilməz. Tam dövrə dönməsinə ehtiyac olmayan, xəssas dərəcəli yerlərdə istifadə olunur. Servo motor içərisində bir ədəd DC motor vardır. DC motorun ucuna bağlı dişli sisteminin köməyilə servo daha cox yük qaldıra bilməkdədir. Bu proses nəticəsində servonun dönmə sürətidə yavaşlamış olur. İstifadə olunan dişli sisteminə görə servo motorların qaldıra biləcəyi yük dəyişir. Servoların qaldıra biləcəyi yük torq gücü üzərindən ifadə edilir. Servo motorların torqu, motor miline bağlı 1 cm uzunluqundaki çubuğun qaldıra biləcəyi maksimum yük olaraq tarif edilir. Ümumi servolar 1,4 kgf.cm torqa sahibdir. Bu da deməkdir ki, motor milinizə bağlı 1 cm uzunluqunda bir çubuq varsa və bu çubuqun ucuna bağlı yük 1,4 kiloqramdan coxsa motorunuzun gücü mili döndürməyə catmaz. Əgər çubuqun uzunluqu 10 cm isə ən cox 140 qram qaldıra bilərsiniz.
<br>
*Arduino’da servo motor kontrolu üçün özəlləşdirilmiş PWM pinləri var. Bu pinlərin yanında (~) işareti qeyd olunmuşdur.* <br>

![Rotary Servo Anatomy](/arduino-exp/img/servo.png ) 
<br>
**Arduino ilə Servo motor nümunəsi:**
```Arduino
#include <Servo.h>  
Servo servoObj;  /* servo motor obyekti yaradıldı */

void setup()
{
  servoObj.attach(8);  /* Servo motor 8 nömrəli pinə bağlandı */
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
 Üzərinə düşən işıq miqdarına görə müqavimət dəyəri dəyişən elektron devrə parcasıdır. Mühit işığının ölçülməsi lazım olan layihələrdə istifadə edilər.`LDR`'nin müqaviməti əgər üzərinə çox işıq düşürsə sıfıra yaxın, az işıq düşərsə və ya qaranlıq mühitdə isə sonsuza yaxın olmaqdadır. 

![LDR](/arduino-exp/img/ldr.jpg ) 
<br>
**nümurə:** LDR sendorunun aldığı dəyərləri analog giriş vasitəsilə oxuyaq. <br> **Kod:**

```Arduino
const int ldrPin = A2; //analog pin 2

void setup(){
	Serial.begin(9600);
}
void loop(){
	int ldrAnalogRead = analogRead(ldrPin);
	Serial.println(ldrAnalogRead);
	delay(1000);
}
```
### Buzzer
Buzzer, aldığı iki dəyər nəticəsində sadəcə `bib` səsi cıxaran lahiyələrdə xəbərdarlıq siqnalı vermək məqsədilə istifadə olunan elektronik parcadır. <br> *nümunə üçün:* 
```Arduino
const int buzzerPin = 8;  // Buzzer'in giriş pini

void setup() {
  pinMode(buzzerPin, OUTPUT);
  Serial.begin(9600); 
}
//
void loop() {
  digitalWrite(buzzerPin,HIGH);  //buzzer ON
  delay(1000);
}
```

### Ultrasonic sensor
Sensor üzerinde giriş və cıxış olmaq üzərə iki üzü var. Cıxış üzündən mühitə müəyyən bir tezlikdə ultrasəs səs dalqası salınır. Giriş üzü də cıxış üzünün mühitə saldığı müəyyən frekanslardakı səs dalgalarını toplar. Uzaklık ölçümü için öncelikle cıxış üzündən mühitə səs dalğası salınır. Salınan səs dalğası 15 dərəcə bucaq altında mühitə yayılır. Yayılan səs dalğası ətrafında olan bir cismə dəydiyi zaman, cisim üzündən sensora geri yansıdar. Yansıyan dalğanın giriş üzünə gəlməsiylə işləm tamamlanır. Dalğanın cıxış üzündən çıxmasıyla giriş üzüne geri qayıtması arasında keçen vaxt ölçülərək, cismin uzaqlığı hesaplanır. Bu bəsit məntiqlə işləyən sensor, 2 cm ilə 200 cm arasındaki uzaqlıqları 1 cm həssasiyət ilə ölçəbilməkdədir. Sensor mühit xaricindəki uzaqlıqları xətalı olaraq ölçməkdədir. <br>

![Ultasonic](/arduino-exp/img/ultrasonic.png) <br> 
*Sensor üzərində `VCC`, `Trig`, `Echo`, `GND` olmaq üzərə 4 ədəd pin mövcuddur. Bunlardan `VCC` pini enerji (5 volt), `GND` pini (-) mənfi. `Trig` pini cıxış üzündən dalğanın salınmasını təşkil edən pindir. `Echo` pini ise giriş üzüne yansıyan dalğanın hər hansısa obyektə cattığını Arduino'ya xəbər verən pindir. Nətəcə etibarilə Arduino'da program yazarken `trig` pini cıxış, `echo` pini isə giriş olaraq qeyd olunmalıdır.* <br>
![Ultasonic](/arduino-exp/img/ultrasonic1.png) <br>
**Nümunə üçün ultrasonic sensor ölçmə:** <br>
![Ultasonic Numune](/arduino-exp/img/Ultrasonic-Sensor-Equasions.png ) <br>


### Sensor
<br>
-----------
**problem, xəta .vs [bildir](https://github.com/mahammad/CENG200_STAJ1/issues/new)** və ya :email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#arduino-modelleri)| [Ana Səhifə](https://github.com/mahammad/CENG200_STAJ1/blob/master/rm/az.md#azerkosmos-t%C9%99cr%C3%BCb%C9%99-program%C4%B1-tap%C5%9F%C4%B1r%C4%B1qlar%C4%B1) |[CENG200 STAJ - 1](https://github.com/mahammad/CENG200_STAJ1#ceng200-staj---1)	     
 ---|----|----