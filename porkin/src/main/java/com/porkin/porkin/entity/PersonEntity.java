package com.porkin.porkin.entity;

import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.porkin.paymentMethods.entity.PixEntity;
import com.porkin.porkin.roles.PersonRoles;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class PersonEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private PersonRoles role;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "pixId")
  private PixEntity pix;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "payPalId")
  private PayPalEntity payPal;

  // constructors

  public PersonEntity(PersonDTO personDTO) {
    BeanUtils.copyProperties(personDTO, this);
  }

  public PersonEntity(String name, String username, String email, String password, PersonRoles role) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public PersonEntity() {}

  // getters and setters

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //return List.of();
    if(this.role == PersonRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    //return UserDetails.super.isAccountNonExpired();
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    //return UserDetails.super.isAccountNonLocked();
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    //return UserDetails.super.isCredentialsNonExpired();
    return true;
  }

  @Override
  public boolean isEnabled() {
    //return UserDetails.super.isEnabled();
    return true;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PersonRoles getRole() {
    return role;
  }

  public void setRole(PersonRoles role) {
    this.role = role;
  }

  public String getPix() {
    return pix != null ? pix.getPix() : "Chave pix não cadastrada";
  }

  public void setPix(PixEntity pix) {
    this.pix = pix;
  }

  public String getPayPal() {
    return payPal != null ? payPal.getPayPal() : "PayPal não cadastrado";
  }

  public void setPayPal(PayPalEntity payPal) {
    this.payPal = payPal;
  }

  // equals and hashCode

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PersonEntity that = (PersonEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
