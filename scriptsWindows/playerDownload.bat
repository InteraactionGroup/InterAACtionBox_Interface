cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q InterAACtionPlayer
C:\"Program Files (x86)"\InterAACtionBoxAFSR\lib\wget\wget.exe -q -c --show-progress https://github.com/AFSR/InterAACtionPlayer-AFSR/releases/latest/download/InterAACtionPlayer.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionPlayer.zip', '../InterAACtionBoxAFSR')
del InterAACtionPlayer.zip
exit