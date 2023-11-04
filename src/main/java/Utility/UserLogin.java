package Utility;

public class UserLogin {
	
	private String password;
	private String email;
	
	public UserLogin(String email, String password) {
		super();
		this.password = password;
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
