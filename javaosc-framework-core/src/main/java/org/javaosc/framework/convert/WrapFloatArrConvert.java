package org.javaosc.framework.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @description
 * @author Dylan Tao
 * @date 2014-09-09
 * Copyright 2014 Javaosc Team. All Rights Reserved.
 */
public class WrapFloatArrConvert implements Convert<Object[],Float[]>{
	
	private static final Logger log = LoggerFactory.getLogger(WrapFloatArrConvert.class);

	@Override
	public Float[] convert(Object[] source) {
		if(source == null) return null;  
		Float[] res = new Float[source.length];  
        for(int i=0;i<source.length;i++){  
            try {  
                res[i] = Float.parseFloat(String.valueOf(source[i]));  
            } catch (NumberFormatException e) {  
            	log.warn("WrapFloatArrConvert failed, value: {} exception: {}", String.valueOf(source[i]), e);  
                return null;  
            }  
        }  
        return res;  
	}

}
