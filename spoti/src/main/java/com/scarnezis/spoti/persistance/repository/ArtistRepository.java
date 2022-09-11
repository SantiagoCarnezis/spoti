package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {

  List<Artist> findAllByNameContaining(String name);

}
