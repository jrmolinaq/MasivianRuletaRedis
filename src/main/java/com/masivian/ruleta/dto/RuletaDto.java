package com.masivian.ruleta.dto;

public class RuletaDto {

    private Integer id;

    private String estado;

    public RuletaDto(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    protected RuletaDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
