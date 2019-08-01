package com.sparrow.repository;

import com.sparrow.model.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {
    Set<PriceListItem> findAllByPriceListId(Long id);
}