package com.translate.dataaccess;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.translate.domain.Translation;

@Stateless
public class TranslationDataAccessImpl implements TranslationDataAccess{

	private Logger logger = Logger.getLogger(TranslationDataAccessImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Translation> getAllTranslationsDAO() {	
		
		Query q = em.createQuery("SELECT t FROM Translation t");			
		@SuppressWarnings("unchecked")
		List<Translation> tempList = q.getResultList();
			
		return tempList;
	}
	
	public void createTranslationDAO(Translation translation){
		em.persist(translation);		
	}
	
	public Translation getTranslationbyId(int id){
		Translation translation = em.find(Translation.class, id);
		return translation;
	}
	
	public void updateTranslationDAO(Translation translation){				
		
		translation.setLastUpdated(new Date());	
				
		Translation currentPersistedState = em.find(Translation.class, translation.getId());
		
		logger.debug("In updateTranslationDAO() currentPersistedState BEFORE translation: " + translation.toString());
		logger.debug("In updateTranslationDAO() currentPersistedState BEFORE: " + currentPersistedState.toString());
		
		//Loop over the fields that we need to update and Sync, this will keep nulling where not needed. 		
		try {
			Class<?> currentPersistedClass = Class.forName(currentPersistedState.getClass().getName());				
			for (String s : translation.getParamsSetToUpdate()){	
				
				for(Method method : currentPersistedClass.getDeclaredMethods()){									
					if(("set"+s).equals(method.getName())){						
						method.setAccessible(true);
						Method getterMethod = currentPersistedClass.getMethod("get"+s);						
						Object valueObject = getterMethod.invoke(translation, (Object[]) null);								
						/*if(getterMethod.getReturnType().equals(String.class) && valueObject.equals(null)){
								logger.debug("In updateTranslationDAO() STRING valueObject NULL NULL: ");
								//method.invoke(currentPersistedState, "");
								//logger.debug("In updateTranslationDAO() STRING METHOD BEING SET: " + method.getName());							
						}
						else if(getterMethod.getReturnType().equals(String.class)){
							logger.debug("In updateTranslationDAO() STRING valueObject JUST STRING CLASS: ");
							//method.invoke(currentPersistedState, valueObject);
							//logger.debug("In updateTranslationDAO() STRING METHOD BEING SET: " + method.getName());
						}
						else{*/
							method.invoke(currentPersistedState, valueObject);
							logger.debug("In updateTranslationDAO() METHOD BEING SET: " + method.getName());
						/*}	*/						
					}
				}
			}	
		} 		
		catch (Exception e) {			
			e.printStackTrace();
			
		}
		
		logger.debug("In updateTranslationDAO() currentPersistedState AFTER: " + currentPersistedState.toString());
		
		//Finally merge the new and old values to Persistence		
		em.merge(currentPersistedState);
		
	}

	@Override
	public Translation getTranslationById(int id) {		
		return em.find(Translation.class, id);
	} 
	
}

