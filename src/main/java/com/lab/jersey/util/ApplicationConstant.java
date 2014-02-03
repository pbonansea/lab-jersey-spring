package com.lab.jersey.util;
/**
 * 
 */

/**
 * @author paolobonansea
 *
 */
public final class ApplicationConstant {

	public static final String ENVIRONMET_PARAM = "spring.profiles.active"; 
	
	public enum Runtime {
		
		ENVIRONMENT(""),
		DEV_ENVIRONMENT("dev"),
		PRO_ENVIRONMENT("pro"),
		TEST_ENVIRONMENT("test");
		
		private String value;
		
		private Runtime(String value) {
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
}
