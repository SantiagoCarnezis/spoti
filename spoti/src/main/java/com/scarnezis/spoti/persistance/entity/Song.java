package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

@Data
public class Song  implements Playable{

  private String name;
  private Artist artist;
  private Gender gender;


  @Override
  public void play() {
    Reproduction reproduction = new Reproduction(this);
    reproduction.run();
  }
}
