package org.websparrow.dao;

import org.springframework.stereotype.Service;
import org.websparrow.entity.Info;

@Service
public interface InfoDAO {

	
	boolean add(Info info);
}
