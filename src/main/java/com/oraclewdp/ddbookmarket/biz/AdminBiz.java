package com.oraclewdp.ddbookmarket.biz;

import com.oraclewdp.ddbookmarket.model.Admin;

public interface AdminBiz {
    boolean findUser(Admin admin);

    boolean findByNameAndPwd(Admin admin);
}
