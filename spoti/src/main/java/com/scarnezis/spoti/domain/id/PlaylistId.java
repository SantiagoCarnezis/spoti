package com.scarnezis.spoti.domain.id;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistId implements Serializable {

  private String name;
  private Long user_id;

  @Override
  public String toString(){
    return name + " from user " + user_id;
  }
}
