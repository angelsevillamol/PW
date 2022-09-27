package PW;

public class kart {
	private int Id;
	private boolean Tipo;
	private String Estado;
	
	public kart() {
		
	}
	
	public kart(int id, boolean tipo, String estado) {
		this.Id= Id;
		this.Tipo= Tipo;
		this.Estado= Estado;
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
