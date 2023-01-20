package com.scarnezis.spoti.repository;

import com.scarnezis.spoti.domain.Song;
import com.scarnezis.spoti.domain.id.SongId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, SongId> {

  Page<Song> findAllByNameContaining(String name, Pageable pageable);

  Page<Song> findAllByArtist_nameContaining(String artistName, Pageable pageable);

  Page<Song> findAllByNameAndArtist_name(String songName, String artistName, Pageable pageable);

  @Modifying
  @Transactional
  @Query(value = "UPDATE song " +
      "SET likesCounter = :likesCounter " +
      "WHERE name = :songName and artist_name = :artist",
      nativeQuery = true)
  void setSongLike(@Param("songName") String songName, @Param("artist") String artist,
                             @Param("likesCounter") Integer likesCounter);
}
