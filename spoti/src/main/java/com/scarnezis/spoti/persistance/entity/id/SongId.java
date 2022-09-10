package com.scarnezis.spoti.persistance.entity.id;

import com.scarnezis.spoti.persistance.entity.Artist;
import lombok.Data;

import java.io.Serializable;

@Data
public class SongId implements Serializable {

  private String name;
  private Artist artist;
}
