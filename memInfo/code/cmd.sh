#!/bin/bash

connection=('aztest@172.16.78.130','aztest@172.16.78.131','aztest@172.16.78.132')
for index in ${!connection[*]}
do
	echo "Enter the " ${connection[index]} "server's password..."
	ssh ${connection[$index]} whoami  >> ${connection[index]}.txt && date +%d-%m-%Y--%H:%M:%S  >> ${connection[index]}.txt && free -m >> ${connection[index]}.txt
done