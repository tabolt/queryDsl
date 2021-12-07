package com.example.demo.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaRepositories(
        basePackages = {"com.example.demo"}
)
@Configuration
public class JpaConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6SpyCustomFormatStrategy.class.getName());
    }

    /**
     * 커스텀 SQL 포멧
     */
    public static class P6SpyCustomFormatStrategy implements MessageFormattingStrategy {

        private final boolean formatSql = false;

        @Override
        public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
            return category + " | " + elapsed + "ms | connection " + connectionId + " | " + this.formatSql(category, sql);
        }

        private String formatSql(String category, String sql) {

            if (formatSql) {

                if (sql == null || sql.trim().equals("")) {
                    return sql;
                }

                if (Category.STATEMENT.getName().equals(category)) {
                    if (sql.startsWith("create") || sql.startsWith("alter") || sql.startsWith("comment")) {
                        sql = FormatStyle.DDL.getFormatter().format(sql);
                    } else {
                        sql = FormatStyle.BASIC.getFormatter().format(sql);
                    }
                }
            }

            return sql;
        }
    }
}
