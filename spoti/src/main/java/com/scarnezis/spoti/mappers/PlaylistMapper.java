package com.scarnezis.spoti.mappers;

import com.scarnezis.spoti.dto.PlaylistInDTO;
import com.scarnezis.spoti.domain.Playlist;
import com.scarnezis.spoti.domain.Track;
import com.scarnezis.spoti.domain.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {LocalDate.class, ArrayList.class, Track.class, User.class})
public interface PlaylistMapper{

  @Mapping(target = "createDate", expression = "java( LocalDate.now() ) ")
  @Mapping(target = "tracks", expression = "java( new ArrayList<Track>() ) ")
  Playlist playlistInDTOToPlaylist(PlaylistInDTO playlistInDTO);
}
