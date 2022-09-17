package com.scarnezis.spoti.persistance.mappers;


import com.scarnezis.spoti.persistance.dto.UserInDTO;
import com.scarnezis.spoti.persistance.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(imports = {LocalDate.class})
public interface UserMapper {

  @Mapping(target = "registerDate", expression = "java( LocalDate.now() ) ")
  public User userInDTOToUser(UserInDTO userInDTO);
}
