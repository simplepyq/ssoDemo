package cn.pyq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-07 20:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private Integer id;
	private String username;
	private String password;
}

