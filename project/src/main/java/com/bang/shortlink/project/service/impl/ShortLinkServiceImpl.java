package com.bang.shortlink.project.service.impl;

import com.bang.shortlink.project.dao.entity.ShortLinkDO;
import com.bang.shortlink.project.dao.mapper.ShortLinkMapper;
import com.bang.shortlink.project.service.ShortLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短链接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl  extends ServiceImpl<ShortLinkMapper, ShortLinkDO>  implements ShortLinkService {
}

