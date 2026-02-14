package com.parqueadero.dominio;

import java.util.Map;

public class Automovil extends Vehiculo {
    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, String tipoCombustible) {
        super(placa, marca, modelo);
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCombustible() { return tipoCombustible; }

    @Override
    public double calcularCosto(long horas, Map<Class<? extends Vehiculo>, Double> tarifas) {
        return horas * tarifas.getOrDefault(Automovil.class, 0.0);
    }
}