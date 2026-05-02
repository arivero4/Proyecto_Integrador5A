# Script para abrir el frontend en el navegador
# Uso: .\open-frontend.ps1

param(
    [int]$port = 8000
)

$frontendPath = Get-Location
$indexPath = Join-Path $frontendPath "index.html"

if (-not (Test-Path $indexPath)) {
    Write-Host "❌ Archivo index.html no encontrado en: $frontendPath" -ForegroundColor Red
    exit 1
}

Write-Host "🌾 Sistema de Gestión Agrícola - Frontend" -ForegroundColor Green
Write-Host "📁 Ruta: $frontendPath" -ForegroundColor Cyan
Write-Host ""

# Verificar si Python está disponible
$pythonAvailable = $null -ne (Get-Command python -ErrorAction SilentlyContinue)
$nodeAvailable = $null -ne (Get-Command npm -ErrorAction SilentlyContinue)

if ($pythonAvailable) {
    Write-Host "✅ Python detectado. Iniciando servidor local..." -ForegroundColor Green
    Write-Host "🌐 Abre tu navegador en: http://localhost:$port/frontend" -ForegroundColor Cyan
    Write-Host ""
    python -m http.server $port
} 
elseif ($nodeAvailable) {
    Write-Host "✅ Node.js detectado. Instalando http-server..." -ForegroundColor Green
    npm install -g http-server
    Write-Host "🌐 Abre tu navegador en: http://localhost:$port" -ForegroundColor Cyan
    Write-Host ""
    http-server -c-1 -p $port
} 
else {
    Write-Host "⚠️  Ni Python ni Node.js detectados." -ForegroundColor Yellow
    Write-Host "📖 Opciones:" -ForegroundColor Cyan
    Write-Host "  1. Instala Python desde https://www.python.org" -ForegroundColor White
    Write-Host "  2. Instala Node.js desde https://nodejs.org" -ForegroundColor White
    Write-Host "  3. O abre directamente: $indexPath" -ForegroundColor White
    Write-Host ""
    Write-Host "🖱️  Abriendo archivo directamente..." -ForegroundColor Cyan
    Invoke-Item $indexPath
}
