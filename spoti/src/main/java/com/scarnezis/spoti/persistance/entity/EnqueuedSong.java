package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class EnqueuedSong {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name = "song_id", unique = true)
  private Song song;
  @Column(name = "_order")
  private Integer order;
}
