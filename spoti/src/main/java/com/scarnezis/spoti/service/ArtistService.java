package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
import com.scarnezis.spoti.persistance.mappers.ArtistMapper;
import com.scarnezis.spoti.persistance.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class ArtistService {

  private final ArtistMapper mapper;
  private final ArtistRepository repository;

  public Artist createArtist(ArtistInDTO artistInDTO){
    //TODO check that not exists another artist, could do using aspects
    Artist artist = this.mapper.artistInDTOToArtist(artistInDTO);
    return this.repository.save(artist);
  }

  public List<Artist> findAllByName(String artistName){
    return this.repository.findAllByNameContaining(artistName);
  }

  public List<Artist> findAll(){
    return this.repository.findAll();
  }
}

