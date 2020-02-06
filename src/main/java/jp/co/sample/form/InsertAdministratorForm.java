package jp.co.sample.form;

/**
 * 管理者登録時に使用するフォーム.
 * @author tanaamiyumi
 *
 */
public class InsertAdministratorForm {

	/** name:名前*/
	private String name;
	
	/**mailaddressメールアドレス*/
	private String mailAddress;
	
	/**pssword パスワード*/
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
	
	
}
