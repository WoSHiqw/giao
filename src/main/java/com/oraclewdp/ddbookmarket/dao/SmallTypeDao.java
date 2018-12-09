package com.oraclewdp.ddbookmarket.dao;

import com.oraclewdp.ddbookmarket.model.SmallType;

import java.util.List;

public interface SmallTypeDao {

	boolean save(SmallType smallType);

	List<SmallType> findAllByBid(int bid);

    int findBidById(int sid);
}
