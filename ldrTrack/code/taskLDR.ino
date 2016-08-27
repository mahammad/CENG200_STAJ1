
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



