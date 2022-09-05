package com.scarnezis.spoti.persistance.entity;

import javax.persistence.*;

@Entity
@Table
public class Test {
  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  private Song song;
}
