package com.kasakad.fileio.kasakaidfileio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

// @TestComponent は無意味なアノテーションなのでなくす
//@TestComponent
@Component
@Slf4j
public class MyResource extends ExternalResource {

    /** ApplicationContext */
    @Autowired
    protected ApplicationContext context;

    /** データソース */
    @Resource
    private TransactionAwareDataSourceProxy masterDataSource;

    @Autowired
    private Environment environment;

    /** CSVデータ格納ディレクトリ */
    private static final String CSV_DIRECTORY = "src/test/resources/testData/";

    @Override
    public void before() {
        log.info(this.getClass().getName() + "before start.");
    }
    @Override
    public void after() {
        log.info(this.getClass().getName() + "after start");
    }

    /**
     * テストデータ投入メソッド
     *
     * @param testData テストデータ
     * @throws Exception 例外
     */
    public void insertData(String... testData) throws Exception {
        DatabaseConnection dbConn = null;
        try {
            // DatabaseConnectionの作成
            dbConn = openDbConn();
            for (String data : testData) {
                // データセットの取得
                CsvDataSet dataSet = new CsvDataSet(new File(CSV_DIRECTORY + data));
                // セットアップ実行
                DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
            }
        } finally {
            // DatabaseConnectionの破棄
            closeDbConn(dbConn);
        }
    }


    private static final String TRUNCATE_DIRECTORY = "/truncate";

    /**
     * テストデータ削除メソッド
     *
     * @throws Exception 例外
     */
    @SneakyThrows
    public void truncateTable() throws Exception {
        DatabaseConnection dbConn = openDbConn();
        executeStatement(dbConn, truncateReference("FALSE"));
        try {
            // データセットの取得
            CsvDataSet dataSet = new CsvDataSet(new File(CSV_DIRECTORY + TRUNCATE_DIRECTORY));
            DatabaseOperation.TRUNCATE_TABLE.execute(dbConn, dataSet);
        } finally {
            // DatabaseConnectionの破棄
            executeStatement(dbConn, truncateReference("TRUE"));
            closeDbConn(dbConn);
        }
    }

    private boolean isMySQL() {
        return environment.getProperty("spring.datasource.url").contains("mysql");
    }

    private String truncateReference(String value) {
        return isMySQL() ? "set foreign_key_checks = " + value + ";" : "SET REFERENTIAL_INTEGRITY " + value + ";";
    }

    public DatabaseConnection openDbConn() throws DatabaseUnitException {
        DatabaseConnection databaseConnection = new DatabaseConnection(DataSourceUtils.getConnection(masterDataSource));
        DatabaseConfig config = databaseConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        return databaseConnection;
    }

    public void closeDbConn(DatabaseConnection dbConn) throws SQLException {
        if (dbConn != null) {
            dbConn.close();
        }
    }

    @SneakyThrows
    private void executeStatement(DatabaseConnection dbConnection, String statement) {
        Statement st = dbConnection.getConnection().createStatement();
        st.execute(statement);
    }
}


