package com.scarnezis.spoti.persistance.entity.id;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SongId implements Serializable {

  private String name;
  private String artist_name;

  public SongId(String name, String artistName) {
    this.name = name;
    this.artist_name = artistName;
  }

  //don´t delete
  public SongId() {
  }
}
