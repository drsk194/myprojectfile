package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    private String address;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    private String gender;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    public Long getProfileId() {
        return profileId;
    }
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
    
    public Long getId() {
        return profileId;
    }
    public void setId(Long id) {
        this.profileId = id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @JsonProperty("user")
    public Customer getUser() {
        return customer;
    }
    
    public void setUser(Customer user) {
        this.customer = user;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getDobAsString() {
        return dob != null ? dob.toString() : null;
    }
    public void setDobFromString(String dobString) {
        this.dob = dobString != null ? LocalDate.parse(dobString) : null;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (customer != null) {
            customer.setFirstName(firstName);
        }
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (customer != null) {
            customer.setLastName(lastName);
        }
    }
    
    public Profile() {
    }
    public Profile(String address, LocalDate dob, String gender) {
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }
}
