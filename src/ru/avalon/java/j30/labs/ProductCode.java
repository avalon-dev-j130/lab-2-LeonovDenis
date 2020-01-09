package ru.avalon.java.j30.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Класс описывает представление о коде товара и отражает соответствующую
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {

    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;

    /**
     * Основной конструктор типа {@link ProductCode}
     *
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }

    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) throws SQLException {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        code = set.getString("PROD_CODE");
        discountCode = set.getString("DISCOUNT_CODE").charAt(0);
        description = set.getString("DESCRIPTION");

    }

    /**
     * Возвращает код товара
     *
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * Устанавливает код товара
     *
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Возвращает код скидки
     *
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }

    /**
     * Устанавливает код скидки
     *
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Возвращает описание
     *
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Хеш-функция типа {@link ProductCode}.
     *
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        return Objects.hash(code, description, discountCode);
    }

    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа
     * {@link ProductCode}
     *
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductCode other = (ProductCode) obj;
        if (!Objects.equals(this.code, other.code)
                || !Objects.equals(this.description, other.description)
                || !Objects.equals(this.discountCode, other.discountCode)) {
            return false;
        }
        return true;
    }

    /**
     * Возвращает строковое представление кода товара.
     *
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return "Product: " + code + " | with discount: "
                + discountCode + " | description: " + description;
    }

    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE базы
     * данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #09 Реализуйте метод getSelectQuery
         */
        String sql = getQuery("getSelectQuery");
        return connection.prepareStatement(sql);
    }

    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE базы данных
     * Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        String sql = getQuery("getInsertQuery");
        return connection.prepareStatement(sql);
    }

    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        String sql = getQuery("getUpdateQuery");
        return connection.prepareStatement(sql);
    }

    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа
     * {@link ProductCode}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        Collection<ProductCode> col = new LinkedList<>();
        while (set.next()) {
            ProductCode prod = new ProductCode(set);
            col.add(prod);
        }
        return new ArrayList<>(col);
    }

    /**
     * Сохраняет текущий объект в базе данных.
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа
     * UPDATE.
     *
     * @param connection действительное соединение с базой данных
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public void save(Connection connection) throws SQLException, IOException {
        /*
         * TODO #13 Реализуйте метод convert
         */
        //если нет записи то добавить
        Collection<ProductCode> allPro = all(connection);
        if (allPro.contains(this)) {
            try (PreparedStatement statement = getUpdateQuery(connection)) {
                statement.setString(1, String.valueOf(getDiscountCode()));
                statement.setString(2, getDescription());
                statement.setString(3, getCode());
                statement.executeUpdate();
                return;
            }
        }
        try (PreparedStatement statement = getInsertQuery(connection)) {
            statement.setString(1, getCode());
            statement.setString(2, String.valueOf(getDiscountCode()));
            statement.setString(3, getDescription());
            statement.executeUpdate();
        }
    }

    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов типа
     * {@link ProductCode}
     *
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     * @throws java.io.IOException
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException, IOException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }

    /**
     * Возвращает текст запроса из файла sql
     *
     * @param path наименование файла sql
     * @return текст запроса
     * @throws IOException
     */
    public static String getQuery(String path) throws IOException {
        path = "/resource/" + path + ".sql"; //путь к файлу sql запроса
        try (InputStream stream = ProductCode.class.getResourceAsStream(path)) {//создаём поток из файла
            try (Reader reader = new InputStreamReader(stream)) {
                try (BufferedReader buff = new BufferedReader(reader)) {
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = buff.readLine()) != null) {//чтаем всё из файла построчно
                        builder.append(line);
                    }
                    return builder.toString();
                }
            }
        }
    }
}
