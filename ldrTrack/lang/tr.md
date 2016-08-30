# ldrTrack 
>  LDR direç yardımı ile bulunduğu alanda üzerine düşen işık veya güneş şualarını değerlerini seri haberleşme(`USB`) ile java programına gönderen robotik ve java programından ibaret olan görev.

### 1. Java program
 Program seri port aracılığı ile Arduino'dan gelen veriyi okur ve Veri zaman grafiği oluşturmakta. Alt ve üst sınırlar ile grafiği sınırlandırma ve bu sınırların kecildiği durumda alarm verme özellikleri mevcut. Programda `jfree`grafik oluşturma və `USB`'den veri okumak için `fazecast` kütüphabeleri kullanılmıştır.

  Program [kod:](/ldrTrack/code/sensorGraph.java)
  <br>
  ![](/ldrTrack/img/s_graph.png) 
  <br>
### 2. Robotik
Görevin bu aşaması çok basit yani Seri haberleşme aracılığıyla okunan veriyi sürekli gönder ve Arduino'ya gelen 4 farklı veriye göre alarm ver. Alarm, led ve buzzer ile sinyal veren elektronik parçalar.
- Gereken elemanlar
    - LDR direnç
    - Buzzer
    - 10k ohm x 2 adet direç
    - LED
    - Arduino
<br>
![şema](/ldrTrack/img/s_ino.png) 
<br>
 #### aşamanın acıklaması:
    Arduino `LDR` direç aracılığı ile okunan voltajı `Serial.begin(9600)` port üzerinden programa gönderir. Programdan gelen veriye göre robotumuz alarm oluşturur.Bu veriler 4 farklı işlem oluşturmakta: 
    * 0 - (ledi yak)
    * 1 - (ledi kapat)
    * 2 - (sinyali aktif et)
    * 3 - (sinyali deaktif et) 

    Arduino kodumuz:

 ```Arduino
        /*
     * ----Task 2----
     * 
     * LDR Sensorundan ölcülen deyeri Java programındakı max min limitlere göre 
     * limit aşıldığında buzzer ile signal gönderib led'in aktiv edilmesi. 
     * 1 --> Led'in aktiv edilmesi
     * 0 --> Led'in deaktiv edilmesi
     * 2 --> Buzzer'in aktiv edilmesi
     * 3 --> Buzzer'in deaktiv edilmesi
     */
    const int ledPin = 2;     // Led'in giriş pini
    const int ldrPin = A0;    // LDR işıq sensorunun digital pin girişi
    const int buzzerPin = 8;  // Buzzer'in giriş pini
    //Programda istifade olunan deyerlerin tanımlanması
    int input=0,ldrValue=0;

    void setup() {
      pinMode(buzzerPin, OUTPUT);
      pinMode(ledPin, OUTPUT); 
      Serial.begin(9600); 
    }
    //
    void loop() {

      ldrValue = analogRead(ldrPin);
      input = Serial.read();
      Serial.println(ldrValue); 
      if(input == '1') {
        digitalWrite(ledPin,HIGH);    //led ON
      }else if(input == '0') { 
        digitalWrite(ledPin,LOW);     //led OFF
      } else if(input == '2'){
        digitalWrite(buzzerPin,LOW); //buzzer OFF
      }else if(input == '3'){
        digitalWrite(buzzerPin,HIGH);  //buzzer ON
      }
      delay(1000);                  
    }
```
----------------------------
- Görev: [Video](https://www.youtube.com/watch?v=Vl69tx_xEss)
----------------------------

**problem, hata .vb [yazınız](https://github.com/mahammad/CENG200_STAJ1/issues/new)** veya :email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/ldrTrack/lang/tr.md#ldrtrack) | [Ana Sayfa](https://github.com/mahammad/CENG200_STAJ1/blob/master/rm/tr.md#azerkosmos-staj-program%C4%B1-g%C3%B6revleri)    
 ----|----

