package br.com.zg.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import br.com.zg.base.exception.BaseException;

public class Menssagens {

	private static Properties properties;
	
	private static Properties valiMesseges;
	
	public static String getMenssagem(String key){
		if(properties == null){
			properties = new Properties();
			synchronized (properties) {
				try {
					properties.load(Menssagens.class.getResourceAsStream("/Messages.properties"));
					valiMesseges.load(Menssagens.class.getResourceAsStream("/ValidationMessages.properties"));
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
		}
		String msg = properties.getProperty(key);
		if(StringUtils.isBlank(msg)){
			msg = valiMesseges.getProperty(key);
		}
		return msg == null? "" : msg;
	}
	
	public static String getMenssagem(String key, String... args){
		String msg = getMenssagem(key);
		
		for (int i = 0; i < args.length; i++) {
			msg = msg.replaceAll("{" + i + "}", args[i]);
		}
		return msg;
	}
}
