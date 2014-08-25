package com.translate.services;

import java.util.List;

import javax.ejb.Remote;

import com.translate.domain.Base;

@Remote
public interface BaseManagerServiceRemote {
	public List<Base> getAllBases();
	public void createBase(Base base);
	public Base getBaseById(int id);
	public void updateBase(Base base);
	public void removeBaseById(int id);
}
