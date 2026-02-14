package com.parqueadero.dominio;

import java.util.Map;

public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, int cilindraje) {
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() { return cilindraje; }

    @Override
    public double calcularCosto(long horas, Map<Class<? extends Vehiculo>, Double> tarifas) {
        return horas * tarifas.getOrDefault(Motocicleta.class, 0.0);
    }
}