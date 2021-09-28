package br.com.curso.product.validation;


public class ErrorValidationProduct {

		private int statusCode;
		private String message;
		
		public ErrorValidationProduct( int statusCode, String message) {
			this.statusCode = statusCode;
			this.message = message;
		}

		public int getStatus_code() {
			return statusCode;
		}

		public String getMessage() {
			return message;
		}
	
}
