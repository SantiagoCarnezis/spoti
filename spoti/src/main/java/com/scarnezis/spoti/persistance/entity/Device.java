package com.scarnezis.spoti.persistance.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table
public class Device {

  @Id
  @GeneratedValue
  private int id;

  @OneToOne
  private User user;

  @Embedded
  private PlayQueue playQueue;
}
