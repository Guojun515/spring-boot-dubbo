package org.guojun.data.provider.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @EnableTransactionManagement 相当于<tx:annotation-driven />
 * @Description 数据库相关的配置（阿里数据库连接池 +mybatis+tk.mapper)
 * @author Guojun
 * @Date 2018年6月3日 上午10:08:04
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DatabaseConfig {
	
	//@Bean方法中参数传入其他Bean作为参数，spring会自动注入
	//直接调用@Bean注解的方法，spring会自动注入该方法生成的Bean
	
	/**
	 * 数据库连接池
	 * @param constantConfig 框架会为我们自动注入
	 * @return
	 */
	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
	public DruidDataSource druidDataSource(ConstantConfig constantConfig) {
		DruidDataSource dataSource = new DruidDataSource();
		//数据库用户名称
		dataSource.setUsername(constantConfig.getJdbcUserName());
		//数据库密码
		dataSource.setPassword(constantConfig.getJdbcPassword());
		//驱动名称
		dataSource.setUrl(constantConfig.getJdbcUrl());
		//JDBC连接串
		dataSource.setDriverClassName(constantConfig.getJdbcDriverClassName());
		//连接池最大使用连接数量
		dataSource.setMaxActive(constantConfig.getJdbcMaxActive());
		//初始化大小
		dataSource.setInitialSize(constantConfig.getJdbcInitialSize());
		//获取连接最大等待时间
		dataSource.setMaxWait(constantConfig.getJdbcMaxWait());
		//连接池最小空闲
		dataSource.setMinIdle(constantConfig.getJdbcMinIdle());
		//超出出连接的检测时间间隔
		dataSource.setTimeBetweenEvictionRunsMillis(constantConfig.getJdbcTimeBetweenEvictionRunsMillis());
		//最小逐出时间
		dataSource.setMinEvictableIdleTimeMillis(constantConfig.getJdbcMinEvictableIdleTimeMillis());
		//测试有效用的SQL Query
		dataSource.setValidationQuery("SELECT 'x'");
		//连接空闲时测试是否有效
		dataSource.setTestWhileIdle(true);
		//获取连接时测试是否有效
		dataSource.setTestOnBorrow(false);
		//归还连接时是否测试有效
		dataSource.setTestOnReturn(false);
		
		return dataSource;
	}
	
	/**
	 * mybatis 的 sqlSessionFactory
	 * @param dataSource 自动注入，@Qualifier根据名字注入
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		//资源路径解析器
		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		
		//扫描Mapper.xml文件
		Resource[] mapperResources = resourcePatternResolver.getResources("classpath:/org/guojun/data/provide/application/mapper/**/*Mapper.xml");
		sessionFactoryBean.setMapperLocations(mapperResources);
		
		List<Interceptor> plugs = new ArrayList<>();
		//分页插件
		plugs.add(this.pageInterceptor());
		sessionFactoryBean.setPlugins(plugs.toArray(new Interceptor[plugs.size()]));
		
		//mybatis配置文件
		Resource configResource = resourcePatternResolver.getResourceLoader().getResource("classpath:config/mybatis/mybatis-setting.xml");
		sessionFactoryBean.setConfigLocation(configResource);;
		
		return sessionFactoryBean;
	}
	
	/**
	 * pageHelper 分页插件
	 * @return
	 */
	private PageInterceptor pageInterceptor() {
		Properties pageProperties = new Properties();
		pageProperties.setProperty("helperDialect", "mysql");
		pageProperties.setProperty("reasonable", "true");
		
		PageInterceptor pageInterceptor = new PageInterceptor();
		pageInterceptor.setProperties(pageProperties);
		return pageInterceptor;
	}
	
	/**
	 * spring sqlSessionTemplate
	 * @param sessionFactory 自动注入，@Qualifier根据名字注入
	 * @return
	 */
	@Bean
	public SqlSession sqlSession(@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactory) {
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sessionFactory);
		
		return sqlSession;
	}
	
	/**
	 * 通用Mapper配置
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSession");
		mapperScannerConfigurer.setBasePackage("org.guojun.data.provider.application.mapper");
		mapperScannerConfigurer.setMarkerInterface(Mapper.class);
		
		return mapperScannerConfigurer;
	}
	
	/**
	 * spirng 事务管理器
	 * @param dataSource 自动注入，@Qualifier根据名字注入
	 * @return
	 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
