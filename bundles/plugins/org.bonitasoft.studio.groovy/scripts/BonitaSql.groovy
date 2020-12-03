import java.sql.Driver;
import groovy.sql.Sql;

/**
 *
 * @deprecated This class will be removed in future Bonita version, connecting to a database from a script inside a process is not recommended.
 * Use a Database connector instead.
 * @since 7.12.0
 */
@Deprecated
public class BonitaSql {
	
	/**
	 * @deprecated This method will be removed in future Bonita version, connecting to a database from a script inside a process is not recommended.
     * Use a Database connector instead.
     * @since 7.12.0
	 * Creates a new Sql instance given a JDBC connection URL. 
	 * @param url a database url of the form jdbc:subprotocol:subname
	 * @param user
	 * @param password
	 * @param driver an instance of the java.sql.Driver to use
	 * @return  a new Sql instance with a connection
	 */
    @Deprecated
	public static Sql newInstance(String url,String user,String password,Driver driver){
		Properties p= new Properties()
		p.setProperty("user",user)
		p.setProperty("password",password)
		return new Sql(driver.connect(url, p))

	}
}
