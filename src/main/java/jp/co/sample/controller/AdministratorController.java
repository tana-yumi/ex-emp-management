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
    * @return　管理者登録時に使用するフォームに遷移する
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
	
	/**
	 * 管理者情報を登録する。
	 * @param form　管理者登録時に使用するフォーム
	 * @return　管理者情報登録画面にリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	/**
	 * ログインする際、リクエストパラメーターが格納されるLoginFormオブジェクトがリクエストスコープに自動的に格納されるメソッド
	 * @return　LoginForm　リクエストパラメーターが格納されるLoginFormオブジェクトを生成
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		
		return new LoginForm();
	}
	/**
	 * administrator/login.htmlにフォワード
	 * @return　ログイン画面に遷移
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * sessionスコープの定義.
	 */
	@Autowired
	private HttpSession session;
		
	/**
	 * ログイン失敗の際と成功の際のメソッド.
	 * @param form　ログイン時に使用するフォームの引数を使用
	 * @param model　エラーメッセージを格納しているリクエストスコープ
	 * @return　ログイン失敗時はエラーメッセージ表示（画面はログイン画面のまま）。成功時は従業員情報一覧ページにフォワード
	 */
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
