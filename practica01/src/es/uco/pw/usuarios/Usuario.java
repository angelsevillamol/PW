package es.uco.pw.usuarios;
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Usuario {

	int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Date fechaInscripcion;
    private String email;

    public Usuario() {
    	setId(0);
        setNombre("");
        setApellidos("");
        setFechaNacimiento(new Date());
        setFechaInscripcion(new Date());
        setEmail("");
    }

    public Usuario(int id, String nombre, String apellidos, Date fechaNacimiento, String email) {
    	setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setFechaNacimiento(fechaNacimiento);
        setFechaInscripcion(new Date());
        setEmail(email);
    }

    public int getId() {
    	return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public String getEmail() {
        return email;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int calcularAntiguedad() {
        Calendar fechaInscripcion = new GregorianCalendar();
        Calendar hoy = new GregorianCalendar();

        fechaInscripcion.setTime(getFechaInscripcion());
        hoy.setTime(new Date());

        int antiguedad = hoy.get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
        return antiguedad;
    }

    public int calcularEdad() {
        Calendar fechaNacimiento = new GregorianCalendar();
        Calendar hoy = new GregorianCalendar();

        fechaNacimiento.setTime(getFechaNacimiento());
        hoy.setTime(new Date());

        int edad = hoy.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        return edad;
    }
    
    public String toString() {
        String str = "";
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        str += "\tNombre: " + getNombre() + System.lineSeparator();
        str += "\tApellidos: " + getApellidos() + System.lineSeparator();
        str += "\tFecha de nacimiento: " + df.format(getFechaNacimiento()) + System.lineSeparator();
        str += "\tFecha de inscripcion: " + df.format(getFechaInscripcion()) + System.lineSeparator();
        str += "\tEmail: " + getEmail() + System.lineSeparator();
        return str;
    }
}
