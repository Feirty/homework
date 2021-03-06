package com.hzu.homework.sysback.school.service;

import java.util.List;

import com.hzu.homework.sysback.school.vo.SchoolModel;

public interface SchoolService {
	public String create(SchoolModel user);
	public String update(SchoolModel user);
	public String delete(SchoolModel user);
	public SchoolModel getByUuid(String uuid);
	public List<SchoolModel> getAll();
	public boolean checkSchoolName(String schoolName);
	public String getUuidByName(String schoolName);
}
