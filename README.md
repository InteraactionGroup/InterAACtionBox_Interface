# Google Chrome Portable

Un google chrome portable personnalisé et adapté a nos logiciels.
Version actuellement utilisé -> 104.0.5112.81

Si on change de version, pour pouvoir envoie le tout en ligne sur git il faut :
* Aller dans GoogleChromePortable/App/Chrome-bin/104.0.5112.81 (Peut changer selon la version de votre Google Chrome Portable)
* Compresser le fichier chrome.dll sous le nom "chromeDll.zip"
* Et supprimer le fichier chrome.dll
* Ensuite compresser le dossier "Data" sous le nom "Data.zip",  celui-ci se trouve dans le dossier "GoogleChromePortable"
* Pour finir supprimer le dossier "Data"

On fait cela car git n'accepte pas l'envoie de fichier supérieur à 100mo ! <br>
Et aussi, la génération d'un exe via innosetup plnate du à un trop grand nombre de fichier.

# Python

Utilisé pour l'ouverture des ports des applications angular en local.
Version actuellement utilisé -> 3.10

# Wget

GNU Wget pour windows, utilitaire de ligne de commande pour récupérer des fichiers à l'aide des protocoles HTTP, HTTPS et FTP.
Version actuellement utilisé -> 1.21.3