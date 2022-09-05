package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import java.util.stream.Collectors;

@Data
@Embeddable
public class PlayQueue{

  @OneToMany(targetEntity = Device.class)
  @JoinColumn(table = "device", name = "device_id", unique = true, referencedColumnName = "id")
  private Collection<EnqueuedSong> enqueuedSongs;
  @Embedded
  private Reproduction reproduction;

  public PlayQueue() {
    enqueuedSongs =  new ArrayDeque<>();
    reproduction = new Reproduction();
  }

  public void play(){
    if (reproduction.haveSong()){
      reproduction.setSong(nextSong());
    }
    reproduction.run();
  }

  public void add(EnqueuedSong enqueuedSong){
    enqueuedSongs.add(enqueuedSong);
  }

  public void quit(EnqueuedSong enqueuedSong){
    enqueuedSongs.remove(enqueuedSong);
  }

  public Queue<Song> songs(){
    return (Queue<Song>) enqueuedSongs.stream().map(EnqueuedSong::getSong);
  }

  public Song nextSong(){
     return songs().poll();
  }

  public Integer size(){
    return enqueuedSongs.size();
  }
}
