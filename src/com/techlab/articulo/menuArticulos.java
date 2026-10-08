import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Articulo;
import model.ArticuloAlimenticio;
import model.ArticuloElectronico;
import model.ArticuloTextil;
import model.ArticuloLimpieza;
import model.Categoria;
import model.Material;
import model.Talle;

public class menuArticulos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        
        precargarCategorias(categorias);

        int opcion;

        do {
            System.out.println("\n----- Menú de Artículos -----");
            System.out.println("1. Ingresar artículo");
            System.out.println("2. Consultar artículo");
            System.out.println("3. Listar artículos");
            System.out.println("4. Modificar un artículo");
            System.out.println("5. Borrar un artículo");
            System.out.println("6. Listar categorías");
            System.out.println("0. Salir");
            
            opcion = leerEntero(scanner, "Ingrese una opción: ");
            
            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    consultarArticulo(scanner, articulos);
                    break;
                case 3:
                    listarArticulos(articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Vuelva pronto!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    public static void precargarCategorias(ArrayList<Categoria> categorias) 
    {
        categorias.add(new Categoria(1, "Electrónico", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Textil", "Vestimenta y calzado"));
        categorias.add(new Categoria(3, "Alimenticio", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    public static void ingresarArticulo(Scanner scanner,ArrayList<Articulo> articulos,ArrayList<Categoria> categorias) 
    {
        System.out.println("\n--- INGRESAR ARTÍCULO ---");
        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo textil");
        System.out.println("3 - Artículo alimenticio");
        System.out.println("4 - Artículo de limpieza");

        int tipo;
        do {
            tipo = leerEntero(scanner, "Seleccionar el tipo de artículo: ");

            if (tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4) {
                System.out.println("Error: opción inválida. Debe ser 1, 2, 3 o 4.");
            }

        } while (tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4);

        int codigo = leerEntero(scanner, "Ingrese el código del artículo: ");

        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Error: ya existe un artículo con ese código.");
            return;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

        listarCategorias(categorias);
        Categoria categoria = pedirCategoriaExistente(scanner, categorias);

        Articulo articulo = null;

        switch (tipo) {
            case 1:
                int garantiaMeses = leerEnteroNoNegativo(scanner, "Ingrese la garantía en meses: ");

                articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
                break;
            case 2:
                Talle talle = elegirTalle(scanner);
                Material material = elegirMaterial(scanner);

                articulo = new ArticuloTextil(codigo, nombre, precio, categoria, talle, material);
                break;
            case 3:
                LocalDate fechaCaducidad = leerFecha(scanner, "Ingrese la fecha de vencimiento (aaaa-mm-dd): ");

                articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, fechaCaducidad);
                break;
            case 4:
                int volumenLitros = leerEnteroNoNegativo(scanner, "Ingrese el volumen en litros: ");

                articulo = new ArticuloLimpieza(codigo, nombre, precio, categoria, volumenLitros);
                break;
        }
            if(articulo != null) {
            articulos.add(articulo);

            System.out.println("Artículo ingresado correctamente.");
            System.out.println("Resumen del objeto creado:");
            System.out.println(articulo);
        }
        
    }

    private static LocalDate leerFecha(Scanner scanner, String mensaje) 
    {     
        while (true) {
        System.out.print(mensaje);
        String input = scanner.nextLine().trim();

        if (util.ValidadorFecha.esFechaValida(input)) {
            return LocalDate.parse(input);
        }
        System.out.println("Error: fecha inválida o inexistente (use formato aaaa-mm-dd, ej: 2026-10-25).");
        }
    }
    
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        for (Articulo articulo : articulos) {
            System.out.println(articulo);
            System.out.println("--------------------------------------------"); 
        }
    }

    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a consultar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("Artículo encontrado:");
        System.out.println(articulo);
    }

    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias) 
    {
        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del artículo: ");

        listarCategorias(categorias);
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;

            int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese la nueva garantía en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        }

        if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;

            LocalDate nuevaFechaCaducidad = leerFecha(scanner, "Ingrese la nueva fecha de vencimiento: ");
            alimenticio.setFechaCaducidad(nuevaFechaCaducidad);
        }

         if (articulo instanceof ArticuloLimpieza) {
            ArticuloLimpieza limpieza = (ArticuloLimpieza) articulo;

            int nuevoVolumen = leerEnteroNoNegativo(scanner, "Ingrese el nuevo volumen en litros: ");
            limpieza.setVolumenLitros(nuevoVolumen);
        }

         if (articulo instanceof ArticuloTextil) {
            ArticuloTextil textil = (ArticuloTextil) articulo;
          
            Talle nuevoTalle = elegirTalle(scanner);
            Material nuevoMaterial = elegirMaterial(scanner);
            textil.setTalle(nuevoTalle);
            textil.setMaterial(nuevoMaterial);
        }

        System.out.println("Artículo modificado correctamente.");
    }

    public static Talle elegirTalle(Scanner scanner) {
        Talle[] talles = Talle.values();
        while (true) {
            System.out.println("\nSeleccione el talle:");
            for (int i = 0; i < talles.length; i++) {
                System.out.println((i + 1) + " - " + talles[i].name() + " (" + talles[i].getDescripcion() + ")");
            }
            int opcion = leerEntero(scanner, "Opción: ");
            if (opcion >= 1 && opcion <= talles.length) {
                return talles[opcion - 1];
            }
            System.out.println("Opción inválida.");
        }
    }

    public static Material elegirMaterial(Scanner scanner) {
        Material[] materiales = Material.values();
        while (true) {
            System.out.println("\nSeleccione el material:");
            for (int i = 0; i < materiales.length; i++) {
                System.out.println((i + 1) + " - " + materiales[i].getNombre());
            }
            int opcion = leerEntero(scanner, "Opción: ");
            if (opcion >= 1 && opcion <= materiales.length) {
                return materiales[opcion - 1];
            }
            System.out.println("Opción inválida.");
        }
    }

    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) 
    {
        System.out.println("\n--- ELIMINAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");

        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("\nArtículo seleccionado: " + articulo);
        System.out.print("¿Está seguro de que desea eliminarlo? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            articulos.remove(articulo);
            System.out.println("Artículo eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada. El artículo no fue eliminado.");
        }
    }

    public static void listarCategorias(ArrayList<Categoria> categorias) 
    {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) 
    {
        while (true) {
            int codigoCategoria = leerEntero(scanner, "Ingrese el código de la categoría: ");

            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println("Error: la categoría no existe.");
        }
    }

    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) 
    {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) 
    {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    public static int leerEntero(Scanner scanner, String mensaje) 
    {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) 
    {
        while (true) {
            int valor = leerEntero(scanner, mensaje);

            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo.");
                continue;
            }

            return valor;
        }
    }
    
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) 
    {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.println("Error: el texto no puede estar vacío.");
        }
    }
}
