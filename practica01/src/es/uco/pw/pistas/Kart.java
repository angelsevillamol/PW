package es.uco.pw.pistas;

public class Kart {
	private int Id;
	private boolean Tipo;
	private String Estado;
	
	public Kart() {
		
	}
	
	public Kart(int id, boolean tipo, String estado) {
		this.Id= id;
		this.Tipo= tipo;
		this.Estado= estado;
	}
	
	public void setId (int Id) {
		this.Id= Id;
	}
	
	public int getId () {
		return Id;
	}
	
	public void setTipo (boolean Tipo) {
		this.Tipo= Tipo;
	}
	
	public boolean getTipo () {
		return Tipo;
		
	}
	
	public void setEstado (String Estado) {
		this.Estado= Estado;
	}
	
	public String getEstado () {
		return Estado;
	}
	
}
