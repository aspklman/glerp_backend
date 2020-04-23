package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.Custom;
import org.jeecg.modules.order.mapper.CustomMapper;
import org.jeecg.modules.order.service.ICustomService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户表
 * @Author: jeecg-boot
 * @Date:   2019-07-26
 * @Version: V1.0
 */
@Service
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom> implements ICustomService {

}
