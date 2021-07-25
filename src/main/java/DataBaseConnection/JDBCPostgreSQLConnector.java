package DataBaseConnection;

import CustomException.ExceptionHandler;

import java.sql.*;

public class JDBCPostgreSQLConnector {

    private static ConnectionPool connectionPool;

    public static void initConnection(){
        connectionPool = ConnectionPool.create();
    }

    public static void closeConnection() throws SQLException {
        connectionPool.shutdown();
    }



    public static int insert(String sql, String exception) throws  ExceptionHandler {
        Connection c = null;
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (SQLException e){
            throw new ExceptionHandler("The class in which the error was flown: " + JDBCPostgreSQLConnector.class
                    + exception, e);
        }finally {
            if(c != null)
                connectionPool.closeConnection(c);
        }
        return -1;
    }

    public static boolean update(String sql, String exception) throws  ExceptionHandler {
        Connection c = null;
        boolean check;
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            check = preparedStatement.execute();
            if(!check){
                return true;
            }
        }catch (SQLException e){
            throw new ExceptionHandler("The class in which the error was flown: " + JDBCPostgreSQLConnector.class
                    + exception, e);
        }finally {
            if(c != null)
                connectionPool.closeConnection(c);
        }
        return false;

    }

    public static ResultSet select(String sql, String exception) throws ExceptionHandler {
        Connection c = null;
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
        }catch (SQLException e){
            throw new ExceptionHandler("The class in which the error was flown: " + JDBCPostgreSQLConnector.class
                    + exception, e);
        }finally {
            if(c != null)
                connectionPool.closeConnection(c);
        }
        return null;
    }

    public static boolean delete(String sql, String exception) throws ExceptionHandler {
        Connection c = null;
        boolean check;
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            check = preparedStatement.execute();
            if(!check){
                return true;
            }
        }catch (SQLException e){
            throw new ExceptionHandler("The class in which the error was flown: " + JDBCPostgreSQLConnector.class
                    + exception, e);
        }finally {
            if(c != null)
                connectionPool.closeConnection(c);
        }
        return false;
    }

    public static ResultSet selectAll(String sql, String exception) throws ExceptionHandler{
        Connection c = null;
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
        }catch (SQLException e){
            throw new ExceptionHandler("The class in which the error was flown: " + JDBCPostgreSQLConnector.class
                    + exception, e);
        }finally {
            if(c != null)
                connectionPool.closeConnection(c);
        }
        return null;
    }

}
