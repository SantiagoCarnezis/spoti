package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class})
public interface PlaylistMapper{

  @Mapping(target = "createDate", expression = "java( LocalDate.now() ) ")
  public Playlist playlistInDTOToPlaylist(PlaylistInDTO playlistInDTO);
}
