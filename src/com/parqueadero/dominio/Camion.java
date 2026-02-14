package com.parqueadero.dominio;

import java.util.Map;

public class Camion extends Vehiculo {
    private double capacidadCarga; // en toneladas

    public Camion(String placa, String marca, String modelo, double capacidadCarga) {
        super(placa, marca, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() { return capacidadCarga; }

    @Override
    public double calcularCosto(long horas, Map<Class<? extends Vehiculo>, Double> tarifas) {
        return horas * tarifas.getOrDefault(Camion.class, 0.0);
    }
}