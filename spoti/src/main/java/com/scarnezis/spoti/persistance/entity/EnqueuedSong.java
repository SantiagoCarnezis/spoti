package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class EnqueuedSong {

  @ManyToOne
  @JoinColumn(name = "song_id", nullable = false)
  private Song song;
  @Column(name = "queue_index", nullable = false)
  private Integer index;
}
