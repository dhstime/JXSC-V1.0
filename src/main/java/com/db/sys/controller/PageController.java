package com.db.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {

	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}

	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	@RequestMapping("doPayUI")
	public String doPayUI(){
		return "common/pay";
	}
}
