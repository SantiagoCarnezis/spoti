package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = TableNames.ARTIST)
@AllArgsConstructor
@NoArgsConstructor
public class Artist implements Serializable {

  @Id
  private String name;
  private LocalDate birthday;
  @Column(name = "register_at", nullable = false)
  private LocalDate registerDate;
}
