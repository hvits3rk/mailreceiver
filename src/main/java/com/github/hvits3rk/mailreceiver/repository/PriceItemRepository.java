package com.github.hvits3rk.mailreceiver.repository;

import com.github.hvits3rk.mailreceiver.entity.PriceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceItemRepository extends CrudRepository<PriceItem, Long> {
}
