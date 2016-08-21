# MemInfo to Excel
> Əməli yaddaş informasiyasının oxunub excel faylına cevrilməsi.

### Tapşırıq
 1. mərhələ, listi verilən serverlərə `ssh` vasitəsilə giriş etmədən `RAM` (Əməli yaddaş) informasiyasının oxumaq.
 2. mərhələ, oxunan məlumatların excel faylına yazılması


 #### Tapşırıq: 1. mərhələsi
Tapşırıqda `bash` proqramlama dilidən istifadə edərək, serverə qoşulma vasitəsi olan `ssh` ilə əlaqə qurmadan birbaşa komanda göndəriləcək hansı ki, `RAM` (Əməli yaddaş) informasiyasının oxuyacaq.

* Nümunə olaraq server istifadəcisi `test` , server `ip`si 178.12.123.78 olan serverə `ssh` ilə komanda göndərək:
```bash
ssh test@178.12.123.78 free -m
```
nümunədə `free -m` komandasi hansı ki serverin əməli yaddaşının məlumatlarının oxunmasında istifadə olunur.
* Tapşırıq: 1. mərhələsini `bash` proqramlama dili ilə kodunu yazaq.

```bash
#!/bin/bash

connection=('aztest@172.16.78.130','aztest@172.16.78.131','aztest@172.16.78.132')
for index in ${!connection[*]}
do
	echo "Enter the " ${connection[index]} "server's password..."
	ssh ${connection[$index]} whoami  >> ${connection[index]}.txt && date +%d-%m-%Y--%H:%M:%S  >> ${connection[index]}.txt && free -m >> ${connection[index]}.txt
done
```
Kod acıqlaması, `connection` adlı listdə qoşulma adları girilən serverlərdir. Bu serverlərə sıra ilə komanda göndərərək, komanda nəticələrini qoşulma adına bağlı text faylına qeyd edər. Əlavə olaraq `whoami` komandası ilə serverin istifadəci adı və `date +%d-%m-%Y--%H:%M:%S` komandası ilə komanda göndərdiyi vaxtı qeydə alır.

#### Tapşırıq 2. mərhələsi
1.mərhələ nəticəsində listimizdə olan bütün serverlerə komandalar göndərərək məlumatlarımızı listimizdəki qoşulma adlarına uyğun olaraq text fayllarına qeyd ettik. Bu mərhələdə bu fayllardakı məlumatları excel fayllarına qeyd etmək üçün program yazmalıyıq bunun üçün isə python programlama dilindən istifadə edəcəyik.
* Bu mərhələ üçün python programlama dilinin `xlsxwriter` kitabxanasından istifadə edəcəyik.

Bu mərhələ üçün nümunəvi kod:
```python
#!/usr/bin/python
import xlsxwriter
import sys
# servers output text files list
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
	worksheet.write(0, 4, 'Server Name')
# Loop
	worksheet.write(x, 0, listm[0])
	worksheet.write(x, 1, listm[8])
	worksheet.write(x, 2, listm[9])
	worksheet.write(x, 3, listm[10])
	worksheet.write(x, 4, inputTxtFiles[x-1])
	workbook.close()
```

#### Tapşırıq son merhelesi
Bu mərhələ, iki mərhələni birləşdirən yəni, tək bir program vasitəsi ilə həm server məlumatlarını alıb həm də excel faylın yaradılması. Bu mərhələ python proqramlama dili ilə yazılacaqdır.

Son mərhələ kod:

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

Kod acıqlaması, program [cmd.sh]( CENG200_STAJ1/memInfo/code/cmd.sh ) faylından serverdən məlumatları almaq üçün lazimi komandaları göndərib məlumatları .txt fayllarına qeyd edəcək nəhayətində məlumatlar excel fayla yazılacaq. Python programı ilə shell'e komanda göndərmək üçün,
```python
import subprocess
```
kitabxanasından istifadə olunub. <br>
**problem, xəta .vs [bildir](https://github.com/smehemmed/CENG200_STAJ1/issues/new)**

---------------------------
 :arrow_up: [yuxarı](https://github.com/smehemmed/CENG200_STAJ1/blob/master/memInfo/lang/az.md#meminfo-to-excel)      | [Ana səhifə](https://github.com/smehemmed/CENG200_STAJ1)| [MemInfo: Ana Səhifə](https://github.com/smehemmed/CENG200_STAJ1/tree/master/memInfo)     
 ----|----|----
