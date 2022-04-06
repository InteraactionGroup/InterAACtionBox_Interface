#!/bin/sh

actualLanguage=$LANG

firstname=$1
lastname=$2
data=$3
subject=$4
to=$5

if [ "$actualLanguage" = "fr_FR.UTF-8" ];
then

    sed -i "s/password  xxxxx/password  $6/" ~/.msmtprc

    #Send message to user to tell him that we have received his message
    sed -i "s/To: xxxxx/To: $5/" ~/.email/emaiFrlUser.txt

    cat ~/.email/emailFrUser.txt | msmtp "$5"
    
    #Send message to the dev mail
    sed -i "s/Nom de l'utilisateur : xxxxx/Nom de l'utilisateur : $2/" ~/.email/emailFrDev.txt
    sed -i "s/Prénom de l'utilisateur : xxxxx/Prénom de l'utilisateur : $1/" ~/.email/emailFrDev.txt
    sed -i "s/Mail de l'utilisateur : xxxxx/Mail de l'utilisateur : $5/" ~/.email/emailFrDev.txt
    sed -i "s/Message : xxxxx/Message : $3/" ~/.email/emailFrDev.txt
    sed -i "s/Subject: xxxxx/Subject: $4/" ~/.email/emailFrDev.txt

    cat ~/.email/emailFrDev.txt | msmtp "contact.interaactionBox@gmail.com"

    #Reset password
    sed -i "s/password  $6/password xxxxx/" ~/.msmtprc

    #Reset mail User
    sed -i "s/To: $5/To: xxxxx/" ~/.email/emailFrUser.txt 

    #Reset email Dev
    sed -i "s/Nom de l'utilisateur : $2/Nom de l'utilisateur : xxxxx/" ~/.email/emailFrDev.txt
    sed -i "s/Prénom de l'utilisateur : $1/Prénom de l'utilisateur : xxxxx/" ~/.email/emailFrDev.txt
    sed -i "s/Mail de l'utilisateur : $5/Mail de l'utilisateur : xxxxx/" ~/.email/emailFrDev.txt
    sed -i "s/Message : $3/Message : xxxxx" ~/.email/emailFrDev.txt
    sed -i "s/Subject: $4/Subject: xxxxx" ~/.email/emailFrDev.txt

else

    sed -i "s/password  xxxxx/password  $6/" ~/.msmtprc

    #Send message to user to tell him that we have received his message
    sed -i "s/To: xxxxx/To: $5/" ~/.email/emailEnUser.txt

    cat ~/.email/emailEnUser.txt | msmtp "$5"
        
    #Send message to the dev mail
    sed -i "s/User lastname : xxxxx/Nom de l'utilisateur : $2/" ~/.email/emailEnDev.txt
    sed -i "s/User firstname : xxxxx/Prénom de l'utilisateur : $1/" ~/.email/emailEnDev.txt
    sed -i "s/User mail : xxxxx/Mail de l'utilisateur : $5/" ~/.email/emailEnDev.txt
    sed -i "s/Message : xxxxx/Message : $3/" ~/.email/emailEnDev.txt
    sed -i "s/Subject: xxxxx/Subject: $4/" ~/.email/emailEnDev.txt

    cat ~/.email/emailEnDev.txt | msmtp "contact.interaactionBox@gmail.com"

    #Reset password
    sed -i "s/password  $6/password xxxxx/" ~/.msmtprc

    #Reset mail User
    sed -i "s/To: $5/To: xxxxx/" ~/.email/emailEnUser.txt 

    #Reset email Dev
    sed -i "s/User lastname : $2/Nom de l'utilisateur : xxxxx/" ~/.email/emailEnDev.txt
    sed -i "s/User firstname : $1/Prénom de l'utilisateur : xxxxx/" ~/.email/emailEnDev.txt
    sed -i "s/User mail : $5/Mail de l'utilisateur : xxxxx/" ~/.email/emailEnDev.txt
    sed -i "s/Message : $3/Message : xxxxx" ~/.email/emailEnDev.txt
    sed -i "s/Subject: $4/Subject: xxxxx" ~/.email/emailEnDev.txt

fi

exit
