/**
 * Abstract
 */
public abstract class Vehiculo {
	private String _placa;
	private String _marca;
	private String _modelo;
	private LocalDateTime _horaEntrada;
	public Parqueadero _unnamed_Parqueadero_10;

	public abstract double calcularCosto();
}