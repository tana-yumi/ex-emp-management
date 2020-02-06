package jp.co.sample.form;

/**
 * 従業員更新時に使用するフォーム.
 * 
 * @author tanaamiyumi
 *
 */
public class UpdateEmployeeForm {

	/** id*/
	private String id;
	/** d扶養家族数*/
	private String dependentsCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

	

}
