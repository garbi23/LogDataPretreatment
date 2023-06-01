import AllVar.getConnection

import java.sql.*


class SeriallizeObject(conn: Connection) {
    private var WRITE_OBJECT_SQL = ""

    private var READ_OBJECT_SQL = ""

    private var conn : Connection = conn

    init {
        val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `friend` (\n" +
                "  `num` int not null auto_increment,\n" +
                "  `name` char(20) not null,\n" +
                "  `tel` char(20) not null,\n" +
                "  `address` char(8),\n" +
                "  PRIMARY KEY (`num`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;"
        val pstmt: PreparedStatement = connection?.prepareStatement(CREATE_TABLE_SQL)!!
        var rs = pstmt.executeQuery()
        rs.close()
        pstmt.close()
    }


    @Throws(Exception::class)
    fun writeJavaObject(query : String) {
        if(conn.isClosed){ conn = getConnection()!!}
        val pstmt: PreparedStatement = conn.prepareStatement(query)
        var rs = pstmt.executeQuery()
        rs.close()
        pstmt.close()
    }

}