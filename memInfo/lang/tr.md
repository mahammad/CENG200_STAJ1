# MemInfo to Excel
> Sunucuların Geçici Bellek(RAM) bilgilerinin okunup excel dosyasına kayıt edilmesi.

### Görev
 1. aşama, sunucuya listesinde bulunan sunuculara `ssh` bağlanma yöntemi ile sunucuya bağlanmadan `RAM` (Geciçi Bellek) bilgilerini gösteren komutu çalıştırıp çıktıyı text dosyasına yazmak.
 2. aşama, 1. aşamadı çıktıları ecel dosyası oluşturup dosyaya aktarmak.


 #### Görev: 1. aşamasi
Görevde `bash` kabuk programlama dili kullanarak `ssh` bağlanma yöntemi kullanarak sunucuya giriş yapmadan, komut gönderip çıktıyı almak.
* örnek olarak sunucu adı `test` , sunucu `ip`si 178.12.123.78 olan sunucuya `ssh` ile komut gönderelim:
```bash
ssh test@178.12.123.78 free -m
```
örnekde `free -m` komutu sunucunun RAM bilgilerinin çıktısını vermektedir. 
* Görev: 1. aşamasini `bash` kabuk proqramlama dili ile kodunu yazalım.

```bash
#!/bin/bash

connection=('aztest@172.16.78.130','aztest@172.16.78.131','aztest@172.16.78.132')
for index in ${!connection[*]}
do
    echo "Enter the " ${connection[index]} "server's password..."
    ssh ${connection[$index]} whoami  >> ${connection[index]}.txt && date +%d-%m-%Y--%H:%M:%S  >> ${connection[index]}.txt && free -m >> ${connection[index]}.txt
done
```
Kod açıklaması, `connection` adlı listede bağlatı isimleri bulunmakta. Bu sunucularada sıra ile komut çalıştırarak çıktıları bağlantı ismi  ve `.txt` uzantılı dosyaya aktarmaktadır.Ek olarak `whoami` komutu ile sunucunun username ve `date +%d-%m-%Y--%H:%M:%S` komutu ile işlem tarihini txt dosyasına aktarıyoruz.

#### Görev 2. aşaması
1.aşama sonucunda sunucu bilgileri sunucu bağlantı ismine bağlı txt dosyalarına aktarıldı. Bu aşamada oluşan bu txt dosyalarının bilgilerini, python programlama dili kullanarak excel dosyasında gerekli kolonlara yazmak
* Bu aşama için python programlama dilinin `xlsxwriter` kütüphanesinden faydalanacağız.

Bu aşama için örnek kod:
```python
#!/usr/bin/python
import xlsxwriter
import sys
# sunucus output text files list
inputTxtFiles=['aztest@172.16.78.128.txt',
               'aztest@172.16.78.128.txt',
               'aztest@172.16.78.128.txt',
               'aztest@172.16.78.128.txt',
]

with open(inputTxtFiles[0],"r") as TextFile:
    liste = TextFile.read()
listm =liste.split()
# list kontrol
# print(listm)ls
# print(listm[1])
for x in xrange(1,(len(inputTxtFiles)+1)):
# excel default title
    workbook = xlsxwriter.Workbook(str(x)+'_servers_memory_info.xlsx')
    worksheet = workbook.add_worksheet()
    worksheet.write(0, 0, 'Date')
    worksheet.write(0, 1, 'Total')
    worksheet.write(0, 2, 'Used')
    worksheet.write(0, 3, 'Free')
    worksheet.write(0, 4, 'sunucu Name')
# Loop
    worksheet.write(x, 0, listm[0])
    worksheet.write(x, 1, listm[8])
    worksheet.write(x, 2, listm[9])
    worksheet.write(x, 3, listm[10])
    worksheet.write(x, 4, inputTxtFiles[x-1])
    workbook.close()
```

#### Görev son aşama
Bu aşama, iki aşamani birleştiren son olarak kullanılacak programın oldu aşamadır. Algoritmamaız, program kabuk dosyasını çalıştıracak ve oluşan dosyaları işleme alıp sonucunda tek excel dosyasını bize oluşturacaktır.

Son aşama kod:

```python
#-*- coding: utf-8 -*-
import csv
import sys
import subprocess
import xlsxwriter

inputTxtFiles=[
    'aztest@172.16.78.128.txt',
    'aztest@172.16.78.130.txt',
]
subprocess.call("./cmd.sh", shell=True)
# txt lerden dataların oxunması
for x in xrange(0,4):
    with open(inputTxtFiles[x],"r") as TextFile:
        liste=TextFile.read()
        listm =liste.split() #list'ini içindeki boş değerlerin teizlenmesi
        TextFile.close()
# Yeni table yaradıb, oxunan data'nın lazımi prametrelerini tabloya yazma işlemi
    with open('test.cvs', 'a') as csv_file:
        writer = csv.writer(csv_file, delimiter=',')
        writer.writerow(listm[0:8])
        writer.writerow(['']+listm[8:15])
        writer.writerow(['']+listm[15:19])
        csv_file.close()
subprocess.call("rm -rf *.txt", shell=True)
```

Kod açıklaması, program [cmd.sh]( https://github.com/mahammad/CENG200_STAJ1/blob/master/memInfo/code/cmd.sh) dosyasındakı komutları çalıştırdıktan sonra çıktıları `.txt` dosyasına aktaracak. Sonra bu bilgilere göre excel dosyası oluşturulacaktır. Python  ile shell'e komut dosyası veya komut çalıştırmak için gerekli kütüphane `subprocess` . <br>
<br>
-----------

**hata, öneri .vb için [yazınız](https://github.com/mahammad/CENG200_STAJ1/issues/new)** veya :email: `msxiyev@gmail.com`

---------------------------
 :arrow_up: [yuxarı](https://github.com/mahammad/CENG200_STAJ1/blob/master/memInfo/lang/tr.md#meminfo-to-excel)| [Ana Sayfa](https://github.com/mahammad/CENG200_STAJ1/blob/master/rm/tr.md#azerkosmos-staj-program%C4%B1-g%C3%B6revleri) |[CENG200 STAJ - 1](https://github.com/mahammad/CENG200_STAJ1#ceng200-staj---1)       
 ---|----|----

