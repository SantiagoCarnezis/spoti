package com.scarnezis.spoti.mappers;


import com.scarnezis.spoti.dto.UserInDTO;
import com.scarnezis.spoti.domain.Playlist;
import com.scarnezis.spoti.domain.User;
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
  User userInDTOToUser(UserInDTO userInDTO);
}
