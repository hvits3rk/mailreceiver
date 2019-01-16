package com.github.hvits3rk.mailreceiver.service;

import com.github.hvits3rk.mailreceiver.model.CompanyInfo;
import com.github.hvits3rk.mailreceiver.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository repository;

    public CompanyInfoService(CompanyInfoRepository repository) {
        this.repository = repository;
    }

    public CompanyInfo save(CompanyInfo companyInfo) {
        return repository.save(companyInfo);
    }

    public CompanyInfo findByName(String name) {
        return repository.findByName(name).orElse(null);
    }
}
