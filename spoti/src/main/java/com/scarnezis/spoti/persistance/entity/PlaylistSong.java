package com.scarnezis.spoti.persistance.entity;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity
@Embeddable
public class PlaylistSong {

  private Song song;
  private LocalDate addedAt;
  private User addedBy;
}
