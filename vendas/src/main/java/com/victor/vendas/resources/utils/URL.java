package com.victor.vendas.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String palavra) {
		try {
			return URLDecoder.decode(palavra, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String list){
		String[] vetor = list.split(",");
		List<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < vetor.length; i++) {
			numbers.add(Integer.parseInt(vetor[i]));
		}
		return numbers;
	}

}
