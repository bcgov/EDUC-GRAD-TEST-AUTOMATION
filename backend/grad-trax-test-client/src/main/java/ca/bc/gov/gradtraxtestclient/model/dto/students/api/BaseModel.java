package ca.bc.gov.gradtraxtestclient.model.dto.students.api;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
	private String createUser;	
	private Date createDate;	
	private String updateUser;	
	private Date updateDate;
}
