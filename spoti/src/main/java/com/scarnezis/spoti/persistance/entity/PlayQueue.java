package com.scarnezis.spoti.persistance.entity;

import lombok.Data;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Queue;

@Data
@Entity
@Embeddable
public class PlayQueue{

  private Collection<Song> songs;
  private Reproduction reproduction;

  public PlayQueue(Collection<Song> songs) {
    this.songs =  songs;
    reproduction = new Reproduction();
  }

  public void play(){
    if (reproduction.getSong() == null){
      reproduction.setSong(nextSong());
    }
    reproduction.run();
  }

  public void add(Song song){
    songs.add(song);
  }

  public void quit(Song song){
    songs.remove(song);
  }

  public Song nextSong(){
     return ((Queue<Song>) songs).poll();
  }

}
