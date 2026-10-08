package model;

public class ArticuloTextil extends Articulo{
    private Talle talle;
    private Material material;

    public ArticuloTextil(int codigo, String nombre, double precio, Categoria categoria, Talle talle, Material material) 
    {
        super(codigo, nombre, precio, categoria);
        this.talle = talle;
        this.material = material;
    }

    public Talle getTalle() 
    {
        return talle;
    }

    public void setTalle(Talle talle) 
    {
        this.talle = talle;
    }

    public Material getMaterial() 
    {
        return material;
    }

    public void setMaterial(Material material) 
    {
        this.material = material;
    }
     
    @Override
    public String getTipoArticulo() 
    {
        return "Textil";
    }

    @Override
    public String getDetalleEspecifico() 
    {
        return "Talle: " + talle.getDescripcion() + ", Material: " + material.getNombre();
    }

}
