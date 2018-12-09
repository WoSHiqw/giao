package com.oraclewdp.ddbookmarket.biz;

import java.util.List;

import com.oraclewdp.ddbookmarket.model.BigType;

public interface BigTypeBiz {

	boolean save(String name);

	List<BigType> findAllBigType();


	



}
