package com.bang.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class ShortLinkGroupSortReqDTO {

    /**
     * 分组id
     */
    private String gid;
    /**
     * 排序
     */
    private Integer sortOrder;
}
