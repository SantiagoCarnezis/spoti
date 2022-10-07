package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.ArtistInDTO;
import com.scarnezis.spoti.persistance.entity.Artist;
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
