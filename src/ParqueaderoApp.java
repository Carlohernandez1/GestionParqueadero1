package com.parqueadero.app;

import com.parqueadero.dominio.*;
import com.parqueadero.negocio.Parqueadero;
import com.parqueadero.dto.TicketSalida;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParqueaderoApp {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        ejecutarMenu(parqueadero);
    }

    /* =========================== MENÚ =========================== */

    private static void ejecutarMenu(Parqueadero parqueadero) {
        while (true) {
            System.out.println("\n===== PARQUEADERO - MENÚ =====");
            System.out.println("1. Ingresar un vehículo");
            System.out.println("2. Registrar salida de un vehículo");
            System.out.println("3. Consultar estado del parqueadero");
            System.out.println("4. Ver tarifas actuales");
            System.out.println("5. Configurar tarifas");
            System.out.println("6. Ver total recaudado");
            System.out.println("0. Salir");
            int opcion = leerEntero("Selecciona una opción (número entero): ");

            switch (opcion) {
                case 1 -> ingresarVehiculo(parqueadero);
                case 2 -> registrarSalida(parqueadero);
                case 3 -> consultarEstado(parqueadero);
                case 4 -> verTarifas(parqueadero);
                case 5 -> configurarTarifas(parqueadero);
                case 6 -> System.out.printf("Total recaudado: $%,.0f%n", parqueadero.calcularTotalRecaudado());
                case 0 -> {
                    System.out.println("¡Hasta pronto!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    /* ===================== OPCIONES DEL MENÚ ===================== */

    private static void ingresarVehiculo(Parqueadero parqueadero) {
        System.out.println("\n--- Ingreso de Vehículo ---");
        System.out.println("Tipo de vehículo:");
        System.out.println("1. Automóvil");
        System.out.println("2. Motocicleta");
        System.out.println("3. Camión");
        int tipo = leerEntero("Elige (número entero 1-3): ");

        String placa = leerTextoNoVacio("Placa (texto, ej: ABC123): ").toUpperCase();
        String marca = leerTextoNoVacio("Marca (texto): ");
        String modelo = leerTextoNoVacio("Modelo (texto): ");

        Vehiculo v;
        switch (tipo) {
            case 1 -> {
                String combustible = leerTextoNoVacio("Tipo de combustible (texto: gasolina/diésel/eléctrico): ");
                v = new Automovil(placa, marca, modelo, combustible);
            }
            case 2 -> {
                int cilindraje = leerEnteroPositivo("Cilindraje (número entero en cc, ej: 150): ");
                v = new Motocicleta(placa, marca, modelo, cilindraje);
            }
            case 3 -> {
                double capacidad = leerDoubleNoNegativo("Capacidad de carga (número con decimales en toneladas, ej: 3.5): ");
                v = new Camion(placa, marca, modelo, capacidad);
            }
            default -> {
                System.out.println("Tipo no válido. Operación cancelada.");
                return;
            }
        }

        boolean ok = parqueadero.registrarEntrada(v);
        if (ok) {
            System.out.println("✅ Entrada registrada correctamente.");
            System.out.println("Hora de entrada: " + v.getHoraEntrada().format(DTF));
        } else {
            System.out.println("⚠️ Ya existe un vehículo con esa placa dentro del parqueadero.");
        }
    }

    private static void registrarSalida(Parqueadero parqueadero) {
        System.out.println("\n--- Salida de Vehículo ---");
        String placa = leerTextoNoVacio("Placa (texto): ").toUpperCase();

        TicketSalida t = parqueadero.registrarSalida(placa);
        if (t == null) {
            System.out.println("⚠️ No se encontró un vehículo con esa placa en el parqueadero.");
            return;
        }

        System.out.println("\n***** TICKET DE SALIDA *****");
        System.out.println("Placa: " + t.getPlaca());
        System.out.println("Tipo: " + t.getTipoVehiculo());
        System.out.println("Entrada: " + t.getHoraEntrada().format(DTF));
        System.out.println("Salida: " + t.getHoraSalida().format(DTF));
        System.out.println("Horas cobradas: " + t.getHorasCobradas() + " (fracciones se redondean hacia arriba)");
        System.out.printf("Tarifa por hora: $%,.0f%n", t.getTarifaHora());
        System.out.printf("TOTAL A PAGAR: $%,.0f%n", t.getTotal());
        System.out.println("****************************");
    }

    private static void consultarEstado(Parqueadero parqueadero) {
        System.out.println("\n--- Vehículos actualmente en el parqueadero ---");
        List<Vehiculo> lista = new ArrayList<>(parqueadero.consultarEstado());
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos en el parqueadero.");
            return;
        }
        lista.sort(Comparator.comparing(Vehiculo::getHoraEntrada));

        for (Vehiculo v : lista) {
            System.out.printf(
                    "%-10s | %-10s | %-10s | %-12s | Entrada: %s%n",
                    v.getPlaca(), v.getMarca(), v.getModelo(),
                    v.getClass().getSimpleName(),
                    v.getHoraEntrada().format(DTF)
            );
        }
        System.out.println("Total vehículos: " + lista.size());
    }

    private static void verTarifas(Parqueadero parqueadero) {
        System.out.println("\n--- Tarifas actuales por hora ---");
        System.out.printf("Automóvil  : $%,.0f%n", parqueadero.getTarifa(Automovil.class));
        System.out.printf("Motocicleta: $%,.0f%n", parqueadero.getTarifa(Motocicleta.class));
        System.out.printf("Camión     : $%,.0f%n", parqueadero.getTarifa(Camion.class));
    }

    private static void configurarTarifas(Parqueadero parqueadero) {
        System.out.println("\n--- Configurar tarifas (por hora) ---");
        double a = leerDoubleNoNegativo("Automóvil ($, número con decimales): ");
        double m = leerDoubleNoNegativo("Motocicleta ($, número con decimales): ");
        double c = leerDoubleNoNegativo("Camión ($, número con decimales): ");

        parqueadero.setTarifa(Automovil.class, a);
        parqueadero.setTarifa(Motocicleta.class, m);
        parqueadero.setTarifa(Camion.class, c);

        System.out.println("✅ Tarifas actualizadas correctamente.");
    }

    /* ================== LECTURA/VALIDACIÓN DE ENTRADAS ================== */

    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = SC.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Entrada vacía. Por favor ingresa un texto válido.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = SC.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Debe ser un número entero. Intenta de nuevo.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int v = leerEntero(mensaje);
            if (v > 0) return v;
            System.out.println("Debe ser un número entero positivo (> 0).");
        }
    }

    private static double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = SC.nextLine().trim();
            try {
                double d = Double.parseDouble(s.replace(",", "."));
                if (d >= 0) return d;
                System.out.println("Debe ser un número mayor o igual a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Debe ser un número (usa punto o coma para decimales).");
            }
        }
    }
}
