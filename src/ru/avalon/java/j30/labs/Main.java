package ru.avalon.java.j30.labs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle
 * Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {

    private static final Properties CONFIG = new Properties();
    private static final String RESOURCE = "/resource/url.properties";

    /**
     * Точка входа в приложение
     *
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */

        try (Connection connection = getConnection()) {
            printAllCodes(connection);

            ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);

            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);
        }
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }

    /**
     * Выводит в кодсоль все коды товаров
     *
     * @param connection действительное соединение с базой данных
     * @throws SQLException
     * @throws IOException
     */
    private static void printAllCodes(Connection connection) throws SQLException, IOException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        codes.forEach((code) -> {
            System.out.println(code);
        });
        System.out.println("=====");
    }

    /**
     * Возвращает URL, описывающий месторасположение базы данных
     *
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {
        /*
         * TODO #02 Реализуйте метод getUrl
         */
        return CONFIG.getProperty("db.driver") + "://"
                + CONFIG.getProperty("db.host") + ":"
                + CONFIG.getProperty("db.port") + "/"
                + CONFIG.getProperty("db.name");
    }

    /**
     * Возвращает параметры соединения
     *
     * @return Объект класса {@link Properties}, содержащий параметры user и
     * password
     */
    private static Properties getProperties() {

        /*
         * TODO #03 Реализуйте метод getProperties
         */
        try (InputStream stream = Main.class.getResourceAsStream(RESOURCE)) {
            CONFIG.load(stream);
        } catch (IOException e) {
            System.err.println("Properties not found");
        }
        return CONFIG;
    }

    /**
     * Возвращает соединение с базой данных Sample
     *
     * @return объект типа {@link Connection}
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        /*
         * TODO #04 Реализуйте метод getConnection
         */
        getProperties();
        String url = getUrl();
        String user = CONFIG.getProperty("db.user");
        String password = CONFIG.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

}
