package com.songlou.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * ÊÚÈ¨×¢½â
 * ²Î¿¼ÍøÖ·£ºhttp://www.codingwhy.com/view/851.html
 * @author sbd04462
 *
 */
@Documented  
@Inherited  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})
public @interface NeedLogin {
	boolean validate() default true;
}
