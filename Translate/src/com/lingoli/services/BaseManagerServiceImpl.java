package com.lingoli.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lingoli.dataaccess.BaseDataAccess;
import com.lingoli.domain.Base;

@Stateless
public class BaseManagerServiceImpl implements BaseManagerServiceLocal, BaseManagerServiceRemote {
	
	@EJB
	BaseDataAccess baseDAO;
	
	@Override
	public List<Base> getAllBases() {
		return baseDAO.getAllBasesDAO();
	}

	@Override
	public void createBase(Base base) {
		baseDAO.createBaseDAO(base);
	}

	@Override
	public Base getBaseById(int id) {
		return baseDAO.getBaseDAOById(id);
	}
	
	@Override
	public void updateBase(Base base){
		baseDAO.updateBaseDAO(base);
	}

	@Override
	public void removeBaseById(int id) {
		baseDAO.deleteBaseByIdDAO(id);		
	}

}
