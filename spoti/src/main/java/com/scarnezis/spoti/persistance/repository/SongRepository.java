package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, SongId> {

  public List<Song> findAllByNameContaining(String name);

  public List<Song> findAllByArtist(String name);

  @Modifying
  @Query(value = "UPDATE song SET numberOfLikes = :numberOfLikes WHERE name = :song and artist = :artist")
  public void setSongLike(String song, Artist artist, Integer numberOfLikes);
}
