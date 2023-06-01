import AllVar.getConnection
import java.io.ByteArrayOutputStream
import java.sql.*

var connection : Connection? = null
var statement: Statement? = null

fun main(args: Array<String>) {
    // Try adding program argument2s via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    connectServer()
    val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `member` (\n" +
            "  `num` int not null auto_increment,\n" +
            "  `id` char(20) not null,\n" +
            "  `name` char(20) not null,\n" +
            "  `gender` char(1),\n" +
            "  `post_num` char(8),\n" +
            "  `address` char(80),\n" +
            "  `tel` char(20),\n" +
            "  `age` int,\n" +
            "  PRIMARY KEY (`num`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;"
    val pstmt: PreparedStatement = connection?.prepareStatement(CREATE_TABLE_SQL)!!
    var rs = pstmt.executeQuery()
    rs.close()
    pstmt.close()
    updateQuery("tel 0111-202 etoi3")
    getResult("member")
}

fun getResult(tableName :String,){
    var list : ArrayList<String> = ArrayList()
    val result: ResultSet = statement?.executeQuery("SELECT * FROM ${tableName} order by age")!!
    while (result.next()) {
        var str = ""
        for(i in 1..7){
            str+=result.getString(i) + " "
        }
        println(str)
    }
}
private fun updateQuery(info : String){
    var list = info.split(" ")
    var query = "update member set ${list[0]}='${list[1]}' where name='${list[2]}'"
    var sr = SeriallizeObject(connection!!)
    sr.writeJavaObject(query)
}


private fun shootQuery(info : String){
    var list = info.split(" ")
    var query = "insert into member (id, name, gender, post_num, " +
            "address, tel, age) " +
            "VALUES('${list[0]}','${list[1]}','${list[2]}','${list[3]}'," +
            "'${list[4]}', '${list[5]}', ${list[6]})"
    var sr = SeriallizeObject(connection!!)
    sr.writeJavaObject(query)
}


private fun closeServer(){
    connection?.close()
    statement?.close()
}

fun connectServer(){
    try {
        openConnection()
        statement = connection?.createStatement()!!
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    } catch (e: SQLException) {
        println("Sever Is Not Running")
        e.printStackTrace()
    }
}

@Throws(SQLException::class, ClassNotFoundException::class)
fun openConnection() {
    if (connection != null && !connection?.isClosed!!) {
        return
    }
    connection = DriverManager.getConnection(
        "jdbc:mariadb://" + AllVar.host + "/" + AllVar.database,
        AllVar.username,
        AllVar.password
    )
}
