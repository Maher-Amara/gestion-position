package com.maherdev.gestionposition;

public class PositionContact {
    int id;
    String numero;
    String pseudo;
    String longitude;
    String latitude;

    public PositionContact(int id, String numero, String pseudo, String longitude, String latitude) {
        this.id = id;
        this.numero = numero;
        this.pseudo = pseudo;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "PositionContact{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
