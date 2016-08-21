#-*- coding: utf-8 -*-
import csv
import sys
import subprocess

	
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
#subprocess.call("rm -rf *.txt", shell=True)

