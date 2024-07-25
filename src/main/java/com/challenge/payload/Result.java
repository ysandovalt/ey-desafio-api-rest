package com.challenge.payload;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author ysand
 */
@Data
public class Result {

    LocalDateTime timestamp;

    public Result() {
        this.timestamp = LocalDateTime.now();
    }

}
