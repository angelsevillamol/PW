/**
 * Gestor de usuarios registrados en el sistema.
 */
package es.uco.pw.usuarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

/**
 * Gestor de usuarios del sistema implementando el patron singleton.
 */
public class GestorUsuarios {

	/**
	 * Instancia del gestor de usuarios.
	 */
    private static GestorUsuarios instance = null;
    
    /**
     * Lista con los usuarios registrados.
     */
    private ArrayList<Usuario> usuarios;
    
    /**
     * Ruta relativa del fichero de datos.
     */
    private String pathDatafile;

    /**
     * Constructor privado.
     */
    private GestorUsuarios() {
        this.usuarios = new ArrayList<Usuario>();
        String propFileName = "resources/config.properties";
        
        try (InputStream input = new FileInputStream(propFileName)) {
            Properties prop = new Properties();
            prop.load(input);
            this.pathDatafile = prop.getProperty("datos.usuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Devuelve el objeto singleton del gestor de usuarios.
     * @return la instancia del gestor de usuarios.
     */
    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }

        return instance;
    }
    
    /**
     * Busca un usuario registrado a partir de su email.
     * @param email	el correo electronico del usuario a buscar.
     * @return 	el usuario encontrado. 
     * 			Si no existe un usuario con dicho correo, entonces devuelve null.
     */
    public Usuario buscarUsuario(String email) {	
        Usuario usuario = null;
        
        for (Usuario u : this.usuarios) {  
            if (u.getEmail().equals(email))  {
                usuario = u;
                return usuario;
            }
        }
        return usuario;
    }
    
    /**
     * Comprueba si hay un usuario registrado con un determinado correo electronico.
     * @param email	el correo electronico del usuario a comprobar.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeUsuario(String email) {	
        Usuario u = buscarUsuario(email);
        return (u != null);
    }
    
    /**
     * Da de alta a un usuario en el sistema.
     * Solo se da de alta si el correo electronico no esta en uso.
     * Se recomienda invocar previamente al metodo existeUsuario.
     * @param usuario	el usuario a dar de alta en el sistema.
     */
    public void altaUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getEmail()) == false) {
            usuario.setId(this.usuarios.size());
            this.usuarios.add(usuario);
        }
    }

    /**
     * Modifica la informacion personal de un usuario. 
     * La nueva informacion se asignara a aquel que tenga el mismo identificador.
     * @param usuario contiene la nueva informacion del usuario. 
     */
    public void modificarUsuario(Usuario usuario) {
        for (Usuario u : this.usuarios) {
            if (u.getId() == usuario.getId()) {
                u.setNombre(usuario.getNombre());
                u.setApellidos(usuario.getApellidos());
                u.setFechaNacimiento(usuario.getFechaNacimiento());
                u.setEmail(usuario.getEmail());
            }
        }
    }

    /**
     * Imprime por pantalla la informacion personal de un usuario dado.
     * @param usuario contiene la informacion personal del usuario a imprimir.
     */
    public void listarUsuario(Usuario usuario) {
        System.out.println(usuario.toString());
    }

    /**
     * Imprime por pantalla la informacion personal de todos los usuarios.
     */
    public void listarUsuarios() {
        for (Usuario u : this.usuarios) {
            listarUsuario(u);
        }
    }
    
    /**
     * Carga la informacion de todos los usuarios registrados en el sistema de la base de datos.
     */
    public void cargarUsuarios() {
        Scanner read = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            read = new Scanner(new File(this.pathDatafile));
            read.useDelimiter(";");

            while (read.hasNext()) {
                int id = Integer.parseInt(read.next());
                String nombre = read.next();
                String apellidos = read.next();
                Date fechaNacimiento = df.parse(read.next());
                Date fechaInscripcion = df.parse(read.next());
                String email = read.next();
                
                Usuario u = new Usuario(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email);
                this.usuarios.add(u);
                read.nextLine();
            }
            
            read.close();	
        } catch (Exception e) {
            System.out.print("Error cargando los datos de usuarios. ");
            System.out.println("Mensaje de la excepcion: " + e.getMessage());
        }
    }
    
    /**
     * Guarda la informacion de todos los usuarios registrados en el sistema en la base de datos.
     */
    public void guardarUsuarios() {
        FileWriter f = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            f = new FileWriter(this.pathDatafile);
            
            Iterator<Usuario> it = this.usuarios.iterator();
            while (it.hasNext()) {
                Usuario u = it.next();
                f.write(u.getId() + ";");
                f.write(u.getNombre() + ";");
                f.write(u.getApellidos() + ";");
                f.write(df.format(u.getFechaNacimiento()) + ";");
                f.write(df.format(u.getFechaInscripcion()) + ";");
                f.write(u.getEmail() + ";");
                f.write("\n");
            }
            
            f.close();	
        } catch (Exception e) {
            System.out.print("Error guardando los datos de usuarios. ");
            System.out.println("Mensaje de la excepcion: " + e.getMessage());
        }
    }
}
