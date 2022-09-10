package com.scarnezis.spoti.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@Data
@Embeddable
public class PlayQueue{

  @ElementCollection
  @CollectionTable(name = "enqueued_song")
  private Collection<EnqueuedSong> enqueuedSongs;


  public PlayQueue() {
    enqueuedSongs =  new ArrayDeque<>();
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

  public void shcuffle(){

  }
}
