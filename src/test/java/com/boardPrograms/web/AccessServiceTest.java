package com.boardPrograms.web;

import com.boardPrograms.web.board.dao.AccessDAO;
import com.boardPrograms.web.board.model.AccessVO;
import com.boardPrograms.web.board.model.BoardVO;
import com.boardPrograms.web.board.model.Params;
import com.boardPrograms.web.board.service.AccessService;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.*;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml", "classpath:spring/dataSource-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class AccessServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(AccessServiceTest.class);
	private static final String namespace = "com.boardPrograms.web.board.boarsMapper";

	@Autowired
	private AccessService accessService;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private static final String queryId = "com.boardPrograms.web.board.boarsMapper";

	@Autowired
	SqlSession sqlSession;

	ResultSet rs = null;

	public static Object convertMapToObject(Map<String, Object> map, Object obj) {
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator itr = map.keySet().iterator();

		while (itr.hasNext()) {
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
			Method[] methods = obj.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methodString.equals(methods[i].getName())) {
					try {
						methods[i].invoke(obj, map.get(keyAttribute));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}

	@Test
	public void testGetEmpList() {

		Map<String, Object> params = new HashMap<String, Object>();

		ArrayList<Params> lists = new ArrayList<Params>();

		params.put("CAMP_ID", "C");
		params.put("CAMP_STAT_CD", "중지");
		params.put("SCENARIO_NAME", "NO");
		params.put("CAMP_NAME", "테스트-통합테스트용");
		params.put("CAMP_COUNT", 1);
		params.put("GRP_VDN", "6001");
		params.put("GRP_NAME", "가입자아웃바운드");
		params.put("TR_NAME", "GH_TEST");
		params.put("CALLLIST_NAME", "U00120090904CL");

		Params paramVO = null;

		List<Params> resultList = accessService.executeProcPostgreSQL("com.boardPrograms.web.board.boardsMapper.getAccessList", params, Params.class);
		System.out.println(">>>>>" + resultList.size());

		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator itr = params.keySet().iterator();

		while (itr.hasNext()) {
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
			Method[] methods = resultList.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methodString.equals(methods[i].getName())) {
					try {
						System.out.println(">>>>>" + (List<Params>) methods[i].invoke(resultList, resultList.get(i).getCAMP_ID()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			for (int i = 0; i < resultList.size(); i++) {
				mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

				System.out.println("list" + resultList.get(i).getCAMP_ID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
