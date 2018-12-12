package cn.pyq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-08 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vo implements Serializable {
	private String logOutUrl;
	private String jSessionId;
}

