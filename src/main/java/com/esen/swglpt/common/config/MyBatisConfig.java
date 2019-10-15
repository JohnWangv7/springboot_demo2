package com.esen.swglpt.common.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.power.datasource.database.DynamicDataSource;
import com.power.mybatis.CustomSqlSessionTemplate;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdong
 */
@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig extends AbstractDataSourceConfig {

    static final String BASE_PACKAGE = "com.esen.swglpt.mapper";

    private static final String ALIASES_PACKAGE = "com.esen.swglpt.entity";

    static final String MAPPER_LOCATION = "classpath*:com/esen/swglpt/mapper/*.xml";

//    @Primary
//    @Bean(name = "dataSourceSB")
//    public DataSource dataSourceSB(Environment env) {
//        String prefix = "spring.datasource.druid.sb.";
//        return getDataSource(env,prefix,"sb");
//    }

    @Primary
    @Bean(name = "dataSourceSB")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSourceSB(Environment env) {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("dataSourceSB") DataSource dataSourceSB) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("sb", dataSourceSB);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSourceSB);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactorySB")
    public SqlSessionFactory sqlSessionFactorySB(@Qualifier("dataSourceSB") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }


    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactorySB") SqlSessionFactory factoryOracle) throws Exception {
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put("oracle", factoryOracle);

        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(factoryOracle);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }

    /**
     * 创建数据源
     *
     * @param dataSource
     * @return
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setTypeAliasesPackage(ALIASES_PACKAGE);
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
}
