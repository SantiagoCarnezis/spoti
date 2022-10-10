package com.scarnezis.spoti.persistance.dto;

import com.scarnezis.spoti.persistance.entity.Track;
import com.scarnezis.spoti.persistance.entity.User;
import lombok.Data;

import java.util.Collection;

@Data
public class PlaylistInDTO {

  private String name;
  private String description;
  //private User user;
}
