package com.parqueadero.negocio;

import com.parqueadero.dominio.*;
import com.parqueadero.dto.TicketSalida;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Parqueadero {

    // Vehículos actualmente dentro (clave = placa)
    private final Map<String, Vehiculo> vehiculosDentro = new HashMap<>();

    // Tarifas por hora, por tipo de vehículo
    private final Map<Class<? extends Vehiculo>, Double> tarifas = new HashMap<>();

    // Acumulado histórico
    private double totalRecaudado = 0.0;

    public Parqueadero() {
        // Tarifas por defecto (puedes cambiarlas desde el menú)
        tarifas.put(Automovil.class, 3000.0);
        tarifas.put(Motocicleta.class, 1500.0);
        tarifas.put(Camion.class, 7000.0);
    }

    public boolean registrarEntrada(Vehiculo v) {
        String placa = v.getPlaca();
        if (vehiculosDentro.containsKey(placa)) return false;
        v.setHoraEntrada(LocalDateTime.now());
        vehiculosDentro.put(placa, v);
        return true;
    }

    public TicketSalida registrarSalida(String placa) {
        Vehiculo v = vehiculosDentro.remove(placa.toUpperCase());
        if (v == null) return null;

        LocalDateTime entrada = v.getHoraEntrada();
        LocalDateTime salida = LocalDateTime.now();

        long minutos = Duration.between(entrada, salida).toMinutes();
        long horasCobradas = Math.max(1, (minutos + 59) / 60); // redondeo hacia arriba

        double tarifaHora = tarifas.getOrDefault(v.getClass(), 0.0);
        double total = v.calcularCosto(horasCobradas, tarifas);
        totalRecaudado += total;

        return new TicketSalida(
                v.getPlaca(),
                v.getClass().getSimpleName(),
                entrada,
                salida,
                horasCobradas,
                tarifaHora,
                total
        );
    }

    public Collection<Vehiculo> consultarEstado() {
        return vehiculosDentro.values();
    }

    public double calcularTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTarifa(Class<? extends Vehiculo> tipo, double valor) {
        tarifas.put(tipo, Math.max(0.0, valor));
    }

    public double getTarifa(Class<? extends Vehiculo> tipo) {
        return tarifas.getOrDefault(tipo, 0.0);
    }
}