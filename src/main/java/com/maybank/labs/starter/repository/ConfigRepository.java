package com.maybank.labs.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maybank.labs.starter.entities.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer> {

	@Query("SELECT C FROM Config C WHERE C.configEntry = :configEntry and C.configShortCode = :configShortCode")
	public Config getConfig(@Param("configEntry") String configEntry, @Param("configShortCode") String configShortCode);
}
