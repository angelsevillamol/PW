package es.uco.pw.usuarios;

import java.util.ArrayList;

public class GestorUsuarios {

    private static GestorUsuarios instance = null;
    private ArrayList<Usuario> usuarios;

    private GestorUsuarios() {
    	usuarios = new ArrayList<Usuario>();
    }

    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }

        return instance;
    }
    
    public Usuario buscarUsuario(String email) {	
    	Usuario usuario = null;
    	
	for (Usuario u : usuarios) {  
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
 	    u.setId(usuarios.size());
    	    usuarios.add(u);
    	}
    }

    public void modificarUsuario(Usuario u) {
    	for (Usuario uiter : usuarios) {
    	    if (uiter.getId() == u.getId()) {
    		u.setNombre(uiter.getNombre());
    	        u.setApellidos(uiter.getApellidos());
    	        u.setFechaNacimiento(uiter.getFechaNacimiento());
    	        u.setEmail(uiter.getEmail());
    	    }
    	}
    }

    public void listarUsuario(Usuario u) {
        System.out.println(u.toString());
    }

    public void listarUsuarios() {
        for (Usuario u : usuarios) {
            listarUsuario(u);
        }
    }
}
