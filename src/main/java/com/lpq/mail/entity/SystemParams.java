package com.lpq.mail.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * system_params
 * @author 
 */
@Data
public class SystemParams implements Serializable {
    private Integer id;

    private String name;

    private String value;

    private String description;

    private static final long serialVersionUID = 1L;
}