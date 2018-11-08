package com.cg.employee.service.starter.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Address_Tbl")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Address_Id")
    private int id;
    @Column(name = "Flat_No")
    private String flatNo;
    @Column(name = "Area")
    private String area;
    @Column(name = "City")
    private String city;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "Address_Type")
    private String addressType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="emp_student_fk")
    @JsonBackReference
    private Employee employee;

    public Address() {
    }

    public Address(String flatNo, String area, String city, String state, String country, String addressType, Employee employee) {
        this.flatNo = flatNo;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressType = addressType;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (flatNo != null ? !flatNo.equals(address.flatNo) : address.flatNo != null) return false;
        if (area != null ? !area.equals(address.area) : address.area != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (addressType != null ? !addressType.equals(address.addressType) : address.addressType != null) return false;
        return employee != null ? employee.equals(address.employee) : address.employee == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (flatNo != null ? flatNo.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (addressType != null ? addressType.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", flatNo='" + flatNo + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
