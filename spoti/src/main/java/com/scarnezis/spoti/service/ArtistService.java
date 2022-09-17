package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.entity.Song;
import com.scarnezis.spoti.persistance.entity.id.SongId;
import com.scarnezis.spoti.persistance.mappers.ArtistMapper;
import com.scarnezis.spoti.persistance.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class ArtistService {

  private final ArtistMapper mapper;
  private final ArtistRepository repository;

  public ArtistService(ArtistMapper mapper, ArtistRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public Artist createArtist(ArtistInDTO artistInDTO){
    Artist artist = this.mapper.artistInDTOToArtist(artistInDTO);
    return this.repository.save(artist);
  }

  public List<Artist> findAllByName(String artistName){
    return this.repository.findAllByNameContaining(artistName);
  }


}
