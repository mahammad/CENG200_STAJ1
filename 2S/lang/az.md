## Sun Spoting (2S)
> azercosmos təcrübə programı tapşırığı

### Mündəricat
1. Məqsədi
2. İstifadə olunan elementlər
3. [Kod](/2S/lang/az.md#Kod)

### Məqsədi
2S: Günəş paneli sensoru vasitəsi ilə azimut ve elevation istiqamətlərində sensor üzərindəki maksimum voltaj dəyinə görə günəş şuasının maksimum düşmə bucağını qeyde alır və prosedur bitdikdən sonra servo motorları vasitəsilə günəş panelini qeydə aldığı azimut ve elevation istiqamətlərinə doğru istiqamətləndirir. Bu prosedurlar bittikdən sonra...

### İstifadə olunan elementlər

* Arduino UNO
![](/2S/img/unosxem.png)

* Solar Panel sensoru
![](/2S/img/sunpanel.png)

* 2 ədəd Servo motorla
![](/2S/img/servo.jpg)

* 2 ədəd 10k ohm diod
![](/2S/img/ohm.jpg)

* Bağlantı kabelleri

### Kod

```arduino

/*
 * 1. Solar Panel sensor vasitesile güneşden gelen işığın ölcerek en yüksek voltaja göre azimut ve elevation değerlerinin tapılması
 * 2. Birinci addımda tapılan
 */
#include "Servo.h"
#include "math.h"
#include "time.h"

// Azimut ve Elevation servo'larının ve başlanğıc derecelerinin define'ları
Servo servoYatay;
Servo servoDikey;
int yatayDerece = 180;
int dikeyDerece = 60;
// Giriş pinleri
const int panelPin=   A1;     // Solar panel analog girişi
const int servoYpin = 10;     // Azimutal servo giriş pin
const int servoDpin = 11;     // Elevation servo giriş pin
// Solar traking değişkenleri
float solarVoltage =   0;     // hesaplanmış Voltage deyeri
int BatteryValue =     0;
int minVal=0, maxVoltage=0,maxVal=0,maxValLocation=0, azimut=0, elevation=0;


//----------------------------------{ -Setup- }----------------------------------//
void setup() {

  Serial.begin(9600);
  servoYatay.attach(servoYpin);
  servoDikey.attach(servoDpin);
  servoYatay.write(yatayDerece);
  servoDikey.write(dikeyDerece);
}

//----------------------------------{ -Dikey Analiz- }----------------------------------//
void dikey() {
   solarVoltage = 0;  BatteryValue = 0;
   minVal=0; maxVoltage=0; maxVal=0;  maxValLocation=0;
   for(int i = 0; i<= 180; i++)
    {
       servoDikey.write(180);
       delay(20);
    }
    dikeyDerece=180;
    for(int j=0; j <= 180; j++)
    {
      solarVoltage=readVoltage();
      if(dikeyDerece > 0)
      {
        if(maxVoltage < solarVoltage)
        {
          maxVoltage=solarVoltage;
          maxVal=solarVoltage;
          maxValLocation=dikeyDerece;
          dikeyDerece-=2;
        }
        else
        {
          minVal=solarVoltage;
          if(minVal < solarVoltage)
          {
            maxVal=solarVoltage;
          }
          else
          {
            minVal = solarVoltage;
          }
          dikeyDerece-=2;
        }
      }
      else
      {
        elevation=maxValLocation;
        for(int i = 0; i<= maxValLocation; i++)
        {
           servoDikey.write(maxValLocation);
           delay(20);
        }
      Serial.print("elevation"); Serial.print(elevation); Serial.print("\n");
      exit(0);
      }
      servoDikey.write(dikeyDerece);
      Serial.print("["); Serial.print(dikeyDerece); Serial.print("] - Solor Voltage:"); Serial.print(solarVoltage); Serial.print(" - Max Location: "); Serial.print(maxValLocation);Serial.print(" --Best Voltage:"); Serial.print(maxVoltage); Serial.print("\n");

      delay(800);
   }
}
//----------------------------------{ Loop }----------------------------------//
void loop() {
  solarVoltage=readVoltage();
  if(yatayDerece > 0)
  {
    //Serial.println("...........................{ -Azimut   (Yatay düzlem)- }...........................");
    if(maxVoltage < solarVoltage)
    {
      maxVoltage=solarVoltage;
      maxVal=solarVoltage;
      maxValLocation=yatayDerece;
      yatayDerece-=2;
    }
    else
    {
      minVal=solarVoltage;
      if(minVal < solarVoltage)
      {
        maxVal=solarVoltage;
      }
      else
      {
        minVal = solarVoltage;
      }
      yatayDerece-=2;
    }
  }
  else
  {
    azimut=maxValLocation;
    for(int i = 0; i<= maxValLocation; i++)
    {
       servoYatay.write(maxValLocation);
       delay(10);
    }
    Serial.print("azimut"); Serial.print(elevation); Serial.print("\n");
    //Serial.println("...........................{ -Elevation(Dikey düzlem)- }...........................");
    dikey();
    sunAzEl(41,49,4);
    int i= azimut, j = elevation;
    while (i-->= 0) {
      servoDikey.write(azimut);
    }
    while (j-->= 0) {
      servoYatay.write(elevation);
    }
    exit(0);
  }
  servoYatay.write(yatayDerece);
  Serial.print("["); Serial.print(yatayDerece); Serial.print("] - Solor Voltage:"); Serial.print(solarVoltage); Serial.print(" - Max Location: "); Serial.print(maxValLocation);Serial.print(" --Best Voltage:"); Serial.print(maxVoltage); Serial.print("\n");

  delay(1000);
}
//Solar sensordan verinin okunup işlenmesi
float readVoltage() {
  int Value  = analogRead(panelPin);
  float solarVolt = (float(Value)*2)/100;
   return solarVolt;
}
/*
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\
//
\\
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\                                      Sun Azimut Elevation hesaplama değişkenleri                                               \\
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\
*/
double pi = 3.14159265359;
double degs = 180.0/pi;
double rads = pi/180.0;
double L,RA,g,daylen,delta,x,y,z;
double sunDiametr = 0.53;
double AirRefr = 34.0/60.0; // atmosferik qırılma derecesi

//   Get the days to J2000
//   h is UT in decimal hours
//   FNday only works between 1901 to 2099 - see Meeus chapter 7
double FNday (int y, int m, int d, float h) {
  int luku = - 7 * (y + (m + 9)/12)/4 + 275*m/9 + d;
  luku+= (long int)y*367;
  return (double)luku - 730530.0 + h/24.0;
};

// o ile 360 arasındakı bucaq
double FNrange (double x) {
    double b = x / (2 * pi);
    double a = (2 * pi) * (b - (long)(b));
    if (a < 0)
      a = (2 * pi) + a;
    return a;
};

// Saat bucağını hesablayan
double f0(double lat, double declin) {
  double fo,dfo;
  dfo = rads*(0.5*sunDiametr + AirRefr);
  if (lat < 0.0)
    dfo = -dfo; // Southern hemisphere
  fo = tan(declin + dfo) * tan(lat*rads);
  if (fo>0.99999)
    fo=1.0; // to avoid overflow //
  fo = asin(fo) + pi/2.0;
  return fo;
};

// Günəşin ecliptic uzunluq dairəsini tapmaq
double FNsun (double d) {
  double w,M,v,r;
  // Güneşin longitude
  w = 282.9404 + 4.70935E-5 * d;
  M = 356.047 + 0.9856002585 * d;
  // Sun's mean longitude
  L = FNrange(w * rads + M * rads);
  // Güneşin anomaliyası
  g = FNrange(M * rads);
  double ecc = 0.016709 - 1.151E-9 * d;
  // ekliptik meyillilik
  double obliq = 23.4393 * rads - 3.563E-7 * rads * d;
  double E = M + degs * ecc * sin(g) * (1.0 + ecc * cos(g));
  E = degs*FNrange(E*rads);
  x = cos(E*rads) - ecc;
  y = sin(E*rads) * sqrt(1.0 - ecc*ecc);
  r = sqrt(x*x + y*y);
  v = atan2(y,x)*degs;
  // güneşin longitutu
  double lonsun = v + w;
  lonsun-= 360.0*(lonsun>360.0);
  // Günəşin ecliptic düzbucaqlı koordinatları
  x = r * cos(lonsun*rads);
  y = r * sin(lonsun*rads);
  double yequat = y * cos(obliq);
  double zequat = y * sin(obliq);
  RA = atan2(yequat,x);
  delta = atan2(zequat,sqrt(x*x + yequat*yequat));
  RA*= degs;
  // Güneşin ecliptic longitu'tu
  return FNrange(L + 1.915 * rads * sin(g) + .02 * rads * sin(2 * g));
};

//
void sunAzEl(double latit, double longit, double tzone){
  double year,m,day,h;
  time_t sekunnit;
  struct tm *p;
//  get the date and time from the user
// read system date and extract the year
/** tarixin alınması **/
  time(&sekunnit);
/**localtime **/
  p=localtime(&sekunnit);
  year = p->tm_year;
  year+= 1900;
  m = p->tm_mon + 1;
  day = p->tm_mday;
  h = p->tm_hour + p->tm_min/60.0;

// testi
// year = 1990; m=4; day=19; h=11.99;  // local time
  double UT = h - tzone;  // universal time
  double jd = FNday(year, m, day, UT);
//   Use FNsun to find the ecliptic longitude of the
//   Sun
  double lambda = FNsun(jd);
//   Obliquity of the ecliptic
  double obliq = 23.4393 * rads - 3.563E-7 * rads * jd;
// Sidereal time at Greenwich meridian
  double GMST0 = L*degs/15.0 + 12.0;  // hours
  double SIDTIME = GMST0 + UT + longit/15.0;
// Hour Angle
  double ha = 15.0*SIDTIME - RA;  // degrees
  ha = FNrange(rads*ha);
  x = cos(ha) * cos(delta);
  y = sin(ha) * cos(delta);
  z = sin(delta);
  double xhor = x * sin(latit*rads) - z * cos(latit*rads);
  double yhor = y;
  double zhor = x * cos(latit*rads) + z * sin(latit*rads);
  double  azim = atan2(yhor,xhor) + pi;
  azim = FNrange(azim);
  double altit = asin(zhor) * degs;
// delta = asin(sin(obliq) * sin(lambda));
  double alpha = atan2(cos(obliq) * sin(lambda), cos(lambda));
//   Find the Equation of Time in minutes
  double equation = 1440 - (L - alpha) * degs * 4;
  ha = f0(latit,delta);
// Conversion of angle to hours and minutes //
  daylen = degs*ha/7.5;
  if (daylen<0.0001) {
    daylen = 0.0;
  }
// arctic winter     //
  double riset = 12.0 - 12.0 * ha/pi + tzone - longit/15.0 + equation/60.0;
  double settm = 12.0 + 12.0 * ha/pi + tzone - longit/15.0 + equation/60.0;
  double noont = riset + 12.0 * ha/pi;
  double altmax = 90.0 + delta*degs - latit;
  if (altmax > 90.0)
    altmax=180.0 - altmax; //to express as degrees from the N horizon
  noont-= 24*(noont>24);
  if (riset > 24.0)
    riset-= 24.0;
  if (settm > 24.0)
    settm-= 24.0;
  azimut = azim*degs;
  elevation = altit;
  Serial.print("Azimuth :" ); Serial.print(azim*degs); Serial.print("\n");
  Serial.print("Altitude:" ); Serial.print(altit); Serial.print("\n");
}
/** Finish**/
```

#### [yuxarı](/2S/lang/az.md)
