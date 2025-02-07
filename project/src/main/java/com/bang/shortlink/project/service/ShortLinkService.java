package com.bang.shortlink.project.service;

import com.bang.shortlink.project.dao.entity.ShortLinkDO;
import com.bang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.bang.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.util.List;

/**
 * 短链接接口层
 */

public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) ;

    /**
     * 分页查询短链接
     * @param requestParam 分页查询请求参数
     * @return 分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询分组内数量
     * @param requestParam 查询分组内数量请求参数
     * @return 数量
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * 修改短链接
     * @param requestParam 修改短链接请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 短链接跳转
     * @param shortUri
     * @param servletRequest
     * @param servletResponse
     */
    void restoreUrl(String shortUri, ServletRequest servletRequest, ServletResponse servletResponse);
}
