package com.challenge.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author ysand
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiResult<T> {

    private T data;
}
