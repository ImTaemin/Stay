package stay.project.sist;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value="stay.data.*", sqlSessionFactoryRef = "mongoFactory")
public class MongoDbDataSourceConfig {

	@Primary
	@Bean(name="datasource-mongo")
	@ConfigurationProperties(prefix="spring.datasource.db-mongo")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build(); 
	}
	
	@Primary
	@Bean(name="mongoFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource-mongo") DataSource dataSource) throws Exception {
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
