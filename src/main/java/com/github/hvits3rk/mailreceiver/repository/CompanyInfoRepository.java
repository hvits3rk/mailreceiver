package com.github.hvits3rk.mailreceiver.repository;

import com.github.hvits3rk.mailreceiver.model.CompanyInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, Long> {

    Optional<CompanyInfo> findByName(String name);
}
