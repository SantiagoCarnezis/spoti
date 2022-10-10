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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, SongId> {

  List<Song> findAllByNameContaining(String name);

  List<Song> findAllByArtist_nameContaining(String artistName);

  List<Song> findAllByNameAndArtist_name(String songName, String artistName);

  @Modifying
  @Transactional
  @Query(value = "UPDATE " + TableNames.SONG +
      " SET number_of_likes = :numberOfLikes WHERE name = :songName and artist_name = :artist",
      nativeQuery = true)
  void setSongLike(@Param("songName") String songName, @Param("artist") String artist,
                   @Param("numberOfLikes") Integer numberOfLikes);
}
