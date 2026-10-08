package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArticuloAlimenticio extends Articulo {

    private LocalDate fechaCaducidad;

    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, LocalDate fechaCaducidad) 
    {
        super(codigo, nombre, precio, categoria);
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCaducidad() 
    {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) 
    {
        this.fechaCaducidad = fechaCaducidad;
    }
     
    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() 
    {
        DateTimeFormatter fechaCaducidadFormatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu");
        return "Fecha de vencimiento: " + fechaCaducidad.format(fechaCaducidadFormatter);
    }

}
