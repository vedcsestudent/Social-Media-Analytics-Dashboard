package com.socialyzer.dao;
import java.util.ArrayList;

public interface IDao<T> {
	
	T findOne(String id) throws Exception;

}
