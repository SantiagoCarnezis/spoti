package com.scarnezis.spoti.persistance.entity;


import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Data
public class Device {

  private User user;
  @Embedded
  private PlayQueue playQueue;
}
