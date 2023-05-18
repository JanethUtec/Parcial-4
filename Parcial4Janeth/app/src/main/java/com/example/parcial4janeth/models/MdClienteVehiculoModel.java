package com.example.parcial4janeth.models;

public class MdClienteVehiculoModel {

    private int idCliente;
    private int idVehiculo;
    private String sMatricula;
    private String iKilometros;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getsMatricula() {
        return sMatricula;
    }

    public void setsMatricula(String sMatricula) {
        this.sMatricula = sMatricula;
    }

    public String getiKilometros() {
        return iKilometros;
    }

    public void setiKilometros(String iKilometros) {
        this.iKilometros = iKilometros;
    }
}
