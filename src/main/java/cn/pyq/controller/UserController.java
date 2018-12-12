package cn.pyq.controller;

import cn.pyq.model.User;
import cn.pyq.model.Vo;
import cn.pyq.util.MockDataBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@RequestMapping("checkLogin")
	public String checkLogin(@RequestParam String redirectUrl, Model model) {
		String token = (String) session.getAttribute("token");
		//没有全局token
		if (StringUtils.isEmpty(token)) {
			session.setAttribute("redirectUrl", redirectUrl);
			return "redirect:/login.jsp";
		}
		//有全局token
		model.addAttribute("token", token);
		return "redirect:" + redirectUrl;
	}

	@RequestMapping("login")
	public String login(User user, String redirectUrl, Model model) {
		if (user.getUsername().equals("pyq") && user.getPassword().equals("123")) {
			String token= String.valueOf(System.nanoTime());
			MockDataBase.tokenSet.add(token);
			session.setAttribute("token",token);
			model.addAttribute("token", token);
			return "redirect:" + redirectUrl;
		}
		session.setAttribute("errorMsg", "Invalid Username or Password!");
		return "redirect:/login.jsp";
	}

	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String token, String logOutUrl, String jSessionId) {
		System.out.println("开始验证!");
		System.out.println(token);
		System.out.println(logOutUrl);
		System.out.println(jSessionId);
		if (MockDataBase.tokenSet.contains(token)) {
			System.out.println("存在此Token");
			List<Vo> voList = MockDataBase.client_info.get(token);
			if (null == voList) {
				voList = new ArrayList<>();
				MockDataBase.client_info.put(token, voList);
			}
			Vo vo = new Vo(logOutUrl, jSessionId);
			voList.add(vo);
			for (Map.Entry<String, List<Vo>> stringListEntry : MockDataBase.client_info.entrySet()) {
				System.out.println(stringListEntry);
			}
			return "true";
		}
		return "false";
	}
	@RequestMapping("logOut")
	public String logOut(){
		session.invalidate();
		return "redirect:/exit.jsp";
	}
}

