package model;

public enum Material {
    ALGODON("Algodón"),
    POLIESTER("Poliéster"),
    LANA("Lana"),
    JEAN_DENIM("Denim / Jean"),
    SEDA("Seda"),
    LINO("Lino"),
    SINTETICO("Sintético / Mixto");

    private final String nombre;

    Material(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getNombre() 
    {
        return nombre;
    }
}
