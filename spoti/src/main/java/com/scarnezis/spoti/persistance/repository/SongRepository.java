package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, SongId> {

  List<Song> findAllByNameContaining(String name);

  List<Song> findAllByArtist(Artist artist);

  @Modifying
  @Query(value = "UPDATE " + TableNames.SONG +
      " SET numberOfLikes = :numberOfLikes WHERE name = :songName and artist = :artist",
      nativeQuery = true)
  void setSongLike(@Param("songName") String songName, @Param("artist") Artist artist,
                   @Param("numberOfLikes") Integer numberOfLikes);
}