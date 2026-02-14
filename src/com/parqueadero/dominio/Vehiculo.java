package com.parqueadero.dominio;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private LocalDateTime horaEntrada;

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa.toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = LocalDateTime.now();
    }

    /** Polimórfico: cada subclase calcula su costo según su tipo. */
    public abstract double calcularCosto(long horas, Map<Class<? extends Vehiculo>, Double> tarifas);

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }
}