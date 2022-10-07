package com.scarnezis.spoti.persistance.entity.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistId implements Serializable {

  private String name;
  private Long user_id;
}
