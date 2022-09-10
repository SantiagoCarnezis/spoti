package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;

public interface ArtistMapper {

  public Artist artistInDTOToArtist(ArtistInDTO artistInDTO);
}
