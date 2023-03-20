package com.thinktalentindia.CrudeProject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinktalentindia.CrudeProject.model.StudentData;
import com.thinktalentindia.CrudeProject.repository.StudentRepository;

@Component
public class StudentBo {

	public static final String ADD = "Successfully Added.";
	public static final String UPDATE = "Successfully Updated.";
	public static final String FAILED = "Failed.";
	public static final String DELETE = "Deleted.";
	public static final String NO_IDS = "could not found the employee with id-";

	@Autowired(required = true)
	private StudentRepository stdRepo;

	public Map<String, Object> addUpdate(StudentData p) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("message", FAILED);
		try {
			if (p.getId() == null) {
				rsMap.put("message", ADD);
			} else {
				rsMap.put("message", UPDATE);
			}
			stdRepo.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public List<StudentData> findAll() {
		List<StudentData> sts = null;
		try {
			sts = stdRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
	}

	public StudentData findById(Long id) {
		StudentData sm = new StudentData();
		try {
			Optional<StudentData> sts = stdRepo.findById(id);
			sm = getEmpFromOPEmp(id, sts);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sm;
	}

	private StudentData getEmpFromOPEmp(Long id, Optional<StudentData> emp) throws Exception {
		StudentData em;
		if (!emp.isPresent()) {
			throw new Exception(NO_IDS + id);
		} else {
			em = emp.get();
		}
		return em;
	}

	public Map<String, Object> remove(String ids) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("message", FAILED);

		try {
			if (ids != null && ids.length() > 0) {
				String idArr[] = ids.split(",");
				if (idArr.length > 0) {
					rsMap.put("message", DELETE);
				} else {
					rsMap.put("message", NO_IDS);
				}
				for (String id : idArr) {
					Long idl = Long.parseLong(id);
					Optional<StudentData> eOp = stdRepo.findById(idl);
					StudentData e = getEmpFromOPEmp(idl, eOp);
					stdRepo.delete(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
}