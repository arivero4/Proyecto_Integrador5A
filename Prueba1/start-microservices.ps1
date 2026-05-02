# Script para iniciar los 3 microservicios (versión consola)
# Ejecutar: .\start-microservices.ps1

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "  Iniciando Microservicios Agrícolas" -ForegroundColor Cyan
Write-Host "  (Versión Aplicación de Consola)" -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""

# Función para iniciar un microservicio
function Start-Microservice {
    param(
        [string]$Name,
        [string]$Path,
        [int]$Port,
        [string]$MainClass
    )
    
    Write-Host "► Iniciando $Name..." -ForegroundColor Green
    
    # Crear nueva ventana de PowerShell para cada microservicio
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$Path'; Write-Host '=== $Name ===' -ForegroundColor Yellow; Write-Host 'Compilando...' -ForegroundColor Gray; javac -encoding UTF-8 -d . -cp . main\$MainClass*.java modeloC\*.java negocioC\*.java persistenciaC\*.java presentacionC\*.java 2>&1; if (`$LASTEXITCODE -eq 0) { Write-Host 'Ejecutando...' -ForegroundColor Green; java main.$MainClass } else { Write-Host 'Error de compilación' -ForegroundColor Red; pause }"
    
    Start-Sleep -Seconds 2
}

# Verificar que Java está instalado
$javaVersion = java -version 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ ERROR: Java no está instalado o no está en el PATH" -ForegroundColor Red
    Write-Host "   Instala Java 17 o superior: https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Yellow
    pause
    exit
}

Write-Host "✓ Java detectado" -ForegroundColor Green
Write-Host ""

# Obtener ruta actual
$baseDir = $PSScriptRoot
if ([string]::IsNullOrEmpty($baseDir)) {
    $baseDir = Get-Location
}

# Iniciar MS-USUARIOS (Puerto 8081)
Start-Microservice -Name "MS-USUARIOS" `
                   -Path "$baseDir\ms-usuarios" `
                   -Port 8081 `
                   -MainClass "MainUsuarios"

# Iniciar MS-TERRITORIAL (Puerto 8082)
Start-Microservice -Name "MS-TERRITORIAL" `
                   -Path "$baseDir\ms-territorial" `
                   -Port 8082 `
                   -MainClass "MainTerritorial"

# Iniciar MS-INSPECCIONES (Puerto 8083)
Start-Microservice -Name "MS-INSPECCIONES" `
                   -Path "$baseDir\ms-inspecciones" `
                   -Port 8083 `
                   -MainClass "MainInspecciones"

Write-Host ""
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "  ✓ Microservicios iniciados" -ForegroundColor Green
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Aplicaciones de consola disponibles:" -ForegroundColor Yellow
Write-Host "  • MS-USUARIOS:     Gestión de Productores y Asistentes" -ForegroundColor White
Write-Host "  • MS-TERRITORIAL:  Gestión Territorial y Cultivos" -ForegroundColor White
Write-Host "  • MS-INSPECCIONES: Inspecciones Fitosanitarias" -ForegroundColor White
Write-Host ""
Write-Host "Cada microservicio se ejecuta en su propia ventana." -ForegroundColor Cyan
Write-Host ""
Write-Host "NOTA: Para usar las APIs REST, ejecuta UsuariosApplication.java," -ForegroundColor Yellow
Write-Host "      TerritorialApplication.java e InspeccionesApplication.java" -ForegroundColor Yellow
Write-Host "      con Maven: mvn spring-boot:run" -ForegroundColor Yellow
Write-Host ""
Write-Host "Presiona cualquier tecla para cerrar este script..." -ForegroundColor Gray
Write-Host "(Los microservicios seguirán corriendo en sus ventanas)" -ForegroundColor Gray
pause
