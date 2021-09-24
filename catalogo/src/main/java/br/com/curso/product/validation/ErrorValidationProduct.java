package br.com.curso.product.validation;


public class ErrorValidationProduct {

		private int status_code;
		private String message;
		
		public ErrorValidationProduct( int status_code, String message) {
			this.status_code = status_code;
			this.message = message;
		}

		public int getStatus_Code() {
			return status_code;
		}

		public String getMessage() {
			return message;
		}
	
}
