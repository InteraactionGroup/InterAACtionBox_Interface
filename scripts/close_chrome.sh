#!/bin/bash
while [ ! -e ~/T�l�chargements/close.txt ] && [ ! -e ~/Downloads/close.txt ]
do
    sleep 1
done
pkill chrome
rm -f ~/T�l�chargements/close.txt
rm -f ~/Downloads/close.txt
