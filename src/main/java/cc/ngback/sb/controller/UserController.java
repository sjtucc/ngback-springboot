package cc.ngback.sb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.ngback.sb.pojo.User;
import cc.ngback.sb.util.JsonResult;

@RestController
public class UserController {
	@RequestMapping(value="/user/list", method = RequestMethod.GET)
	public JsonResult listUser() {
		List<User> users= new ArrayList<User>();
		users.add(new User("a", "1"));
		users.add(new User("b", "2"));
		return JsonResult.build(200, "ok", users);
	}	
}
