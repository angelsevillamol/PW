/**
 * 
 */
package es.uco.pw.programas;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import es.uco.pw.usuarios.*;

enum OpcionesMenu {
    SALIR,
    ALTA,
    MODIFICAR,
    LISTAR,
    INCORRECTA
}

enum OpcionesMenuModificar {
    SALIR,
    NOMBRE,
    APELLIDOS,
    FECHANACIMIENTO,
    EMAIL,
    INCORRECTA
}

/**
 * Programa que permite gestionar los usuarios de la aplicacion.
 */
public class ProgramaPrincipal {
    
    private static GestorUsuarios gestorUsuarios;
    private static Scanner sc;
    
    /**
     * Espera a que el usuario introduzca ENTER para continuar.
     */
    private static void pulseEnter() {
        System.out.print("Pulse ENTER para continuar.");
        sc.nextLine();
    }
    
    /**
     * Imprime las opciones del menu principal por pantalla y solicita al usuario una opcion.
     * @return la opcion seleccionada por el usuario.
     */
    private static OpcionesMenu imprimirMenu() {
        OpcionesMenu opt;
        
        System.out.println("MENU:");
        System.out.println("\t1. Dar de alta a un usuario.");
        System.out.println("\t2. Modificar la informacion de un usuario.");
        System.out.println("\t3. Listar a los usuarios actualmente registrados.");
        System.out.println("\t0. Salir.");
        
        try {
            System.out.print("Seleccione una opcion: ");
            int intOpt = Integer.parseInt(sc.nextLine());
            if (intOpt < 0 || intOpt > 3) {
                opt = OpcionesMenu.INCORRECTA;
            }
            else {
                opt = OpcionesMenu.values()[intOpt];
            }
        } catch (NumberFormatException nfe) {
            opt = OpcionesMenu.INCORRECTA;	
        }
        
        return opt;
    }
    
    /**
     * Imprime las opciones de modificacion de usuarios por pantalla y solicita al usuario una opcion.
     * @return la opcion seleccionada por el usuario.
     */
    private static OpcionesMenuModificar imprimirMenuModificar() {
        OpcionesMenuModificar opt;
        
        System.out.println("\t1. Modificar nombre.");
        System.out.println("\t2. Modificar apellidos.");
        System.out.println("\t3. Modificar fecha de nacimiento.");
        System.out.println("\t4. Modificar email.");
        System.out.println("\t0. Salir.");
        
        try {
            System.out.print("Seleccione una opcion: ");
            int intOpt = Integer.parseInt(sc.nextLine());
            if (intOpt < 0 || intOpt > 5) {
                opt = OpcionesMenuModificar.INCORRECTA;
            }
            else {
                opt = OpcionesMenuModificar.values()[intOpt];
            }
        } catch (NumberFormatException nfe) {
            opt = OpcionesMenuModificar.INCORRECTA;	
        }
        
        return opt;
    }
    
    /**
     * Modifica la informacion personal de un usuario registrado a partir 
     * de los valores introducidos por el usuario del metodo.
     */
    private static void modificarUsuario() { 
        System.out.println("MODIFICAR USUARIO");
        
        // Solicita el correo electronico del usuario.
        System.out.print("Introduzca el correo del usuario a modificar: ");
         String str = sc.nextLine();
         if (str == null || str.isEmpty()) {
             System.out.print("El correo electronico es un campo obligatorio. ");
             return;
         }
             
         // Comprueba si existe dicho usuario.
         Usuario u = gestorUsuarios.buscarUsuario(str);
         if (u == null) {
             System.out.print("Usuario no encontrado. ");
             return;
         }

        OpcionesMenuModificar opt;
        
        do {
            gestorUsuarios.listarUsuario(u);
            opt = imprimirMenuModificar();
        
            switch (opt) {
                case SALIR:
                    gestorUsuarios.modificarUsuario(u);
                    System.out.print("Usuario modificado correctamente. ");
                    break;
                case NOMBRE:
                    System.out.print("\tNombre: ");
                    str = sc.nextLine();
                    if (str == null || str.isEmpty()) {
                        System.out.print("El nombre es un campo obligatorio. ");
                    }
                    else {
                        u.setNombre(str);
                    }
                    pulseEnter();
                    break;
                case APELLIDOS:
                    System.out.print("\tApellidos: ");
                    str = sc.nextLine();
                    if (str == null || str.isEmpty()) {
                        System.out.print("Los apellidos son un campo obligatorio. ");
                    }
                    else {
                        u.setApellidos(str);
                    }
                    pulseEnter();
                    break;
                case FECHANACIMIENTO:
                    System.out.print("\tFecha de nacimiento (dd/MM/yyyy): ");
                    str = sc.nextLine();
                    Date fechaNacimiento = new Date();
                    Date today = new Date();
                    try {
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        fechaNacimiento = df.parse(str);
                        if (fechaNacimiento.compareTo(today) > 0) {
                            System.out.print("Fecha de nacimiento incorrecta. ");
                        }
                        else {
                            u.setFechaNacimiento(fechaNacimiento);
                        }
                    } catch (ParseException e) {
                        System.out.print("La fecha de nacimiento es un campo obligatorio y debe introducirse en el formato dado. ");
                    }
                    pulseEnter();
                    break;
                case EMAIL:
                    System.out.print("\tEmail: ");
                    str = sc.nextLine();
                    if (str == null || str.isEmpty()) {
                        System.out.print("El correo electronico es un campo obligatorio. ");
                    }
                    else if (gestorUsuarios.existeUsuario(str)) {
                        System.out.print("Ya existe un usuario con esa direccion de correo electronico. ");
                    }
                    else {
                        u.setEmail(str);
                    }
                    pulseEnter();
                    break;
                default:
                    System.out.print("Opcion incorrecta. ");
                    pulseEnter();
            }
        
        } while (opt != OpcionesMenuModificar.SALIR);    
    }
    
    /**
     * Da de alta a un usuario a partir con la informacion personal introducida 
     * por teclado por el usuario del metodo.
     */
    private static void altaUsuario() {
        System.out.println("ALTA DE UN USUARIO");
        System.out.println("Introduzca sus datos: ");
        
        // Solicita el correo electronico del usuario.
        System.out.print("\tEmail: ");
        String email = sc.nextLine();
        if (email == null || email.isEmpty()) {
            System.out.print("El correo electronico es un campo obligatorio. ");
            return;
        }
        
        // Comprueba si existe dicho usuario.
        if (gestorUsuarios.existeUsuario(email)) {
            System.out.print("Ya existe un usuario con esa direccion de correo electronico. ");
            return;
        }
        
        System.out.print("\tNombre: ");
        String nombre = sc.nextLine();
        if (nombre == null || nombre.isEmpty()) {
            System.out.print("El nombre es un campo obligatorio. ");
            return;
        }
        
        System.out.print("\tApellidos: ");
        String apellidos = sc.nextLine();
        if (apellidos == null || apellidos.isEmpty()) {
            System.out.print("Los apellidos son un campo obligatorio. ");
            return;
        }
        
        System.out.print("\tFecha de nacimiento (dd/MM/yyyy): ");
        String str = sc.nextLine();
        Date fechaNacimiento = new Date();
        Date today = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            fechaNacimiento = df.parse(str);
            if (fechaNacimiento.compareTo(today) > 0) {
                System.out.print("Fecha de nacimiento incorrecta. ");
                return;
            }
        } catch (ParseException e) {
            System.out.print("La fecha de nacimiento es un campo obligatorio y debe introducirse en el formato dado. ");
            return;
        }
        
        Usuario u = new Usuario(0, nombre, apellidos, fechaNacimiento, today, email);
        gestorUsuarios.altaUsuario(u);
        System.out.print("Usuario dado de alta con exito. ");
    }
    
    public static void main(String[] args) {
    	gestorUsuarios = GestorUsuarios.getInstance();
        sc = new Scanner(System.in);
        OpcionesMenu opt;
        
        System.out.println("Bienvenido a la pagina de Karts.");
        gestorUsuarios.cargarUsuarios();
    
        do {
            opt = imprimirMenu();
        
            switch (opt) {
                case SALIR:
                    gestorUsuarios.guardarUsuarios();
                    break;
                case ALTA:
                    altaUsuario();
                    pulseEnter();
                    break;
                case MODIFICAR:
                    modificarUsuario();
                    pulseEnter();
                    break;
                case LISTAR:
                    gestorUsuarios.listarUsuarios();
                    pulseEnter();
                    break;
                default:
                    System.out.print("Opcion incorrecta. ");
                    pulseEnter();
            }
        
        } while (opt != OpcionesMenu.SALIR);
    }
}
