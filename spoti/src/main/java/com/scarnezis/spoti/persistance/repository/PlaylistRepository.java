package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.id.PlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, PlaylistId> {

  //TODO is necessary?
  @Modifying
  @Query(value = "SELECT * FROM " + TableNames.PLAYLIST + " WHERE user_id = :user_id",
      nativeQuery = true)
  List<Playlist> findAllByUser_id(@Param("user_id") Long userId);

  List<Playlist> findAllByNameContaining(String playlistName);
}
