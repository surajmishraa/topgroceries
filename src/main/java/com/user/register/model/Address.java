package com.user.register.model;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private Integer id;

    @Column(name = "hno")
    private String hno;

    @Column(name = "locality")
    private String locality;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Address() {
    }

    public Address(Integer id, String hno, String locality, String city, String state, String pincode, User user) {
        this.id = id;
        this.hno = hno;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHno() {
        return hno;
    }

    public void setHno(String hno) {
        this.hno = hno;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", hno='" + hno + '\'' +
                ", locality='" + locality + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", user=" + user +
                '}';
    }
}
