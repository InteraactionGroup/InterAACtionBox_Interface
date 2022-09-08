cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q InterAACtionScene
C:\"Program Files (x86)"\InterAACtionBoxAFSR\lib\wget\wget.exe -q -c --show-progress https://github.com/AFSR/InterAACtionScene-AFSR/releases/latest/download/InterAACtionScene.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionScene.zip', '../InterAACtionBoxAFSR')
del InterAACtionScene.zip
exit