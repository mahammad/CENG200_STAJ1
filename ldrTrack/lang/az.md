# ldrTrack 
> Ətrafında olan işıq şüalarını qrafiq olaraq nəticələndirən və bu qrafiqi alt və üst limitlər ilə limitləyən, limitlərə görə siqnal və led işıq vasitəsilə xəbərdarlıq verən robotik və java program təminatından ibarət olan tapşırıq.

Tapşırıq robotik və program təminatından ibarətdir.
1. Java program
  Program usb port vasitəsilə gələn məlumatı oxuyaraq bu məlumata görə istifadəciyə qrafiq yaradır. Limit xanalarına görə isə bu qrafiqi alt və üst limitlərə ayırmaqdadır. Anlıq oxunan məlumata görə hər hansısa limit kecilərsə program usb port vasitəsilə 2 xəbardarlıq məlumatı göndərir, anlıq oxunan məlumat stabil hala qayıdarsa bu zaman xəbərdarlıqların deaktiv edilməsi üçün 2 deaktiv et məlumatını usb port vasitəsilə göndərər. Programda `` qrafiq cəkmə kitabxanası və usb dən məlumat oxumaq üçün `` kitabxanasından istifadə olunmuşdur.

  Program [kod:](/ldrTrack/code/sensorGraph.java)