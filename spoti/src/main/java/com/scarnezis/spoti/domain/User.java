package com.scarnezis.spoti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private LocalDate birthday;
  @Column(nullable = false)
  private LocalDate registerDate;

  public User(String name, String email, LocalDate birthday, LocalDate registerDate) {
    this.name = name;
    this.email = email;
    this.birthday = birthday;
    this.registerDate = registerDate;
  }
}
