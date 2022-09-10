package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.SongInDTO;
import com.scarnezis.spoti.persistance.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class})
public interface SongMapper {

  @Mapping(target = "releaseDate", expression = "java( LocalDate.now() ) ")
  @Mapping(target = "numberOfLikes", constant = "0")
  public Song songInDTOToSong(SongInDTO songInDTO);
}
