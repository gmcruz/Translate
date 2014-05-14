package com.translate;

import java.util.List;

import javax.ejb.Local;

import com.translate.domain.Base;

@Local
public interface BaseManagerServiceLocal {
	public List<Base> getAllBases();
	public void createBase(Base base);
	public Base getBaseById(int id);
	public void updateBase(Base base);
	public void removeBaseById(int id);	
}