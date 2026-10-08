package model;

public class ArticuloLimpieza extends Articulo{
    private double volumenLitros;

    public ArticuloLimpieza(int codigo, String nombre, double precio, Categoria categoria, double volumenLitros) 
    {
        super(codigo, nombre, precio, categoria);
        this.volumenLitros = volumenLitros;  
    }

    public double getVolumenLitros() 
    {
        return volumenLitros;
    }

    public void setVolumenLitros(double volumenLitros) 
    {
        this.volumenLitros = volumenLitros;
    }
     
    @Override
    public String getTipoArticulo() 
    {
        return "Limpieza";
    }

    @Override
    public String getDetalleEspecifico() 
    {
        return "Volumen en litros: " + volumenLitros;
    }

    @Override
    public String toString() 
    {
        return super.toString() + " [subtipo limpieza]";
    }
}
