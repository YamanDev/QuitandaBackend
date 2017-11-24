package br.com.yaman.quitanda.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.yaman.quitanda.business.GenericCrudBusiness;
import br.com.yaman.quitanda.wrapper.WrapperJsonObject;


public abstract class CrudControllerBase<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudControllerBase.class);
    public static final String JSON = "application/json";
    
    public abstract GenericCrudBusiness<T> getBusinessClass();

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<T> pageLoad() {
    	try {
    		return getBusinessClass().findAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return new ArrayList<T>();
		}
        
    }
  
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public T save(@RequestBody WrapperJsonObject<T> t) {
        return getBusinessClass().save(t.getT());
    }

    @RequestMapping(value = "/find-one", method = RequestMethod.GET)
    public T findOne(@RequestParam Integer id) {
        return getBusinessClass().findOne(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody WrapperJsonObject<T> t) {
    	getBusinessClass().delete(t.getT());
    }

    public Type getClassType() {
        Type mySuperclass = this.getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        return tType;
    }
    
    private Gson getNewGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
				.create();
	}
    
}
