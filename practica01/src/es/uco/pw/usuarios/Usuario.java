/**
 * Informacion de un usuario registrado en el sistema.
 * @author
 */
package es.uco.pw.usuarios;

import java.util.Date;
import java.text.SimpleDateFormat; 
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * Representa un usuario registrado en el sistema.
 */
public class Usuario {
	
	/**
	 * Numero de identificacion del usuario. 
	 * Debe ser unico para cada usuario.
	 */
    private int id;
    
    /**
     * Nombre del usuario.
     */
    private String nombre;
    
    /**
     * Apellidos del usuario.
     */
    private String apellidos;
    
    /**
     * Fecha de nacimiento del usuario.
     */
    private Date fechaNacimiento;
    
    /**
     * Fecha de inscripción del usuario en el sistema.
     */
    private Date fechaInscripcion;
    
    /**
     * Correo electronico del usuario. 
     * Debe ser unico para cada usuario.
     */
    private String email;

    /**
     * Crea un nuevo usuario con los valores por defecto.
     */
    public Usuario() {
        setId(0);
        setNombre("");
        setApellidos("");
        setFechaNacimiento(new Date());
        setFechaInscripcion(new Date());
        setEmail("");
    }

    /**
     * Crea un nuevo usuario dado sus 
     * @param id				el nuevo numero de identificacion del usuario.
     * 							Debe comprobarse previamente que sea unico.				
     * @param nombre			la cadena con el nuevo nombre del usuario.
     * @param apellidos			la cadena con los nuevos apellidos del usuario.
     * @param fechaNacimiento	la nueva fecha de nacimiento del usuario.
     * @param fechaInscripcion	la nueva fecha de inscripcion del usuario.
     * @param email				la cadena con el nuevo correo electronico del usuario.
     * 							Debe comprobarse previamente que sea unico.		
     */
    public Usuario(int id, String nombre, String apellidos, Date fechaNacimiento, Date fechaInscripcion, String email) {
        setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setFechaNacimiento(fechaNacimiento);
        setFechaInscripcion(fechaInscripcion);
        setEmail(email);
    }

    /**
     * Devuelve el identificador del usuario.
     * @return el numero de identificacion del usuario.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Devuelve el nombre del usuario.
     * @return la cadena con el nombre del usuario.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve los apellidos del usuario.
     * @return la cadena con los apellidos del usuario.
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     * @return la fecha de nacimiento del usuario.
     */
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Devuelve la fecha de inscripcion del usuario.
     * @return la fecha de inscripcion del usuario.
     */
    public Date getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    /**
     * Devuelve el correo electronico del usuario.
     * @return la cadena con el correo electronico del usuario.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Asigna el identificador del usuario.
     * @param id	el nuevo numero de identificacion del usuario.
     * 				Debe comprobarse previamente que sea unico.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Asigna el nombre del usuario.
     * @param nombre	la cadena con el nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Asigna los apellidos del usuario.
     * @param apellidos	la cadena con los nuevos apellidos del usuario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Asigna la fecha de nacimiento del usuario.
     * @param fechaNacimiento	la nueva fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Asigna la fecha de inscripcion del usuario.
     * @param fechaInscripcion	la nueva fecha de inscripcion del usuario.
     */
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Asigna el correo electronico del usuario.
     * @param email	la cadena con el nuevo correo electronico del usuario.
     * 				Debe comprobarse previamente que sea unico.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la edad del usuario.
     * @return	el numero de años del usuario.
     */
    public int calcularEdad() {
        Calendar fechaNacimiento = new GregorianCalendar();
        Calendar hoy = new GregorianCalendar();

        fechaNacimiento.setTime(getFechaNacimiento());
        hoy.setTime(new Date());

        int edad = hoy.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        return edad;
    }
    
    /**
     * Devuelve la antiguedad del usuario.
     * @return el numero de meses que el usuario lleva registrados en el sistema.
     */
    public int calcularAntiguedad() {
        Calendar fechaInscripcion = new GregorianCalendar();
        Calendar hoy = new GregorianCalendar();

        fechaInscripcion.setTime(getFechaInscripcion());
        hoy.setTime(new Date());

        int antiguedad = hoy.get(Calendar.MONTH) - fechaInscripcion.get(Calendar.MONTH);
        return antiguedad;
    }
    
    /**
     * Devuelve una cadena con la información del usuario.
     * @return la informacion del usuario.
     */
    @Override
    public String toString() {
        String str = "";
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        str += "\tNombre: " + getNombre() + System.lineSeparator();
        str += "\tApellidos: " + getApellidos() + System.lineSeparator();
        str += "\tFecha de nacimiento: " + df.format(getFechaNacimiento()) + System.lineSeparator();
        str += "\tEdad: " + calcularEdad() + System.lineSeparator();
        str += "\tFecha de inscripcion: " + df.format(getFechaInscripcion()) + System.lineSeparator();
        str += "\tAntigüedad: " + calcularAntiguedad() + " meses" + System.lineSeparator();
        str += "\tEmail: " + getEmail() + System.lineSeparator();
        return str;
    }
}
