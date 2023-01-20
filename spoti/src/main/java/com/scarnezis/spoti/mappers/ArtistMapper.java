package com.scarnezis.spoti.mappers;

import com.scarnezis.spoti.dto.ArtistInDTO;
import com.scarnezis.spoti.domain.Artist;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {LocalDate.class})
public interface ArtistMapper {

  @Mapping(target = "registerDate", expression = "java( LocalDate.now() ) ")
  Artist artistInDTOToArtist(ArtistInDTO artistInDTO);
}
