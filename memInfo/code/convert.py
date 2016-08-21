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