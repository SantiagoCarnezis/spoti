package com.scarnezis.spoti.persistance.mappers;

import com.scarnezis.spoti.persistance.entity.Device;
import com.scarnezis.spoti.persistance.entity.PlayQueue;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {PlayQueue.class})
public interface DeviceMapper {

  @Mapping(target = "playQueue", expression = "java( new PlayQueue() ) ")
  @Mapping(target = "playingSong", ignore = true)
  @Mapping(target = "playingSongSeconds", ignore = true /*, constant = "0"*/)
  Device deviceInDTOToDevice(Object o);
}
