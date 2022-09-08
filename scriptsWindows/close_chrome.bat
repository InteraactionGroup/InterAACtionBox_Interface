cd C:\\Users\\%USERNAME%\\Downloads
IF EXIST close161918.txt (
	del close161918.txt
	taskkill /IM chrome.exe /F
	taskkill /IM python.exe /F
	cd "C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows"
	reset_ports.bat
)
exit