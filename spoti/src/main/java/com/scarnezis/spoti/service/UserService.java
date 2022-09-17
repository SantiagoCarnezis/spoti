package com.scarnezis.spoti.service;

import com.scarnezis.spoti.persistance.dto.UserInDTO;
import com.scarnezis.spoti.persistance.entity.Device;
import com.scarnezis.spoti.persistance.entity.Playlist;
import com.scarnezis.spoti.persistance.entity.User;
import com.scarnezis.spoti.persistance.mappers.UserMapper;
import com.scarnezis.spoti.persistance.repository.DeviceRepository;
import com.scarnezis.spoti.persistance.repository.UserRepository;
import lombok.Data;


@Data
public class UserService {

  private UserRepository userRepository;
  private DeviceRepository deviceRepository;
  private UserMapper userMapper;
  private SearchEntity searcherEntity;

  public User signIn(UserInDTO userInDTO){
    User user = this.userMapper.userInDTOToUser(userInDTO);
    return this.userRepository.save(user);
  }

  public void logIn(Long deviceId, Long userId) throws Exception {
    User user = searcherEntity.get(userId, userRepository);
    this.deviceRepository.logOut(userId);
    Device device = searcherEntity.get(deviceId, deviceRepository);
    device.setUser(user);
  }

  public void logOut(Long deviceId) throws Exception {
    Device device = searcherEntity.get(deviceId, deviceRepository);
    device.logOut();
  }
}
