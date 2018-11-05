package com.cg.employee.service.starter.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employee_Tbl")
public class Employee {

    @Id
    @Column(name = "Emp_Id")
    private int id;
    @Column(name = "Emp_Name")
    private String name;
    @Column(name = "Emp_Designation")
    private String designation;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Address> addresses;

    public Employee() {
    }

    public Employee(int id, String name, String designation, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
