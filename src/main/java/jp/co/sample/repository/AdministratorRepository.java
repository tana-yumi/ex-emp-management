package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するレポジトリ.
 * @author tanaamiyumi
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		
		return administrator;
	};
	
	/**
	 * 管理者情報を挿入するメソッド.
	 * @param administrator　管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する.
	 * @param mailAddress　メールアドレス
	 * @param password　パスワード
	 * @return　管理者情報（存在しない場合はnullを返す）
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		String sql = "SELECT id,name, mail_address, password FROM administrators WHERE mail_address=:mailAddress AND password=:password;";
		
		List<Administrator> administratorlist = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorlist.size()==0) {
			return null;
		}
		return administratorlist.get(0);
		
//		if(mailAddress !=null && password !=null) {
//		
//		//Administrator administrator = new Administrator();
//		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
//		return administrator;
//		
//		}else {
//		return null;
//		}
	}
}
