#!/bin/bash

yearList=$(ls -la json/private_sector | awk '{print $13}')
nameList=$(ls -la json/private_sector | cut -c51-84)
for i in $nameList
do
	echo $i
	mongoimport --jsonArray -d aws -c privateSector${yearList[i]} --file /json/private_sector/${nameList[i]}
done
#should do the same for 2016 data