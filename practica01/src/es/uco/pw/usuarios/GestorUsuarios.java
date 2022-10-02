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

public class GestorUsuarios {

    private static GestorUsuarios instance = null;
    private ArrayList<Usuario> usuarios;
    private String pathDatafile;

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

    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }

        return instance;
    }
    
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
    
    public boolean existeUsuario(String email) {	
        Usuario u = buscarUsuario(email);
        return (u != null);
    }
    
    public void altaUsuario(Usuario u) {
        if (existeUsuario(u.getEmail()) == false) {
            u.setId(this.usuarios.size());
            this.usuarios.add(u);
        }
    }

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

    public void listarUsuario(Usuario u) {
        System.out.println(u.toString());
    }

    public void listarUsuarios() {
        for (Usuario u : this.usuarios) {
            listarUsuario(u);
        }
    }
    
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
