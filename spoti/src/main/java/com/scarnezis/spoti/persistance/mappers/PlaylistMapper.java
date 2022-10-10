package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.dto.PlaylistInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.Track;
import com.scarnezis.spoti.persistance.entity.User;
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
 // @Mapping(target = "user_id", expression = "java( playlistInDTO.getUser().getId() ) ")
  Playlist playlistInDTOToPlaylist(PlaylistInDTO playlistInDTO);
}
