package com.scarnezis.spoti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Track {

  @ManyToOne
  @JoinColumn(name = "song_name", nullable = false, referencedColumnName = "name")
  @JoinColumn(name = "song_artist", nullable = false, referencedColumnName = "artist_name")
  private Song song;
  @Column(nullable = false)
  private LocalDate addedAt;
  @ManyToOne
  @JoinColumn(name = "added_by_user_id", nullable = false)
  private User addedBy;
}
