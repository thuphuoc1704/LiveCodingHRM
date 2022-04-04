package common;
import com.github.javafaker.Faker;
public class DataHelper {
	private Faker faker;
	public static DataHelper getData() {
		return new DataHelper();	
	}
	public DataHelper(){
		faker=new Faker();
	}
	public String getFirstName( ) {
		return faker.address().firstName();
		
	}
	public String getLastName( ) {
		return faker.address().lastName();
	}
	public String getFullName( ) {
		return faker.address().firstName()+faker.address().lastName();
	}
	public String getPassWord( ) {
		return faker.internet().password();
	}
}
