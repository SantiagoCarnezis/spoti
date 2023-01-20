package com.scarnezis.spoti.repository;

import com.scarnezis.spoti.domain.Playlist;
import com.scarnezis.spoti.domain.id.PlaylistId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, PlaylistId> {

  Page<Playlist> findAllByNameContaining(String playlistName, Pageable pageable);
}
