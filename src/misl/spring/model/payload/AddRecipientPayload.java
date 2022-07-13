package misl.spring.model.payload;

import lombok.Data;

@Data
public class AddRecipientPayload {
	private int orderId;
	private String phoneNumber;
}
