package com.translate.backingbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;

import com.translate.domain.Translation;
import com.translate.services.TranslationManagerServiceLocal;

@ManagedBean(name="allTranslationsPageBean")
public class AllTranslationsPageBean {

	@EJB
	private TranslationManagerServiceLocal translationService;
	
	private UIData dataTable;
	
	public List<Translation> getTranslations(){		
		System.out.println("CALLED getTranslations in AllTranslationsPageBean.java");		
		return translationService.getAllTranslations();	
	}

	public UIData getDataTable() {
		return dataTable;
	}

	public void setDataTable(UIData dataTable) {
		this.dataTable = dataTable;
	}	
	
}
