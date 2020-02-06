package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を行うコントローラー.
 * @author tanaamiyumi
 *
 */
@Controller
@RequestMapping
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
   /**
    * InsertAdministratorFormをインスタンス化してreturnする.
    * @return
    */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return  insertAdministratorForm;
	}
	
	/**
	 * 管理者情報登録画面に遷移する.
	 * @return 管理者情報登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	/**
	 * ログインする際、リクエストパラメーターが格納されるLoginFormオブジェクトがリクエストスコープに自動的に格納されるメソッド
	 * @return　LoginForm
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		
		return new LoginForm();
	}
	/**
	 * administrator/login.htmlにフォワード
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	@Autowired
	private HttpSession session;
		
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		
		if( administrator == null) {
			model.addAttribute("erros", "メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}else {
			session.setAttribute("administratorName", administrator.getName());
			
			return "forward:/employee/showList";
		}
	}
}
