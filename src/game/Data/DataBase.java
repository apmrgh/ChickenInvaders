package game.Data;

public class DataBase {
}



//    synchronized public void savedb() {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrgh", "root", "");
//            Statement statement = connection.createStatement();
//
//            String tableName = "mydatabases";
//            if (!connection.getMetaData().getTables(null, null, tableName, null).next()) {
//                statement.executeUpdate("create table " + tableName + " (serializedCoinList varchar(1023),chickenGroupSize int,serializedPowerUpList varchar(1023),serializedEggList varchar(1023),spacCraftSize int,serializedBoss varchar(2047),numberPlayers int,hasWarning boolean,name varchar(255),gameTime int,play boolean)");
//            }
//            statement.executeUpdate("delete from " + tableName);
//            for (Database database : databaseList) {
//                statement.executeUpdate("insert into " + tableName + " values(" + "'" + database.serializeListToJson(database.coinlist).toString() + "'," + database.chickenGroup.size()+ "," + "'" + database.serializeListToJson(database.powerUpList).toString() + "'," + "'" + database.serializeListToJson(database.eggList).toString() + "',"   + database.spacecrafts.size() + "," + "'"  + database.serializeListToJson(database.bosses).toString() + "'," + database.numberPlayers + "," + database.hasWarning + "," + "'" + database.name + "'," + database.gameTime + "," + database.play + ")");
//            }
//
//            tableName = "myPlayers";
//            if (!connection.getMetaData().getTables(null, null, tableName, null).next()) {
//                statement.executeUpdate("create table " + tableName + " (name varchar(1023), coin int, chickenLifeTaken int, numberOfLife int, missiel int, level int, stage int)");
//            }
//            statement.executeUpdate("delete from " + tableName);
//            for (SpaceShip s : playerSpacecrafts) {
//                statement.executeUpdate("insert into " + tableName + " values(" + "'" + s.name + "'," + s.coin+ "," + s.chickenLifeTaken + "," + s.numberOfLife + "," + s.missile + "," + s.level + "," + s.stage + ")");
//            }
//
//            connection.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Database does not exist");
//        }
//
//
//    }
//
//    synchronized public void loadDataDB() {
//
//
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pour", "root", "");
//            Statement statement = connection.createStatement();
//
//            String tableName = "myPlayers";
//            ResultSet resultSet = statement.executeQuery("select * from myPlayers");
//            playerSpacecrafts = new ArrayList<>();
//            while (resultSet.next()){
//
//                SpaceShip spaceShip =new SpaceShip(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
//                playerSpacecrafts.add(spaceShip);
//            }
//        }catch (Exception e){
//            System.out.println("Database does not exist");
//
//        }
//
//    }
