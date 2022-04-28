package orm;

import anotations.Column;
import anotations.Entity;
import anotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(Object entity) throws IllegalAccessException, SQLException {
        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object value = idColumn.get(entity);

        if (value == null || (long) value <= 0) {
            return doInsert(entity, idColumn);
        }
        return doUpdate(entity, idColumn);
    }

    private boolean doInsert(Object entity, Field primaryKey) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        String tableFields = getColumnWithoutId(entity.getClass());
        String tableValues = getColumnsValuesWithoutId(entity.getClass());

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)"
                , tableName, tableFields, tableFields);

        return connection.prepareStatement(insertQuery).execute();

    }

    private String getColumnsValuesWithoutId(Class<?> aClass) throws IllegalAccessException {
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(aClass);

            values.add(o.toString());
        }
        return String.join(", ", values);
    }

    private String getColumnWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.joining(", "));
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationByType[0].name();
    }

    @Override
    public Iterable<E> find(Class table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class table) {
        return null;
    }

    @Override
    public E findFirst(Class table, String where) {
        return null;
    }

    private Field getIdColumn(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException(
                                "Entity does not have primary key"));
    }
}
