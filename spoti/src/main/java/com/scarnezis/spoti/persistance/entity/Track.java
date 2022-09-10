package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Embeddable
public class Track {

  @ManyToOne
  @JoinColumn(name = "song_id", nullable = false)
  private Song song;
  @Column(nullable = false)
  private LocalDate addedAt;
  @ManyToOne
  @JoinColumn(name = "added_by_user_id", nullable = false)
  private User addedBy;
}
