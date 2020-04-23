package org.jeecg.modules.order.service.impl;

import org.jeecg.modules.order.entity.Country;
import org.jeecg.modules.order.mapper.CountryMapper;
import org.jeecg.modules.order.service.ICountryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 目的地表
 * @Author: jeecg-boot
 * @Date:   2019-08-02
 * @Version: V1.0
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

}
