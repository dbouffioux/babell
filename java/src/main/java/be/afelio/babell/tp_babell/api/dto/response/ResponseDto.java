package be.afelio.babell.tp_babell.api.dto.response;


public class ResponseDto<T> {

	@Override
	public String toString() {
		return "ResponseDto [status=" + status + ", message=" + message + ", payload=" + payload + "]";
	}

	private ResponseDtoStatus status;
	private String message;
	private T payload;
	
	public ResponseDto() {}
	
	public ResponseDto(ResponseDtoStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDtoStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseDtoStatus status) {
		this.status = status;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
}
