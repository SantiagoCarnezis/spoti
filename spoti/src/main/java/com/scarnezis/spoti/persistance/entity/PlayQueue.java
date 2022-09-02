package com.scarnezis.spoti.persistance.entity;

import lombok.Data;
import java.util.Collection;
import java.util.Queue;

// singleton
@Data
public class PlayQueue implements Playable{

  private Collection<Song> songs;

  public PlayQueue(Collection<Song> songs) {
    this.songs = songs;
  }

  @Override
  public void play() {
    while (!songs.isEmpty()){
      Song song = ((Queue<Song>) songs).remove();
      song.play();
    }
  }

  public void add(Song song){
    songs.add(song);
  }

  public void quit(Song song){
    songs.remove(song);
  }
}
