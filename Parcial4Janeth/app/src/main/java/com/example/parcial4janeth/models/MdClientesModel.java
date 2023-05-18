package com.example.parcial4janeth.models;

public class MdClientesModel {

    private int idCliente;
    private String sNombreCliente;
    private String sApellidosCliente;
    private String sDireccionCliente;
    private String sCuidadCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getsNombreCliente() {
        return sNombreCliente;
    }

    public void setsNombreCliente(String sNombreCliente) {
        this.sNombreCliente = sNombreCliente;
    }

    public String getsApellidosCliente() {
        return sApellidosCliente;
    }

    public void setsApellidosCliente(String sApellidosCliente) {
        this.sApellidosCliente = sApellidosCliente;
    }

    public String getsDireccionCliente() {
        return sDireccionCliente;
    }

    public void setsDireccionCliente(String sDireccionCliente) {
        this.sDireccionCliente = sDireccionCliente;
    }

    public String getsCuidadCliente() {
        return sCuidadCliente;
    }

    public void setsCuidadCliente(String sCuidadCliente) {
        this.sCuidadCliente = sCuidadCliente;
    }
}
