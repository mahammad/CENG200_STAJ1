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
Kod, `connection` adlı listdə qoşulma adları girilən serverlərdir. Bu serverlərə sıra ilə komanda göndərərək, komanda nəticələrini qoşulma adına bağlı text faylına qeyd edər. Əlavə olaraq `whoami` komandası ilə serverin istifadəci adı və `date +%d-%m-%Y--%H:%M:%S` komandası ilə komanda göndərdiyi vaxtı qeydə alır.

#### Tapşırıq 2. mərhələsi
1.mərhələ nəticəsində listimizdə olan bütün serverlerə komandalar göndərərək məlumatlarımızı listimizdəki qoşulma adlarına uyğun olaraq text fayllarına qeyd ettik. Bu mərhələdə bu fayllardakı məlumatları excel fayllarına qeyd etmək üçün program yazmalıyıq bunun üçün isə python programlama dilindən istifadə edəcəyik.
