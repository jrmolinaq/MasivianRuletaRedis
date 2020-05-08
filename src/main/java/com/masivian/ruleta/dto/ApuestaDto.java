package com.masivian.ruleta.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ApuestaDto {

    private Integer id;

    @Min(0)
    @Max(36)
    private Integer numero;

    private String color;

    @NotNull
    @Min(0)
    @Max(10000)
    private BigDecimal dinero;

    private String idUsuario;

    private Integer idRuleta;

    public ApuestaDto(Integer id, @Min(0) @Max(36) Integer numero, String color, @NotNull @Min(0) @Max(10000) BigDecimal dinero,
                      @NotNull String idUsuario, Integer idRuleta) {
        this.id = id;
        this.numero = numero;
        this.color = color;
        this.dinero = dinero;
        this.idUsuario = idUsuario;
        this.idRuleta = idRuleta;
    }

    protected ApuestaDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getDinero() {
        return dinero;
    }

    public void setDinero(BigDecimal dinero) {
        this.dinero = dinero;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRuleta() { return idRuleta; }

    public void setIdRuleta(Integer idRuleta) { this.idRuleta = idRuleta; }
}
