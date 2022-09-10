package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
