package com.scarnezis.spoti.service;

import com.scarnezis.spoti.dto.ArtistInDTO;
import com.scarnezis.spoti.domain.Artist;
import com.scarnezis.spoti.exceptions.ResourceAlreadyExistsException;
import com.scarnezis.spoti.exceptions.ResourceNotFoundException;
import com.scarnezis.spoti.mappers.ArtistMapper;
import com.scarnezis.spoti.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class ArtistService {

  private final ArtistMapper mapper;
  private final ArtistRepository repository;

  public Artist get(String artistName){
    return repository
        .findById(artistName)
        .orElseThrow(()
            -> new ResourceNotFoundException("Artist " + artistName));
  }

  public void validateExists(String artistName) {
    if( repository.existsById(artistName))
      throw new ResourceAlreadyExistsException(artistName);
  }

  public Artist createArtist(ArtistInDTO artistInDTO){
    Artist artist = this.mapper.artistInDTOToArtist(artistInDTO);
    validateExists(artist.getName());
    return this.repository.save(artist);
  }

  public Page<Artist> findAllByName(String artistName, Integer offset, Integer pageSize){
    if (offset == null)
      offset = 0;
    return this.repository.findAllByNameContaining(artistName, PageRequest.of(offset, pageSize));
  }

  public Page<Artist> findAll(Integer offset, int pageSize){
    return this.repository.findAll(PageRequest.of(offset, pageSize));
  }

}

