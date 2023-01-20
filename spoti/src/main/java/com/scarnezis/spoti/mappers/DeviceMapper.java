package com.scarnezis.spoti.mappers;

import com.scarnezis.spoti.domain.Device;
import com.scarnezis.spoti.domain.PlayQueue;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    imports = {PlayQueue.class})
public interface DeviceMapper {

  @Mapping(target = "playQueue", expression = "java( new PlayQueue() ) ")
  @Mapping(target = "playingSong", ignore = true)
  @Mapping(target = "playingSongSeconds", ignore = true)
  Device deviceInDTOToDevice(Object o);
}
