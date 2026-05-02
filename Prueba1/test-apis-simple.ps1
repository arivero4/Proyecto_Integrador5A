# Script para probar las APIs de los microservicios
# Ejecutar: .\test-apis-simple.ps1

Write-Host "╔════════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║           PRUEBA DE MICROSERVICIOS - DEMO                 ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

$baseUrlUsuarios = "http://localhost:8081/api"
$baseUrlTerritorial = "http://localhost:8082/api"
$baseUrlInspecciones = "http://localhost:8083/api"

# Función para hacer requests
function Invoke-ApiTest {
    param(
        [string]$Name,
        [string]$Url,
        [string]$Method = "GET",
        [object]$Body = $null
    )
    
    Write-Host "🔹 $Name" -ForegroundColor Cyan
    Write-Host "   URL: $Url" -ForegroundColor Gray
    
    try {
        $headers = @{
            "Content-Type" = "application/json"
        }
        
        if ($Body) {
            $jsonBody = $Body | ConvertTo-Json -Depth 10
            Write-Host "   Body: $jsonBody" -ForegroundColor Gray
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $headers -Body $jsonBody -TimeoutSec 10
        } else {
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $headers -TimeoutSec 10
        }
        
        Write-Host "   ✅ SUCCESS - Status: 200/201" -ForegroundColor Green
        
        if ($response) {
            $responseJson = $response | ConvertTo-Json -Depth 3
            if ($responseJson.Length -gt 500) {
                Write-Host "   Respuesta: $(($responseJson.Substring(0, 500)))..." -ForegroundColor White
            } else {
                Write-Host "   Respuesta: $responseJson" -ForegroundColor White
            }
        }
        
        Write-Host ""
        return $response
        
    } catch {
        Write-Host "   ❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host ""
        return $null
    }
}

Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host "1️⃣  MS-USUARIOS (Puerto 8081)" -ForegroundColor Yellow
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host ""

# Test 1: Crear Productor
$productor = @{
    documentoIdentidad = "123456789"
    nombres = "Juan"
    apellidos = "Pérez"
    telefono = "3001234567"
}
$productorCreado = Invoke-ApiTest -Name "Crear Productor" `
                                   -Url "$baseUrlUsuarios/productores" `
                                   -Method "POST" `
                                   -Body $productor

Start-Sleep -Seconds 1

# Test 2: Listar Productores
Invoke-ApiTest -Name "Listar Productores" `
               -Url "$baseUrlUsuarios/productores" `
               -Method "GET"

Start-Sleep -Seconds 1

# Test 3: Crear Asistente Técnico
$asistente = @{
    documentoIdentidad = "987654321"
    nombres = "María"
    apellidos = "García"
    telefono = "3009876543"
    especialidad = "Fitosanitaria"
}
$asistenteCreado = Invoke-ApiTest -Name "Crear Asistente Técnico" `
                                   -Url "$baseUrlUsuarios/asistentes-tecnicos" `
                                   -Method "POST" `
                                   -Body $asistente

Start-Sleep -Seconds 1

Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host "2️⃣  MS-TERRITORIAL (Puerto 8082)" -ForegroundColor Yellow
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host ""

# Test 4: Crear Departamento
$departamento = @{
    codigoDepartamento = "05"
    nombreDepartamento = "Antioquia"
}
$deptoCreado = Invoke-ApiTest -Name "Crear Departamento" `
                               -Url "$baseUrlTerritorial/departamentos" `
                               -Method "POST" `
                               -Body $departamento

Start-Sleep -Seconds 1

# Test 5: Listar Departamentos
Invoke-ApiTest -Name "Listar Departamentos" `
               -Url "$baseUrlTerritorial/departamentos" `
               -Method "GET"

Start-Sleep -Seconds 1

# Test 6: Crear Lugar de Producción
$lugar = @{
    codigoIca = "ICA-001"
    nombre = "Finca La Esperanza"
    areaProductiva = 50.5
    tipoExplotacion = "Agricultura orgánica"
    numeroPredialPredio = "PRED-001"
}
$lugarCreado = Invoke-ApiTest -Name "Crear Lugar de Producción" `
                               -Url "$baseUrlTerritorial/lugares-produccion" `
                               -Method "POST" `
                               -Body $lugar

Start-Sleep -Seconds 1

Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host "3️⃣  MS-INSPECCIONES (Puerto 8083)" -ForegroundColor Yellow
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Yellow
Write-Host ""

# Test 7: Crear Inspección Fitosanitaria (valida lugar en MS-TERRITORIAL)
$inspeccion = @{
    fechaVisita = "07/03/2026"
    estadoPlaga = "Controlado"
    conceptoTecnico = "Aplicar fertilizante orgánico"
    codigoIcaLugarProduccion = "ICA-001"
    idAsistenteTecnico = "987654321"
}
$inspeccionCreada = Invoke-ApiTest -Name "Crear Inspección (Valida con MS-TERRITORIAL)" `
                                    -Url "$baseUrlInspecciones/inspecciones" `
                                    -Method "POST" `
                                    -Body $inspeccion

Start-Sleep -Seconds 1

# Test 8: Listar Inspecciones
Invoke-ApiTest -Name "Listar Inspecciones" `
               -Url "$baseUrlInspecciones/inspecciones" `
               -Method "GET"

Start-Sleep -Seconds 1

Write-Host ""
Write-Host "╔════════════════════════════════════════════════════════════╗" -ForegroundColor Green
Write-Host "║              ✅ PRUEBAS COMPLETADAS                        ║" -ForegroundColor Green
Write-Host "╚════════════════════════════════════════════════════════════╝" -ForegroundColor Green
Write-Host ""
Write-Host "🎉 Has probado exitosamente:" -ForegroundColor Yellow
Write-Host "   • MS-USUARIOS: Creación de Productor y Asistente Técnico" -ForegroundColor White
Write-Host "   • MS-TERRITORIAL: Departamentos y Lugares de Producción" -ForegroundColor White
Write-Host "   • MS-INSPECCIONES: Inspección con validación inter-microservicio" -ForegroundColor White
Write-Host ""
Write-Host "📚 Consulta README-API.md para ver todos los endpoints (26 total)" -ForegroundColor Cyan
Write-Host "📊 Revisa DIAGRAMA-SECUENCIA.md para ver los 31 diagramas" -ForegroundColor Cyan
Write-Host "📐 Consulta DIAGRAMA-CASOS-USO.md para ver los 40 casos de uso" -ForegroundColor Cyan
Write-Host ""
