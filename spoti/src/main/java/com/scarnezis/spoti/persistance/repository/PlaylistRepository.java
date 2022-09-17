package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

  @Modifying
  @Query(value = "SELECT * FROM " + TableNames.PLAYLIST + " WHERE user = :user_id",
      nativeQuery = true)
  List<Playlist> findAllByUser(@Param("user_id") Long userId);

  List<Playlist> findAllByNameContaining(String playlistName);
}
