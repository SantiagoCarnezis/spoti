package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class SearchEntity {

  public <E, ID> E get(ID id, JpaRepository<E, ID> repository) throws Exception {
    Optional<E> optional = repository.findById(id);
    if(!optional.isPresent())
      throw new Exception();
    return optional.get();
  }
}
