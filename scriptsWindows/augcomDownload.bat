cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q AugCom
C:\"Program Files (x86)"\InterAACtionBoxAFSR\lib\wget\wget.exe -q -c --show-progress https://github.com/AFSR/AugCom-AFSR/releases/latest/download/AugCom.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('AugCom.zip', '../InterAACtionBoxAFSR')
del AugCom.zip
exit