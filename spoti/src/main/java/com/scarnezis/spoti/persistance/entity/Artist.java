package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Artist {

  @Id
  private String name;
  private LocalDate birthday;
  @Column(name = "register_at", nullable = false)
  private LocalDate registerDate;
}
