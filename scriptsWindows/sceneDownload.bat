cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
certutil -urlcache -split -f https://github.com/InteraactionGroup/InterAACtionScene/releases/latest/download/InterAACtionScene.zip InterAACtionScene.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionScene.zip', '../InterAACtionBoxAFSR')
del InterAACtionScene.zip