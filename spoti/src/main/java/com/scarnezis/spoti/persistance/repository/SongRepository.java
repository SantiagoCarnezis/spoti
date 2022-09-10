package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

  public List<Song> findAllByName(String name);

  public List<Song> findAllByArtist(String name);
}
