cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q interaactionGaze-windows
C:\"Program Files (x86)"\InterAACtionBoxAFSR\lib\wget\wget.exe -q -c --show-progress https://github.com/InteraactionGroup/interaactionGaze/releases/latest/download/interAACtionGaze-windows.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('interAACtionGaze-windows.zip', '../InterAACtionBoxAFSR')
powershell ren interAACtionGaze-windows interAACtionGaze
del interAACtionGaze-windows.zip
exit