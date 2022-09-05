package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Embeddable
public class Track {

  @ManyToOne
  @JoinColumn(name = "song_id")
  private Song song;
  private LocalDate addedAt;
  @ManyToOne
  private User addedBy;
}
