package app.springboot.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by: deep.patel on 25/02/19
 */

@Data
@AllArgsConstructor
public class Notification {
    private String id;
    private String message;
}
