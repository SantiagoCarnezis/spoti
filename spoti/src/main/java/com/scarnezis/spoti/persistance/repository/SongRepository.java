package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, SongId> {

  List<Song> findAllByNameContaining(String name);

  List<Song> findAllByArtistContaining(String artistName);

  List<Song> findAllByNameAndArtist(String songName, String artistName);

  @Modifying
  @Query(value = "UPDATE " + TableNames.SONG +
      " SET numberOfLikes = :numberOfLikes WHERE name = :songName and artist = :artist",
      nativeQuery = true)
  void setSongLike(@Param("songName") String songName, @Param("artist") Artist artist,
                   @Param("numberOfLikes") Integer numberOfLikes);
}
