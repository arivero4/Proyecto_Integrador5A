# Script para probar las APIs de los microservicios

Write-Host "================================================"
Write-Host "  Probando APIs de Microservicios"
Write-Host "================================================"
Write-Host ""

$baseUrl = "http://localhost"

Write-Host "1. Probando MS-USUARIOS (8081)"
Write-Host "   GET /api/usuarios/health"
Invoke-RestMethod -Uri "$baseUrl`:8081/api/usuarios/health" -Method Get | ConvertTo-Json
Write-Host ""

Write-Host "2. Probando MS-TERRITORIAL (8082)"
Write-Host "   GET /api/predios/health"
Invoke-RestMethod -Uri "$baseUrl`:8082/api/predios/health" -Method Get | ConvertTo-Json
Write-Host ""

Write-Host "3. Probando MS-INSPECCIONES (8083)"
Write-Host "   GET /api/inspecciones/health"
Invoke-RestMethod -Uri "$baseUrl`:8083/api/inspecciones/health" -Method Get | ConvertTo-Json
Write-Host ""

Write-Host "================================================"
Write-Host "  Creando Usuario de Prueba"
Write-Host "================================================"
Write-Host ""

$usuarioJson = @{
    nombre = "Juan Carlos Pérez"
    email = "juan@example.com"
    telefono = "+57 300 123 4567"
    tipoUsuario = "PRODUCTOR"
    estado = "ACTIVO"
} | ConvertTo-Json

Write-Host "POST /api/usuarios"
Write-Host $usuarioJson
$respuesta = Invoke-RestMethod -Uri "$baseUrl`:8081/api/usuarios" -Method Post -ContentType "application/json" -Body $usuarioJson
$respuesta | ConvertTo-Json
Write-Host ""

$usuarioId = $respuesta.id

Write-Host "================================================"
Write-Host "  Listando Usuarios"
Write-Host "================================================"
Write-Host ""

Write-Host "GET /api/usuarios"
Invoke-RestMethod -Uri "$baseUrl`:8081/api/usuarios" -Method Get | ConvertTo-Json
Write-Host ""

Write-Host "================================================"
Write-Host "  Prueba Completada"
Write-Host "================================================"
Write-Host ""
