package com.scarnezis.spoti.repository;

import com.scarnezis.spoti.domain.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {

  Page<Artist> findAllByNameContaining(String name, Pageable pageable);

}
