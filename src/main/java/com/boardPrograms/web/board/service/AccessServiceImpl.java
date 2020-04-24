package com.boardPrograms.web.board.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.ldap.HasControls;

import java.util.Properties;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boardPrograms.web.board.dao.AccessDAO;
import com.boardPrograms.web.board.model.AccessVO;
import com.boardPrograms.web.board.model.DBtable;
import com.boardPrograms.web.board.model.Params;

@Component
@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
public class AccessServiceImpl implements AccessService {

	private static final String namespace = "com.boardPrograms.web.board.boardsMapper";

	@Autowired
	AccessDAO accessDAO;
	AccessVO accessVO;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private Log log = LogFactory.getLog(getClass());

	public AccessServiceImpl(AccessDAO accessDAO, SqlSession sqlSession) {
		this.accessDAO = accessDAO;
		this.sqlSession = sqlSession;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
	public Cursor<Object> getAccessListCursor(Map<String, Object> param) {
		Cursor<Object> cursor = null;
		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			cursor = accessDAO.getAccessListCursor(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cursor;
	}

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

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
	public <T> List<T> executeProcPostgreSQL(String queryId, Map<String, Object> param, Class<T> clazz) {

		List<T> list = new ArrayList<T>();
		ResultSet rs = null;
		Object object = null;
		ArrayList listArray = new ArrayList();

		ArrayList<Params> lists = new ArrayList<Params>();

		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Class className = clazz;
			Method m[] = className.getDeclaredMethods();

			Params params = new Params();

			Field fieldList[] = className.getDeclaredFields();

			for (int i = 0; i < fieldList.length; i++) {
				Field fid = fieldList[i];
				System.out.println("name" + fid.getName());
				String CampId = fid.getName().toString();
			}

			Method methods[] = clazz.getDeclaredMethods();

			methods = clazz.getClass().getMethods();
			System.out.println("method" + methods[0].toString());
			StringBuilder sb = new StringBuilder();

			for (Method method : methods) {
				sb.append(method.getName());

				Class<?>[] argTypes = method.getParameterTypes();
				sb.append("(");

				int size = argTypes.length;
				for (Class<?> argType : argTypes) {
					String argName = argType.getName();
					sb.append(argName + "val");
					System.out.println("arg" + argName);
					if (--size != 0) {
						sb.append(", ");
					}
				}
				sb.append(")");
			}

			for (int i = 0; i < methods.length; i++) {
				Method me = methods[i];
				System.out.println("name" + me.getName().toString());

				System.out.println(className.getClass().toString());
				System.out.println("declare" + me.getDeclaringClass().toString());
				System.out.println("class" + me.getReturnType());
				clazz = (Class<T>) me.getReturnType();
				System.out.println("class re" + clazz);
			}

			Field fields[] = clazz.getDeclaredFields();

			for (Field field : fields) {
				String fieldName = field.getName();
				System.out.println("field" + fieldName);
			}

			for (Field fieldName : className.getDeclaredFields()) {
				fieldName.setAccessible(true);
			}

			list = accessDAO.executeProcPostgreSQL(queryId, param);
			System.out.println("list value" + list);

			HashMap<String, Object> mapVO = (HashMap<String, Object>) list.get(0);

			System.out.println("map" + mapVO);

			rs = (ResultSet) mapVO.get("result");

			System.out.println("rs" + rs);

			ResultSetMetaData rsmd = rs.getMetaData();
			int nColumn = rsmd.getColumnCount();

			while (rs.next()) {
				T item = (T) className.newInstance();

				Class<?> zclass = item.getClass();
				for (Field field : zclass.getDeclaredFields()) {
					field.setAccessible(true);
					DBtable column = field.getAnnotation(DBtable.class);
					if (column == null) {
						System.out.println("sys");
						break;
					}

					Object value = rs.getObject(column.columnName());

					System.out.println("val" + value);

					Class<?> type = field.getType();
					System.out.println("typ" + type);
					if (isPrimitive(type)) {
						Class<?> boxed = boxPrimitiveClass(type);
						value = boxed.cast(value);
					}
					field.set(item, value);
				}

				list.add(item);
				System.out.println("list" + list.get(0).getClass().getName());

				System.out.println("item" + item.getClass().getFields().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private Class getClassType(ResultSetMetaData rsmd, int i) throws NumberFormatException, SQLException {
		HashMap<String, Object> hm = new HashMap();
		hm.put("91", Date.class); // 데이트
		hm.put("12", String.class); // varchar2
		hm.put("2", int.class); // number

		return (Class) hm.get(String.valueOf(rsmd.getColumnType(i)));
	}

	private static Class<?> boxPrimitiveClass(Class<?> type) {
		if (type == int.class) {
			return Integer.class;
		} else if (type == long.class) {
			return Long.class;
		} else if (type == double.class) {
			return Double.class;
		} else if (type == float.class) {
			return Float.class;
		} else if (type == boolean.class) {
			return Boolean.class;
		} else if (type == byte.class) {
			return Byte.class;
		} else if (type == char.class) {
			return Character.class;
		} else if (type == short.class) {
			return Short.class;
		} else if (type == String.class) {
			return String.class;
		} else {
			String string = "class '" + type.getName() + "' is not a primitive";
			throw new IllegalArgumentException(string);
		}
	}

	private static boolean isPrimitive(Class<?> type) {
		return (type == int.class || type == long.class || type == double.class || type == float.class
				|| type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
	public Map<String, Object> getAccessList(Map<String, Object> param) {

		Map<String, Object> map = null;

		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			map = accessDAO.getAccessList(param);
			System.out.println("map" + map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public AccessDAO getAccessDAO() {
		return accessDAO;
	}

	public void setAccessDAO(AccessDAO accessDAO) {
		this.accessDAO = accessDAO;
	}

}
