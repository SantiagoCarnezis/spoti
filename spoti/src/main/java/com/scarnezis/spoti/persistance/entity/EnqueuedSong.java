package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class EnqueuedSong {

  @ManyToOne
  @JoinColumn(name = "song_name", nullable = false, referencedColumnName = "name")
  @JoinColumn(name = "song_artist", nullable = false, referencedColumnName = "artist_id")
  private Song song;
  @Column(name = "queue_index", nullable = false)
  private Integer index;
}
