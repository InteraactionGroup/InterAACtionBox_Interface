#!/bin/bash
while [ ! -e ~/T�l�chargements/close161918.txt ] && [ ! -e ~/Downloads/close161918.txt ]
do
    sleep 1
done
pkill chrome
rm -f ~/T�l�chargements/close161918.txt
rm -f ~/Downloads/close161918.txt
