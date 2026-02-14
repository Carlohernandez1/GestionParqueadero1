# Proyecto Parqueadero

Sistema de gestión para un parqueadero desarrollado en Java aplicando herencia y polimorfismo.  
Permite registrar entradas y salidas de vehículos, calcular el costo de estadía por tipo y consultar el estado del parqueadero.

---

## Descripción breve
- Tipos de vehículo: **Automóvil**, **Motocicleta**, **Camión** (heredan de `Vehiculo`).
- Cálculo del costo: tarifa por hora según el tipo y tiempo de estadía (fracciones se cobran como **1 hora completa**).
- Interfaz de usuario por **consola** con validaciones e indicaciones de tipo de dato (texto, entero, decimal).
- Permite ver y cambiar **tarifas** y consultar **total recaudado**.

---

## Estructura del código (paquetes y clases)
src/
└─ com/parqueadero/
├─ app/
│    └─ ParqueaderoApp.java      # Punto de entrada y menú de consola
├─ dominio/
│    ├─ Vehiculo.java            # Clase base (abstracta)
│    ├─ Automovil.java
│    ├─ Motocicleta.java
│    └─ Camion.java
├─ negocio/
│    └─ Parqueadero.java         # Lógica: entrada/salida, tarifas, recaudado
└─ dto/
└─ TicketSalida.java        # Datos del ticket de salida

---

## Instrucciones para ejecutar

1. Abrir el proyecto  con **JDK 17**
2. Abrir la clase:

com.parqueadero.app.ParqueaderoApp
3. Ejecutar (**Run**).  
4. Usar el menú de la consola:


Ingresar un vehículo  (la consola indica si el dato es TEXTO, ENTERO o DECIMAL)
Registrar salida de un vehículo
Consultar estado del parqueadero
Ver tarifas actuales
Configurar tarifas
Ver total recaudado
Salir


Tarifas por defecto:
- Automóvil: $3.000/h
- Motocicleta: $1.500/h
- Camión: $7.000/h
- *(Se pueden cambiar desde la opción 5.)*




- eden cambiar desde la opción 5.)*

