package stay.project.sist;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value="stay.data.*", sqlSessionFactoryRef = "mySqlFactory")
public class MySqlDataSourceConfig {

	@Primary
	@Bean(name="datasource-mysql")
	@ConfigurationProperties(prefix="spring.datasource.db-mysql")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build(); 
	}
	
	@Primary
	@Bean(name="mySqlFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("stay.data.*");
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml"));
        return sqlSessionFactory.getObject();
    }
	
    @Primary
    @Bean(name = "sqlSession")
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
