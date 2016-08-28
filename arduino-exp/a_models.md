# Arduino modelleri
> arduino modellərinin öyrənilməsi və numunələr

## mündericat
- [Servo](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#servo-motor)
- [LDR](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#ldr)
- [Solar Panel](https://github.com/mahammad/CENG200_STAJ1/blob/master/arduino-exp/a_models.md#solar-panel)

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

```
<br>
### LDR

### Solar Panel
