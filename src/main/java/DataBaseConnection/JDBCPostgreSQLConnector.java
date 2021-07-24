package DataBaseConnection;

import java.sql.*;

public class JDBCPostgreSQLConnector {

    private static ConnectionPool connectionPool;

    public static void initConnection(){
        connectionPool = ConnectionPool.create();
    }

    public static void closeConnection() throws SQLException {
        connectionPool.shutdown();
    }



    public static int insert(String sql, String exception) throws SQLException {
        Connection c = null;
        //initConnection();
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(c != null)
                connectionPool.releaseConnection(c);
        }
        return -1;
    }

    public static boolean update(String sql, String exception) throws SQLException {
        Connection c = null;
        //initConnection();
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(c != null)
                connectionPool.releaseConnection(c);
        }
        return false;

    }

    public static ResultSet select(String sql, String exception) throws SQLException {
        Connection c = null;
        initConnection();
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(c != null)
                connectionPool.releaseConnection(c);
        }
        return null;
    }

    public static boolean delete(String sql, String exception) throws SQLException {
        Connection c = null;
        //initConnection();
        try{
            c = connectionPool.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(c != null)
                connectionPool.releaseConnection(c);
        }
        return false;
    }

}
