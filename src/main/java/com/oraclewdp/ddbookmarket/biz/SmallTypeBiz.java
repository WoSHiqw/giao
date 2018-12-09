package com.oraclewdp.ddbookmarket.biz;

import com.oraclewdp.ddbookmarket.model.SmallType;

import java.util.List;

public interface SmallTypeBiz {

	boolean save(SmallType smallType);

	List<SmallType> findAllByBid(int bid);

    int findBidById(int sid);
}
