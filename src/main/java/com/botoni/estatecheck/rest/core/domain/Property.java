package com.botoni.estatecheck.rest.core.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Property {
    private UUID id;
    private User user;
    private String nome;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer unitNumber;

    public Property(UUID id, User user, String nome, BigDecimal latitude, BigDecimal longitude, Integer unitNumber) {
        this.id = id;
        this.user = user;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.unitNumber = unitNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", user=" + user +
                ", nome='" + nome + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", unitNumber=" + unitNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
