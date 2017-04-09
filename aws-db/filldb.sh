#!/bin/bash
mongoimport --jsonArray -d aws -c statusCodes --file ./json/private_sector/codes.json
mongoimport --jsonArray -d aws -c privateSector2014 --file ./json/private_sector/2014_1.json
mongoimport --jsonArray -d aws -c privateSector2014 --file ./json/private_sector/2014_2.json

#should do the same for 2016 data