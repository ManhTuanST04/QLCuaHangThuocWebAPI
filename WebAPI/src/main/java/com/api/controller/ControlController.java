package com.api.controller;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DAO.ControlDAO;
import com.api.model.*;

@RestController
public class ControlController {
	@Autowired
	ControlDAO controlDAO;
	
	@GetMapping(value = "/api/control/getallcontrol")
	public List<ControlModel> GetAllControl(){
		List<ControlModel> lst = controlDAO.GetAllControl();
		return lst;
	}
	
	@GetMapping(value = "/api/control/getcontrolper")
	public List<ControlModel> GetControlPer(int perId){
		List<ControlModel> lst = controlDAO.GetControlPer(perId);
		return lst;
	}
}
