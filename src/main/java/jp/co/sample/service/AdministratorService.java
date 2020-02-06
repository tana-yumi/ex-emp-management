package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者関連機能の業務処理を行うサービス.
 * @author tanaamiyumi
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	/**
	 * 管理者情報を挿入.
	 * administratorRepositoryのinsertメソッドを呼ぶ
	 */
	@Autowired
	private AdministratorRepository administratorRepository;
	
	public void insert(Administrator administrator) {
		
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理を行う
	 * @param mailAddress
	 * @param password
	 * @return　戻ってきた管理者情報をそのまま呼び出し元に返す。
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
		 
		
	}
}
