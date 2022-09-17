package com.scarnezis.spoti.persistance.dto;

import com.scarnezis.spoti.persistance.entity.Playlist;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserInDTO {

  private String name;
  private Set<Playlist> playlists;
  private String email;
  private LocalDate birthdate;
}
