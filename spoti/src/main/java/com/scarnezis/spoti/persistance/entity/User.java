package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = TableNames.USER)
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
