# ldrTrack 
> Ətrafında olan işıq şüalarını qrafiq olaraq nəticələndirən və bu qrafiqi alt və üst limitlər ilə limitləyən, limitlərə görə siqnal və led işıq vasitəsilə xəbərdarlıq verən robotik və java program təminatından ibarət olan tapşırıq.

Tapşırıq robotik və program təminatından ibarətdir.
### 1. Java program
  Program usb port vasitəsilə gələn məlumatı oxuyaraq bu məlumata görə istifadəciyə qrafiq yaradır. Limit xanalarına görə isə bu qrafiqi alt və üst limitlərə ayırmaqdadır. Anlıq oxunan məlumata görə hər hansısa limit kecilərsə program usb port vasitəsilə 2 xəbardarlıq məlumatı göndərir, anlıq oxunan məlumat stabil hala qayıdarsa bu zaman xəbərdarlıqların deaktiv edilməsi üçün 2 deaktiv et məlumatını usb port vasitəsilə göndərər. Programda `jfree` qrafiq cəkmə kitabxanası və usb dən məlumat oxumaq üçün `fazecast` kitabxanasından istifadə olunmuşdur.

  Program [kod:](/ldrTrack/code/sensorGraph.java)
  <br>
  ![](/ldrTrack/img/s_graph.png) 
  <br>
### 2. Robotik
Tapşırığın bu mərhələsində Arduino vasitəsilə üzərinə düşən işığı ölcən istifadəcidən gələn komandolara görə isə siqnal və işıq yandıran bəsit robotdur.
- istifadə olunan elemntlər
	- LDR: foto müqvimət göstərici
	- Buzzer: siqnal modul
	- 10k ohm x 2 ədəd müqavimət
	- LED
	- Arduino
<br>
![sxem](/ldrTrack/img/s_ino.png) 
<br>
 #### Mərhələnin acıqlaması:
 	Arduino `LDR` modul vasitəsilə anlıq oxuduğu voltajı `Serial.begin(9600)` port vasitəsilə programa göndərir və bu port vasitəsilə programdan gələn məlumatlara görə də robotumuz xəbardarlıq siqnalları yaradır.Bu məlumatlar 4 fərqli əmr yerinə yetirir. Bu dörd əmr: 
 	* 0 - (ledi yandır)
 	* 1 - (ledi söndür)
 	* 2 - (siqnali aktiv et)
 	* 3 - (siqnali deaktiv et) 

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
- Tapşırıq video carx [Video](https://www.youtube.com/watch?v=Vl69tx_xEss)
----------------------------

**problem, xəta .vs [bildir](https://github.com/mahammad/CENG200_STAJ1/issues/new)**

:email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/ldrTrack/lang/az.md#ldrtrack) | [Ana səhifə](/rm/az.md)| [ldrTrack: Ana Səhifə](/ldrTrack/README.md)     
 ----|----|----
