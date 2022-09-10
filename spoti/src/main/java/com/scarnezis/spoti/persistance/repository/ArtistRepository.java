package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

  Optional<Artist> findByName(String name);
}
