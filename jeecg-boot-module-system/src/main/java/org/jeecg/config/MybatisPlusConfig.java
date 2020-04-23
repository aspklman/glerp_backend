




package org.jeecg.config;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 单数据源配置（jeecg.datasource.open = false时生效）
 *
 * @Author zhoujf
 */
@Slf4j
@Configuration
@MapperScan(value = {"org.jeecg.modules.**.mapper*"})
public class MybatisPlusConfig {

//    //需要注入的字段
//    private static final String SYSTEM_TENANT_ID = "provider_id";
//
//    //忽略不需要注入的表
//    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("sys_user", "sys_role");
//
//    @Autowired
//    private ApiContext apiContext;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//
//        log.info("租户ID是：" + SYSTEM_TENANT_ID);
//
//        // SQL解析处理拦截：增加租户处理回调。
//        TenantSqlParser tenantSqlParser = new TenantSqlParser()
//                .setTenantHandler(new TenantHandler() {
//
//                    @Override
//                    public Expression getTenantId() {
//                        // 从当前系统上下文中取出当前请求的内码，通过解析器注入到SQL中。
//                        Long currentWithinCode = apiContext.getCurrentProviderId();
//                        log.info("租户代码是：" + currentWithinCode);
//                        if (null == currentWithinCode) {
//                            throw new RuntimeException("从当前系统上下文中取出当前请求的内码为空。");
//                        }
//                        return new LongValue(currentWithinCode);
//                    }
//
//                    @Override
//                    public String getTenantIdColumn() {
//                        return SYSTEM_TENANT_ID;
//                    }
//
//                    @Override
//                    public boolean doTableFilter(String tableName) {
//                        // 忽略掉一些表：如租户表（provider）本身不需要执行这样的处理。
//                        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
//                    }
//                });
//        paginationInterceptor.setSqlParserList(Lists.newArrayList(tenantSqlParser));


        return new PaginationInterceptor();
    }



//    @Bean(name = "performanceInterceptor")
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }



//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


}
