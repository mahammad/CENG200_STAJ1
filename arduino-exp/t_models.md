# Arduino moduls
> arduino modüllerinin öğrenilmesi ve örnekleri.

## İçerik
- Temel Arduino Fonksiyonları
- [Seri Port](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#seri-port)
- [Servo](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#servo-motor)
- [LDR](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#ldr)
- [Buzzer](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#buzzer)
- [Ultrasonic sensor](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#ultrasonic-sensor)

### Temel Arduino Fonksiyonları

 - `Setup()` fonksiyonu <br>
    Setup fonksiyonu, bu fonksiyon program çalıştırılmaya başladığı zaman çalışmasını istediğimiz ayarlamalar yapılır ki bunlar bir kereye mahsus yapılması istenen ayarlamalardır. <br>
 - `Loop()` fonksiyonu <br> 
    Setup fonksiyonun, program `Setup()` fonksiyonunu çalıştırdıktan sonra programda sürekli çalışması istenen kodlar yazılır ki bunlar Arduino çalıştığı zaman sürekli çalışmaktadır.
 - `PinMode()` <br>
    Arduino'ya gelen ve giden bilgileri ayarlamak için kullanılan fonksiyondur eğer Arduino'ya bilgi göndermek istiyorsak `INPUT`, tam tersi Arduino'dan bilgi göndermek istiyorsak `OUTPUT`  değişkenleri ile belirtiyoruz. Genellikle `Setup()` fonksiyonunda programın ilk çalıştığı zaman yapılması istenen tanımlamalardan biridir. 
      örnek için: <br>
```Arduino
    pinMode(12,OUTPUT);   //12. pini cıkış olarak tanımala 
    pinMode(12,INPUT);    //12. pini giriş olarak tanımala 
```
 - `DigitalWrite()`
    Her hangi pine programın algoritmasına bağlı olarak güç vermek `HIGH` veya güç kesmek (`LOW`) yani `1` veya `0` durumlarını ayarlamak için kullanılmaktadır. 
```Arduino
    digitalWrite(12,HIGH);      //12. pine güç vermek 'ON'
    digitalWrite(12,LOW);       //12. pine güç almak  'OFF'
```
 - `DigitalRead()`
    Arduino'nun digital pin girişlerinden verileri okumak için kullanılmaktadır.
```Arduino
    int digitalOkunan = digitalRead(12);      //12. pinden veriyi okumak
``` 
 - `AnalogRead()`
    Arduino'nun analog pin girişlrrinden(A0,A1,A2...) daha çok net verileri okumak için kullanılmaktadır.
```Arduino
    int analogOkunan = digitalRead(A2);      //A2. pinden veriyi okumak
``` 
 - `Delay()`
    Arduino'nun `Loop()` fonksiyonunun döngü zamanını ayarlamak için kullanılır.
```Arduino
    delay(1000);     // ~1 saniyelik dönme zamanı.
```
 
<br>
### Seri Port
Arduino'ya komut göndermek veya bilgileri görüntülemek için `USB` üzerinden bilgisayara bilgi göndermek için Arduino'nun `0` ve `1` numaralı yani `Rx` ve `Tx` pinleri seri haberleşmektedir. Bu pinler aynı zamanda Arduino'nun bilgisayar ile haberleşmesini ayarlayan `USB` hattına bağlanır. `0` ve `1` numaralı pinler başka yere bağlı olduğu zaman, Arduino'nun bilgisayarla haberleşememektedir. *Bu pinler Arduino'nun özel pinleridir ve sadece haberleşme için kullanılmalıdır.*

### Servo Motor
Servo motor 0 ila 180 derece arasında 1 derece hassasiyetle dönebilen motor çeşididir. Tam tur atamaz. Genellikle robot kol gibi tam tur dönmesine gerek olmayan, hassas açılı yerlerde kullanılır. Servo motor içerisinde bir adet DC motor bulunur. DC motorun ucuna bağlı dişli sisteminin yardımıyla servo mili daha fazla yük kaldırabilmektedir. Bu işlem sırasında servonun dönüş hızı da yavaşlamış olur. Kullanılan dişli sistemine göre servo motorların kaldırabileceği yük değişir.Servoların kaldırabileceği yük tork gücü üzerinden ifade edilir. Servo motorların torku, motor miline bağlı 1 cm uzunluğundaki çubuğun kaldırabileceği maksimum yük olarak tarif edilir. Piyasada bulunan servolar genellikle 1,4 kgf.cm torka sahiptir. Bu da demek oluyor ki, motor milinize bağlı 1 cm uzunluğunda bir çubuk varsa ve bu çubuğun ucuna bağlı yük 1,4 kilogramdan fazlaysa motorunuzun gücü mili döndürmeye yetmez. Eğer çubuğun uzunluğu 10 cm ise en fazla 140 gram kaldırabilirsiniz.
<br>
*Arduino’da servo motor kontrolü için özelleştirilmiş PWM pinleri bulunmaktadır. Bu pinlerin yanında dalga (~) işareti bulunmaktadır.* <br>

![Rotary Servo Anatomy](/arduino-exp/img/servo.png ) 
<br>
**Arduino ile Servo motor örneği:**
```Arduino
#include <Servo.h>  
Servo servoObj;  /* servo motor objesi yaradıldı */

void setup()
{
  servoObj.attach(8);  /* Servo motor 8 numaralı pine bağlandı */
}
 
void loop()
{
  servoObj.write(100);  /* Motorun mili 100. dereceye döner */
  delay(1000);
  servoObj.write(20);   /* Motor mili 20. dereceye döner */
  delay(1000);
}
```
<br>
### LDR
 ÜÜzerine düşen ışık miktarına göre direnç değeri değişen elektronik devre elemanıdır. Ortam ışığının ölçülmesi gereken projelerde kullanılır. LDR'nin direnci eğer üzerine fazla ışık düşüyorsa sıfıra yakın, az ışık düşüyor vaya karanlık ortamda ise sonsuza yakın olmaktadır.

![LDR](/arduino-exp/img/ldr.jpg ) 
<br>
**Örnek:** LDR sensörünün aldığı değerleri analog girişi ile okuyalım<br> **Kod:**

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
Buzzer, aldığı iki değer sonucunda sadece `bip` sesi cıkaran projelerde uyarı sinyali üretmek amaclı kullanılan elektronik parçadır. <br> *örnek için:* 
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
Sensör üzerinde giriş ve çıkış olmak üzere iki yüzey bulunmaktadır. Çıkış yüzeyinden ortama belirli bir frekansta ultrasonik ses dalgası salınır. Giriş yüzeyi de çıkış yüzeyinin ortama saldığı belirli frekanslardaki ses dalgalarını toplar. Uzaklık ölçümü için öncelikle çıkış yüzeyinden ortama ses dalgası salınır. Salınan ses dalgası 15 derece açıyla ortamda yayılır. Yayılan ses dalgası bu alanda bulunan bir cisme çarptığında, cisim yüzeyinden sensöre geri yansır. Yansıyan dalganın giriş yüzeyine gelmesiyle işlem tamamlanır. Dalganın çıkış yüzeyinden çıkmasıyla giriş yüzeyine ulaşması arasında geçen süre ölçülerek, cismin uzaklığı hesaplanır. Bu basit mantıkla çalışan sensör, 2 cm ile 200 cm arasındaki uzaklıkları 1 cm hassasiyetle ölçebilmektedir. Sensör bu aralık dışındaki uzaklıkları istikrarlı olarak ölçememektedir. <br>

![Ultasonic](/arduino-exp/img/ultrasonic.png) <br> 
Sensör üzerinde `VCC`, `Trig`, `Echo`, `GND` olmak üzere 4 adet pin bulunmaktadır. Bunlardan `VCC` pini besleme (5 volt), `GND` pini toprak hattıdır. `Trig` pini çıkış yüzeyinden dalganın salınmasını sağlayan pindir. `Echo` pini ise giriş yüzeyine yansıyan dalganın ulaştığını Arduino'ya haber veren pindir. Sonuç olarak program yazarken Arduino'da `trig` pini çıkış, `echo` pini ise giriş olarak tanımlanmalıdır. <br>
![Ultasonic](/arduino-exp/img/ultrasonic1.png) <br>
**Örnek için:** *ultrasonic sensör ölçümü ve hesaplanması.* <br>
![Ultasonic Numune](/arduino-exp/img/Ultrasonic-Sensor-Equasions.png ) <br>


<br>
-----------

**hata, öneri .vb için [yazınız](https://github.com/mahammad/CENG200_STAJ1/issues/new)** veya :email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/t_models.md#arduino-moduls)| [Ana Sayfa](https://github.com/mahammad/CENG200_STAJ1/blob/master/rm/tr.md) |[CENG200 STAJ - 1](https://github.com/mahammad/CENG200_STAJ1#ceng200-staj---1)       
 ---|----|----
