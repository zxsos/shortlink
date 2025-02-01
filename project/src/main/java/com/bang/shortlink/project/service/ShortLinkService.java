package com.bang.shortlink.project.service;

import com.bang.shortlink.project.dao.entity.ShortLinkDO;
import com.bang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 短链接接口层
 */

public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) ;

}
