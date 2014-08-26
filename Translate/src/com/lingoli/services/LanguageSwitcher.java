package com.lingoli.services;

import java.io.Serializable;
import java.util.Locale;

import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



/**
 * Used for managing i18n in application for each user
 * @author Krzysztof Grajek
 *
 */
@ManagedBean
@SessionScoped
public class LanguageSwitcher implements Serializable {
   
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LanguageSwitcher.class);
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    /**
     * Sets the current {@code Locale} for each user session
     * 
     * @param languageCode - ISO-639 language code
     */
    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    
}