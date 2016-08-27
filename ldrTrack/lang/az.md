# ldrTrack 
> Ətrafında olan işıq şüalarını qrafiq olaraq nəticələndirən və bu qrafiqi alt və üst limitlər ilə limitləyən, limitlərə görə siqnal və led işıq vasitəsilə xəbərdarlıq verən robotik və java program təminatından ibarət olan tapşırıq.

Tapşırıq robotik və program təminatından ibarətdir.
1. Java program
  Program usb port vasitəsilə gələn məlumatı oxuyaraq bu məlumata görə istifadəciyə qrafiq yaradır. Limit xanalarına görə isə bu qrafiqi alt və üst limitlərə ayırmaqdadır. Anlıq oxunan məlumata görə hər hansısa limit kecilərsə program usb port vasitəsilə 2 xəbardarlıq məlumatı göndərir, anlıq oxunan məlumat stabil hala qayıdarsa bu zaman xəbərdarlıqların deaktiv edilməsi üçün 2 deaktiv et məlumatını usb port vasitəsilə göndərər. Programda `jfree` qrafiq cəkmə kitabxanası və usb dən məlumat oxumaq üçün `fazecast` kitabxanasından istifadə olunmuşdur.

  Program [kod:](/ldrTrack/code/sensorGraph.java)
  <br>
  ![](/ldrTrack/img/s_graph.png) 
  <br>
2. Robotik
	Tapşırığın bu mərhələsində Arduino vasitəsilə üzərinə düşən işığı ölcən istifadəcidən gələn komandolara görə isə siqnal və işıq yandıran bəsit robotdur.
	- istifadə olunan elemntlər
		- LDR: foto müqvimət göstərici
		- Buzzer: siqnal modul
		- 10k ohm x 2 ədəd müqavimət
		- LED
		- Arduino
![sxem](/ldrTrack/img/s_ino.png) 
 Mərhələnin acıqlaması:
