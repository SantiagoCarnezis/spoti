package com.scarnezis.spoti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EnqueuedSong {

  @ManyToOne
  @JoinColumn(name = "song_name", nullable = false, referencedColumnName = "name")
  @JoinColumn(name = "song_artist", nullable = false, referencedColumnName = "artist_name")
  private Song song;
  @Column(name = "queue_index", nullable = false)
  private Integer index;
}
