package cn.pyq.util;

import cn.pyq.model.Vo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-08 21:19
 */
public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		String token = (String) session.getAttribute("token");
		MockDataBase.tokenSet.remove(token);
		List<Vo> voList = MockDataBase.client_info.remove(token);
		try {
			for (Vo vo : voList) {
				HttpUtil.sendHttpRequest(vo.getLogOutUrl(),vo.getJSessionId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

