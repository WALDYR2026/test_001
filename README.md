# LABORATORIO: Java Factorial + Pruebas JUnit + GitHub Actions

## Objetivo
Comprender la integración continua (CI) a través de un caso muy simple y verificable.

**Programa productivo**: `src/main/java/edu/laboratorio/Factorial.java`.
**Programa de prueba**: `src/test/java/edu/laboratorio/FactorialTest.java`.
**Automatización GitHub**: `.github/workflows/java-ci.yml`.

## Requisitos
- JDK 21
- Apache Maven 3.9 o compatible
- VS Code con Extension Pack for Java (recomendado)
- Git y cuenta GitHub

## Algoritmo
`5! = 5 * 4 * 3 * 2 * 1 = 120`.
`0! = 1`. Los números negativos no tienen factorial definido en este programa.
El límite permitido es 20, porque 21! desborda el tipo `long`.

## Paso 1. Abrir en VS Code
Extraer el archivo ZIP, abrir la carpeta `java-factorial-github-actions` y abrir una terminal integrada.
Verificar:

```powershell
java -version
mvn -version
```

## Paso 2. Ejecutar la aplicación

```powershell
mvn clean compile
mvn exec:java
```

Salida esperada: `Factorial de 5 = 120`.

## Paso 3. Ejecutar pruebas JUnit

```powershell
mvn test
```

Salida esperada: `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` y `BUILD SUCCESS`.
Las pruebas revisan `0!`, `1!`, `3!`, `5!`, `7!` y rechazo del número negativo.

## Paso 4. Publicar en GitHub
Crear un repositorio público o privado **vacío** llamado `java-factorial-github-actions`.
Ejecutar desde la carpeta del proyecto:

```powershell
git init
git branch -M main
git add .
git commit -m "Laboratorio Java factorial y pruebas JUnit"
git remote add origin https://github.com/TU_USUARIO/java-factorial-github-actions.git
git push -u origin main
```

Reemplazar `TU_USUARIO` por su identificador. Debe disponer de credenciales Git configuradas.

## Paso 5. Observar GitHub Actions
En GitHub abrir repositorio > pestaña **Actions** > **Java Factorial CI** > corrida más reciente > job `build-and-test`.
Verificar cada etapa:
1. Clonar repositorio
2. Configurar Java 21
3. Compilar
4. Correr pruebas unitarias
5. Ejecutar factorial

El workflow se dispara al hacer push a main o crear/actualizar pull requests hacia main.

## Paso 6. Provocar un fallo intencional
Abrir `src/test/java/edu/laboratorio/FactorialTest.java`.
En `pruebaFactorialDeCinco`, cambiar temporalmente:

`assertEquals(120, Factorial.calcular(5));`

por

`assertEquals(121, Factorial.calcular(5));`

Después correr `mvn test`. **Debe fallar**: se esperaba 121, pero el programa devuelve 120.

Subir el error al repositorio:

```powershell
git add .
git commit -m "Demostración de prueba fallida"
git push
```

En GitHub Actions el job se marca en rojo y muestra el test fallido. La etapa posterior de ejecutar el programa no se realizará si fallan las pruebas.

## Paso 7. Reparar el test
Restaurar el valor esperado `120` y subirlo:

```powershell
git add .
git commit -m "Corregir prueba factorial"
git push
```

Comprobar de nuevo en Actions: se espera una ejecución verde.

## Explicación para pizarra
- `@Test`: designa el método como una prueba JUnit.
- `assertEquals(esperado, real)`: comprueba igualdad.
- `assertThrows`: comprueba que una operación inválida produce la excepción esperada.
- `pom.xml`: declara JUnit y plugins Maven.
- `mvn test`: compila y lanza tests mediante Surefire; si falla una prueba, Maven devuelve un código de error.
- `.github/workflows/java-ci.yml`: especifica cuándo y dónde ejecutar estos comandos automáticamente.
- Una prueba fallida **no implica** que la aplicación deba fallar al arrancar; refleja el incumplimiento de una condición de prueba.

## Nota de verificación
El programa principal puede compilarse con `javac` sin Maven; las pruebas JUnit requieren las dependencias Maven.
El resultado exacto de GitHub Actions solo se confirma después de publicar el repositorio y ejecutar el workflow.
