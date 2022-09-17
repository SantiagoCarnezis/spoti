package com.scarnezis.spoti.persistance.dto;

import com.scarnezis.spoti.persistance.entity.Track;
import lombok.Data;

import java.util.Collection;

@Data
public class PlaylistInDTO {

  private String name;
  private Collection<Track> tracks;
  private String description;
}
