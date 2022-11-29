package com.scarnezis.spoti.persistance.repository;

import com.scarnezis.spoti.persistance.TableNames;
import com.scarnezis.spoti.persistance.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

  @Modifying
  @Transactional
  @Query(value = "UPDATE " + TableNames.DEVICE + " SET user_id = null WHERE user_id = :userId",
      nativeQuery = true)
  void logOut(@Param("userId") Long userId);
}
