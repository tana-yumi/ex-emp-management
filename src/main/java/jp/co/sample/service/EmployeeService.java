package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を全件検索する業務処理を行う.
 * 
 * @author tanaamiyumi
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得する.
	 * 
	 * @return 従業員全件情報
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();

		return employeeList;
	}

	/**
	 * 従業員情報を取得する.
	 * 
	 * @param id 従業員ID
	 * @return 検索された従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}

	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
}
