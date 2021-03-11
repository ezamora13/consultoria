package ec.gob.mag.renagro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SectorDispersoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SectorDispersoNotFoundException(String message) {
		super(message);
	}
}