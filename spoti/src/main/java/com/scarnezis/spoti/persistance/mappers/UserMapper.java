package com.scarnezis.spoti.persistance.mappers;


import com.scarnezis.spoti.persistance.dto.UserInDTO;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.HashSet;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {LocalDate.class, HashSet.class, Playlist.class})
public interface UserMapper {

  @Mapping(target = "registerDate", expression = "java( LocalDate.now() ) ")
  //@Mapping(target = "playlists", expression = "java( new HashSet<Playlist>() ) ")
  User userInDTOToUser(UserInDTO userInDTO);
}
