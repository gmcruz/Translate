package com.translate.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Base;

@Local
public interface BaseDataAccess {
	public List<Base> getAllBasesDAO();
	public void createBaseDAO(Base base);
	public Base getBaseDAOById(int id);
	public void updateBaseDAO(Base base);
	public void deleteBaseByIdDAO(int id);	
}
