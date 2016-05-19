package kr.co.davizn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.davizn.model.EmpDto;
import kr.co.davizn.service.EmpService;

@RestController
@RequestMapping("/emp/")
public class EmpController {
	
	@Autowired
	private EmpService empservice;

	// 사원 등록 view
	@RequestMapping("empCreate.dvn")
	public String goCreate() {
		return "emp.empCreate";
	}
	
	// 사원 등록 action
	@RequestMapping("empCreateAction.dvn")
	public String goCreateAction(EmpDto dto) {
		String result = empservice.insert(dto);
		return result;
	}

	// 사원 삭제
	@RequestMapping("empDelete.dvn")
	public String goDelete(@RequestParam int empno) {
		String result = empservice.delete(empno);
		return result;
	}

		// 사원 정보 수정 view
	@RequestMapping("empModifiedView.dvn")
	public String goModifiedView(@RequestParam int empno,Model model) {
		EmpDto emp = empservice.getEmp(empno);
		model.addAttribute("emp",emp);
		return "emp.empModified";
	}
	
	// 사원 정보 수정 action
	@RequestMapping("empModified.dvn")
	public String goModified(EmpDto dto, Model model) {
		String result= empservice.edit(dto);
		return result;
	}

	/*// 전체 리스트
	@RequestMapping("empList.dvn")
	public String goList(Model model) {
		List<EmpDto> list = empservice.getEmpList();
		model.addAttribute("list", list);
		return "emp.empList";
	}*/
	
	//json으로 받기!
	@RequestMapping("empList.dvn")
	public List<EmpDto> List(){
		
		return list;
	}

	// 검색 view
	@RequestMapping("empSearch.dvn")
	public String goSearch() {
		return "emp.empSearch2";
	}

	//검색1
	@RequestMapping("empSearchAction.dvn")
	public String goSearchAction(@RequestParam String column, @RequestParam String keyvalue, Model model) {
		List<EmpDto> list = empservice.getSearch1(column,keyvalue);
		model.addAttribute("list", list);
		return "emp.empSearch2";
	}

	//검색2
	@RequestMapping("empSearchAction2.dvn")
	public String goSearchAction2(HttpServletRequest request, String keyvalue, Model model) {
		List<EmpDto> list = empservice.getSearch2(request,keyvalue);
		model.addAttribute("list", list);
		return "emp.empSearch2";
	}
	
	@RequestMapping(value="json.dvn", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> jsonSample(){
		
		System.out.println("aaaaaa");
		HashMap<String, Object> hsm = new HashMap<String, Object>();
		hsm.put("user_id", "naver");
		hsm.put("user_nm", "네이버");
		hsm.put("user_pw", 123);
		
		
		return hsm;
	}
}
