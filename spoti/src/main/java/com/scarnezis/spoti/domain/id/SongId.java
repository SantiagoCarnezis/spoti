package com.scarnezis.spoti.domain.id;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongId implements Serializable {

  private String name;
  private String artist_name;

  @Override
  public String toString(){
    return name + " from artist " + artist_name;
  }
}
