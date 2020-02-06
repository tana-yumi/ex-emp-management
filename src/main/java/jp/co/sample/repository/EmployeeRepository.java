package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;


/**
 * 従業員情報を表すドメインクラス.
 * employeesテーブルに対応
 * @author tanaamiyumi
 *
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDepondentsCount(rs.getInt("depondentsCount"));
	 
		return employee;
	};
	
	/**
	 * 従業員一覧情報を入社日順で取得する.
	 * @return
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone,salary,characteristics,depondentsCount "
				+ "FROM employees ORDER BY hireDate;";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		return employeeList;
	}

	/**
	 * 主キーから従業員情報を取得する.
	 * @param id
	 * @return
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone,salary,characteristics,depondentsCount FROM employees WEHER id = :id;";
		
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}

	/**
	 * 従業員情報を変更する.
	 * @param employee
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET id=:id, name=:name, image=:image, gender=:gender, hireDate=:hireDate, mailAddress=:mailAddress, zipCode=:zipCode, address=:address, telephone=:telephone,salary=:salary,characteristics=:characteristics,depondentsCount=:depondentsCount WHERE id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
	}
	
}