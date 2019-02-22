package app.springboot.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by: deep.patel on 22/02/19
 */

@Data
@AllArgsConstructor
public class ResponseObject {
    private int status;
    private String message;
}
