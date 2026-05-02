# Script para iniciar todos los microservicios
# Abre cada microservicio en una nueva ventana de PowerShell

Write-Host "================================================"
Write-Host "  Iniciando Microservicios Agrícolas"
Write-Host "  MS-USUARIOS (8081)"
Write.Host "  MS-TERRITORIAL (8082)" 
Write-Host "  MS-INSPECCIONES (8083)"
Write-Host "================================================"
Write-Host ""

$servicios = @(
    @{
        nombre = "MS-USUARIOS"
        puerto = 8081
        ruta = ".\java\usuarios-service"
    },
    @{
        nombre = "MS-TERRITORIAL" 
        puerto = 8082
        ruta = ".\java\predios-service"
    },
    @{
        nombre = "MS-INSPECCIONES"
        puerto = 8083
        ruta = ".\java\inspecciones-service"
    }
)

foreach ($servicio in $servicios) {
    Write-Host "Iniciando $($servicio.nombre) en puerto $($servicio.puerto)..."
    $titulo = "$($servicio.nombre) - Puerto $($servicio.puerto)"
    
    # Abrir nueva ventana de PowerShell
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$((Get-Location).Path)\$($servicio.ruta)' ; mvn spring-boot:run" -WindowStyle Normal -WindowTitle $titulo
    
    Start-Sleep -Seconds 2
}

Write-Host ""
Write-Host "================================================"
Write-Host "  ✅ Todos los microservicios iniciados"
Write-Host "================================================"
Write-Host ""
Write-Host "URLs disponibles:"
Write-Host "  MS-USUARIOS:      http://localhost:8081"
Write-Host "  MS-TERRITORIAL:   http://localhost:8082"
Write-Host "  MS-INSPECCIONES:  http://localhost:8083"
Write-Host ""
Write-Host "Health Checks:"
Write-Host "  http://localhost:8081/api/usuarios/health"
Write-Host "  http://localhost:8082/api/predios/health"
Write-Host "  http://localhost:8083/api/inspecciones/health"
Write-Host ""
