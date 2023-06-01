import java.sql.Connection
import java.sql.DriverManager

object AllVar {
    var driver : String = "org.mariadb.jdbc.Driver"
    var host : String = "127.0.0.1:3306"
    var database : String = "sample"
    var username : String = "user1"
    var password : String = "123456"

    fun getConnection() : Connection?{
        var connect : Connection? = null
        try {
            Class.forName(driver)
            connect = DriverManager.getConnection(
                "jdbc:mariadb://$host/$database",
                username,
                password
            )
            return connect
        }catch (e : Exception){
            print("DB 정보를 가져 올 수 없습니다!")
        }
        return null
    }
}