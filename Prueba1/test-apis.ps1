# Ejemplos de requests HTTP para probar las APIs
# Usar con Invoke-RestMethod en PowerShell

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "  Pruebas de APIs - Microservicios" -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""

# Verificar que los microservicios están corriendo
Write-Host "1. Verificando microservicios..." -ForegroundColor Yellow

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8081/api/usuarios/healthcheck" -Method Get
    Write-Host "   ✓ MS-USUARIOS (8081): $response" -ForegroundColor Green
} catch {
    Write-Host "   ❌ MS-USUARIOS (8081): No responde" -ForegroundColor Red
}

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8082/api/departamentos" -Method Get
    Write-Host "   ✓ MS-TERRITORIAL (8082): Responde correctamente" -ForegroundColor Green
} catch {
    Write-Host "   ❌ MS-TERRITORIAL (8082): No responde" -ForegroundColor Red
}

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8083/api/inspecciones" -Method Get
    Write-Host "   ✓ MS-INSPECCIONES (8083): Responde correctamente" -ForegroundColor Green
} catch {
    Write-Host "   ❌ MS-INSPECCIONES (8083): No responde" -ForegroundColor Red
}

Write-Host ""

# ===== EJEMPLOS DE USO =====

# Ejemplo 1: Crear un productor
Write-Host "2. Creando productor..." -ForegroundColor Yellow
$productor = @{
    documentoIdentidad = "123456789"
    nombres = "Juan Carlos"
    apellidos = "Pérez García"
    telefono = "555-1234"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8081/api/productores" `
                                 -Method Post `
                                 -ContentType "application/json" `
                                 -Body $productor
    Write-Host "   ✓ Productor creado: $($response.nombres) $($response.apellidos)" -ForegroundColor Green
} catch {
    Write-Host "   ℹ Ya existe o error: $($_.Exception.Message)" -ForegroundColor Yellow
}

# Ejemplo 2: Listar productores
Write-Host ""
Write-Host "3. Listando productores..." -ForegroundColor Yellow
try {
    $productores = Invoke-RestMethod -Uri "http://localhost:8081/api/productores" -Method Get
    Write-Host "   ✓ Total de productores: $($productores.Count)" -ForegroundColor Green
    foreach ($p in $productores) {
        Write-Host "     • $($p.nombres) $($p.apellidos) - Doc: $($p.documentoIdentidad)" -ForegroundColor White
    }
} catch {
    Write-Host "   ❌ Error al listar: $($_.Exception.Message)" -ForegroundColor Red
}

# Ejemplo 3: Crear departamento
Write-Host ""
Write-Host "4. Creando departamento..." -ForegroundColor Yellow
$departamento = @{
    codigoDane = "05"
    nombre = "Antioquia"
    region = "Andina"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8082/api/departamentos" `
                                 -Method Post `
                                 -ContentType "application/json" `
                                 -Body $departamento
    Write-Host "   ✓ Departamento creado: $($response.nombre)" -ForegroundColor Green
} catch {
    Write-Host "   ℹ Ya existe o error" -ForegroundColor Yellow
}

# Ejemplo 4: Crear lugar de producción
Write-Host ""
Write-Host "5. Creando lugar de producción..." -ForegroundColor Yellow
$lugarProduccion = @{
    codigoIca = "ICA001"
    nombre = "Finca El Sol"
    areaProductiva = 15.5
    tipoExplotacion = "Orgánico"
    numeroPredialPredio = "PRED001"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8082/api/lugares-produccion" `
                                 -Method Post `
                                 -ContentType "application/json" `
                                 -Body $lugarProduccion
    Write-Host "   ✓ Lugar de producción creado: $($response.nombre) ($($response.codigoIca))" -ForegroundColor Green
} catch {
    Write-Host "   ℹ Ya existe o error" -ForegroundColor Yellow
}

# Ejemplo 5: Crear inspección (valida que el lugar exista)
Write-Host ""
Write-Host "6. Creando inspección fitosanitaria..." -ForegroundColor Yellow
$inspeccion = @{
    fechaVisita = "07/03/2026"
    estadoPlaga = "Controlado"
    conceptoTecnico = "Aplicar fertilizante nitrogenado según recomendaciones"
    codigoIcaLugarProduccion = "ICA001"
    idAsistenteTecnico = "987654321"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8083/api/inspecciones" `
                                 -Method Post `
                                 -ContentType "application/json" `
                                 -Body $inspeccion
    Write-Host "   ✓ Inspección creada: ID $($response.id)" -ForegroundColor Green
    Write-Host "     Fecha: $($response.fechaVisita)" -ForegroundColor White
    Write-Host "     Estado: $($response.estadoPlaga)" -ForegroundColor White
} catch {
    Write-Host "   ❌ Error: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "     (Verifica que el lugar de producción ICA001 exista en MS-TERRITORIAL)" -ForegroundColor Yellow
}

# Ejemplo 6: Listar inspecciones
Write-Host ""
Write-Host "7. Listando inspecciones..." -ForegroundColor Yellow
try {
    $inspecciones = Invoke-RestMethod -Uri "http://localhost:8083/api/inspecciones" -Method Get
    Write-Host "   ✓ Total de inspecciones: $($inspecciones.Count)" -ForegroundColor Green
    foreach ($i in $inspecciones) {
        Write-Host "     • ID: $($i.id) - Fecha: $($i.fechaVisita) - Estado: $($i.estadoPlaga)" -ForegroundColor White
    }
} catch {
    Write-Host "   ❌ Error al listar: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host ""
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "  Pruebas completadas" -ForegroundColor Green
Write-Host "=========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Consulta README-API.md para más ejemplos" -ForegroundColor Cyan
Write-Host ""
pause
