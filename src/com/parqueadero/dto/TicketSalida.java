package com.parqueadero.dto;

import java.time.LocalDateTime;

public class TicketSalida {
    private final String placa;
    private final String tipoVehiculo;
    private final LocalDateTime horaEntrada;
    private final LocalDateTime horaSalida;
    private final long horasCobradas;
    private final double tarifaHora;
    private final double total;

    public TicketSalida(String placa, String tipoVehiculo,
                        LocalDateTime horaEntrada, LocalDateTime horaSalida,
                        long horasCobradas, double tarifaHora, double total) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.horasCobradas = horasCobradas;
        this.tarifaHora = tarifaHora;
        this.total = total;
    }

    public String getPlaca() { return placa; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSalida() { return horaSalida; }
    public long getHorasCobradas() { return horasCobradas; }
    public double getTarifaHora() { return tarifaHora; }
    public double getTotal() { return total; }
}
