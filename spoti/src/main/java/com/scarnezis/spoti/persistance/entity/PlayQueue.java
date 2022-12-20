package com.scarnezis.spoti.persistance.entity;

import com.scarnezis.spoti.persistance.TableNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Embeddable
@AllArgsConstructor
public class PlayQueue{

  @ElementCollection
  @CollectionTable(name = TableNames.ENQUEUED_SONG)
  @OrderColumn(name = "queue_index")
  private Collection<EnqueuedSong> enqueuedSongs;

  public PlayQueue() {
    enqueuedSongs =  new ArrayDeque<>();
  }

  public void addSong(EnqueuedSong enqueuedSong){
    enqueuedSongs.add(enqueuedSong);
  }

  public void quit(EnqueuedSong enqueuedSong){
    enqueuedSongs.remove(enqueuedSong);
  }

  public Queue<Song> songs(){
    return enqueuedSongs.
        stream().
        sorted(Comparator.comparing(EnqueuedSong::getIndex)).
        map(EnqueuedSong::getSong).
        collect(Collectors.toCollection(PriorityQueue::new));
  }

  public Song nextSong(){
     return songs().poll();
  }

  public Integer size(){
    return enqueuedSongs.size();
  }

  public Integer getLastSongIndex(){
    Optional<EnqueuedSong> optionalEnqueuedSong = enqueuedSongs.
            stream().
            max(Comparator.comparing(EnqueuedSong::getIndex));
    return optionalEnqueuedSong.map(enqueuedSong -> enqueuedSong.getIndex() + 1).orElse(0);
  }

  public void shuffle(){
    List<Integer> newIndexes = getShuffleIndexes();
    enqueuedSongs.forEach(enqueuedSong -> enqueuedSong.setIndex(newIndexes.remove(0)));
  }

  private List<Integer> getShuffleIndexes() {
    Random random = new Random();
    return random.
    ints(0,  this.size()).
    distinct().
    limit(this.size()).
    boxed().
    collect(Collectors.toList());
  }
}
