# Script para verificar conectividad a Oracle 10g
# Uso: .\verify-oracle.ps1

Write-Host "🗄️  Verificación de Oracle 10g" -ForegroundColor Green
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Green
Write-Host ""

# Configuración
$OracleHost = "localhost"
$OraclePort = 1521
$OracleSID = "XE"

# Test 1: Verificar conectividad al puerto
Write-Host "[1/4] Verificando puerto TCP 1521..." -ForegroundColor Cyan
try {
    $tcpClient = New-Object System.Net.Sockets.TcpClient
    $connect = $tcpClient.BeginConnect($OracleHost, $OraclePort, $null, $null)
    $wait = $connect.AsyncWaitHandle.WaitOne(2000, $false)
    
    if ($wait -and $tcpClient.Connected) {
        Write-Host "  ✓ Puerto 1521 abierto - Oracle está corriendo" -ForegroundColor Green
        $tcpClient.Close()
    } else {
        Write-Host "  ✗ Puerto 1521 cerrado - Oracle NO está corriendo" -ForegroundColor Red
        Write-Host "    Inicia Oracle:" -ForegroundColor Yellow
        Write-Host "    > net start OracleServiceXE" -ForegroundColor White
    }
} catch {
    Write-Host "  ✗ Error al conectar: $_" -ForegroundColor Red
}

Write-Host ""

# Test 2: Verificar JAVA_HOME
Write-Host "[2/4] Verificando Java..." -ForegroundColor Cyan
$javaVersion = java -version 2>&1 | Select-String "version"
if ($javaVersion) {
    Write-Host "  ✓ Java instalado: $javaVersion" -ForegroundColor Green
} else {
    Write-Host "  ✗ Java no encontrado" -ForegroundColor Red
}

Write-Host ""

# Test 3: Verificar Maven
Write-Host "[3/4] Verificando Maven..." -ForegroundColor Cyan
$mavenPath = "C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd"
if (Test-Path $mavenPath) {
    Write-Host "  ✓ Maven 3.9.15 encontrado" -ForegroundColor Green
} else {
    Write-Host "  ✗ Maven no encontrado en ruta esperada" -ForegroundColor Red
    Write-Host "    Instala con: choco install maven" -ForegroundColor Yellow
}

Write-Host ""

# Test 4: Verificar Oracle JDBC Driver
Write-Host "[4/4] Verificando Oracle JDBC Driver..." -ForegroundColor Cyan
$m2Repository = "$env:USERPROFILE\.m2\repository"
$oracleJdbc = Get-ChildItem $m2Repository -Recurse -ErrorAction SilentlyContinue | Where-Object { $_.Name -like "*ojdbc*.jar" }

if ($oracleJdbc) {
    Write-Host "  ✓ Oracle JDBC encontrado: $($oracleJdbc.Name)" -ForegroundColor Green
} else {
    Write-Host "  ✗ Oracle JDBC no encontrado" -ForegroundColor Yellow
    Write-Host "    Se descargará automáticamente al ejecutar 'mvn clean install'" -ForegroundColor White
}

Write-Host ""
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Green
Write-Host "Resumen:" -ForegroundColor Cyan
Write-Host "  URL de conexión: jdbc:oracle:thin:@$OracleHost`:$OraclePort`:$OracleSID" -ForegroundColor White
Write-Host "  Usuario: system" -ForegroundColor White
Write-Host "  Contraseña: oracle" -ForegroundColor White
Write-Host ""
Write-Host "🚀 Próximo paso:" -ForegroundColor Green
Write-Host "  cd microservicios" -ForegroundColor Cyan
Write-Host "  & 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' clean install -DskipTests" -ForegroundColor Cyan
Write-Host ""
