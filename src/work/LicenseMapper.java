package work;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LicenseMapper {
	String tagLocation();
	String columId();
	String xsd() default "M";
	int startNum() default -1;
	int endNum() default -1;

}
