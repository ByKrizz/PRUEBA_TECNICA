# generate_hex.ps1
# Script para generar estructura de carpetas con arquitectura hexagonal en Java (Maven)

# Ruta base del proyecto
$basePath = "src\main\java\com\prueba\accounts"
$resourcesPath = "src\main\resources"
$testPath = "src\test\java\com\prueba\accounts"

# Carpetas de arquitectura hexagonal
$folders = @(
    # Dominio (Entidades, Value Objects, Puertos)
    "$basePath\domain\model",
    "$basePath\domain\port\in",
    "$basePath\domain\port\out",

    # Aplicación (Casos de Uso, Servicios de aplicación)
    "$basePath\application\service",
    "$basePath\application\usecase",

    # Infraestructura (Adaptadores, Configuración, Persistencia)
    "$basePath\infrastructure\config",
    "$basePath\infrastructure\adapter\in\web",        # Controladores REST
    "$basePath\infrastructure\adapter\in\cli",        # CLI (opcional)
    "$basePath\infrastructure\adapter\out\persistence", # Repositorios JPA
    "$basePath\infrastructure\adapter\out\external",    # Integraciones externas

    # Recursos
    "$resourcesPath",

    # Tests (unitarios y de integración)
    "$testPath\domain",
    "$testPath\application",
    "$testPath\infrastructure"
)

# Crear carpetas
foreach ($folder in $folders) {
    if (-not (Test-Path $folder)) {
        New-Item -ItemType Directory -Path $folder -Force | Out-Null
        Write-Host "Carpeta creada: $folder"
    } else {
        Write-Host "Carpeta ya existe: $folder"
    }
}

Write-Host "✅ Estructura Hexagonal creada correctamente."
