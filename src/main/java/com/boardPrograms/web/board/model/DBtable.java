package com.boardPrograms.web.board.model;

import java.lang.annotation.Retention;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBtable {
	public String columnName();
	
}
