package com.masivian.ruleta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash("Ruleta")
public class Ruleta {

    @Id
    private Integer id;

    private Estado estado;

    protected Ruleta(){}

    public Ruleta(Estado estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ruleta{" +
                "id=" + id +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruleta ruleta = (Ruleta) o;
        return id.equals(ruleta.id) &&
                estado == ruleta.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado);
    }

}
