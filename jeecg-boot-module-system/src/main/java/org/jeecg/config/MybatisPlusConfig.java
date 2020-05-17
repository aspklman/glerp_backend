//
//
//package org.jeecg.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.baomidou.mybatisplus.core.parser.ISqlParser;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
//import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
//
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.LongValue;
//import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
//import net.sf.jsqlparser.expression.operators.relational.InExpression;
//import net.sf.jsqlparser.schema.Column;
//
///**
// * @author miemie
// * @since 2018-08-10
// */
//@Slf4j
//@Configuration
//@MapperScan(value = {"org.jeecg.modules.**.mapper*"})
//public class MybatisPlusConfig {
//
//    /**
//     * 多租户属于 SQL 解析部分，依赖 MP 分页插件
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        /*
//         * 【测试多租户】 SQL 解析处理拦截器<br>
//         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//         */
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new MyTenantParser();
//        log.info("运行到这里:" + tenantSqlParser);
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//
//            /**
//             * 2019-8-1
//             *
//             * https://gitee.com/baomidou/mybatis-plus/issues/IZZ3M
//             *
//             * tenant_id in (1,2)
//             * @param  where 如果是where，可以追加，不是where的情况：比如当insert时，不能insert into user(name, tenant_id) values('test', tenant_id IN (1, 2));
//             * @return
//             */
//
//            public Expression getTenantId(boolean where) {
//                final boolean multipleTenantIds = true;//这里只是演示切换单个tenantId和多个tenantId
//                //具体场景，可以根据情况来拼接
//                if (where && multipleTenantIds) {
//                    //演示如何实现tenant_id in (1,2)
//                    return multipleTenantIdCondition();
//                } else {
//                    //演示：tenant_id=1
//                    return singleTenantIdCondition();
//                }
//            }
//
//            private Expression singleTenantIdCondition() {
//                return new LongValue(1);//ID自己想办法获取到
//            }
//
//            private Expression multipleTenantIdCondition() {
//                final InExpression inExpression = new InExpression();
//                inExpression.setLeftExpression(new Column(getTenantIdColumn()));
//                final ExpressionList itemsList = new ExpressionList();
//                final List<Expression> inValues = new ArrayList<>(2);
//                inValues.add(new LongValue(1));//ID自己想办法获取到
//                inValues.add(new LongValue(2));
//                itemsList.setExpressions(inValues);
//                inExpression.setRightItemsList(itemsList);
//                return inExpression;
//            }
//
//            @Override
//            public Expression getTenantId() {
//                return null;
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//                /*if ("user".equals(tableName)) {
//                    return true;
//                }*/
////                return false;
//                return !"user".equalsIgnoreCase(tableName);
//            }
//
//        });
//
//        sqlParserList.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
////        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
////            @Override
////            public boolean doFilter(MetaObject metaObject) {
////                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
////                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
////                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
////                    return true;
////                }
////                return false;
////            }
////        });
//        return paginationInterceptor;
//    }
//}





















package org.jeecg.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    //需要注入的字段
    private static final String SYSTEM_TENANT_ID = "fact_no";

    //忽略不需要注入的表
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("sys_tenant");

    @Autowired
    private ApiContext apiContext;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        log.info("租户ID是：" + SYSTEM_TENANT_ID);

        // SQL解析处理拦截：增加租户处理回调。
        TenantSqlParser tenantSqlParser = new TenantSqlParser()
                .setTenantHandler(new TenantHandler() {

                    @Override
                    public Expression getTenantId() {
                        // 从当前系统上下文中取出当前请求的内码，通过解析器注入到SQL中。
                        Long currentWithinCode = apiContext.getCurrentProviderId();
                        log.info("租户代码是：" + currentWithinCode);
                        if (null == currentWithinCode) {
                            throw new RuntimeException("从当前系统上下文中取出当前请求的内码为空。");
                        }
                        return new LongValue(currentWithinCode);
                    }

                    @Override
                    public String getTenantIdColumn() {
                        return SYSTEM_TENANT_ID;
                    }

                    @Override
                    public boolean doTableFilter(String tableName) {
                        // 忽略掉一些表：如租户表（provider）本身不需要执行这样的处理。
                        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
                    }
                });
        paginationInterceptor.setSqlParserList(Lists.newArrayList(tenantSqlParser));


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
