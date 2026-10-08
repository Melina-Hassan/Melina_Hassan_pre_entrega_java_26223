package model;

public enum Talle {
    XS("Extra Small"),
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Doble Extra Large"),
    XXXL("Triple Extra Large");

    private final String descripcion;

    Talle(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }
}