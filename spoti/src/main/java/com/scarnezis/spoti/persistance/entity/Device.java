package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

@Data
public class Device {

  private User user;
  private Reproduction reproduction;
  private PlayQueue playQueue;
}
