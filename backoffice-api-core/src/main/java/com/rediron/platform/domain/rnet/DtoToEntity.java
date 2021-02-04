package com.rediron.platform.domain.rnet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.tomax.api.DataTransferObject;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.api.annotation.Child;
import com.tomax.api.annotation.Parent;

public class DtoToEntity {
	protected static List<DataTransferObject> getRoots(DataTransferObject dto, Map<DataTransferObject, String> pathMap) {
		List<DataTransferObject> list = new ArrayList<DataTransferObject>();

		getRoots(dto, list, pathMap);

		return list;
	}

	private static void getRoots(DataTransferObject dto, List<DataTransferObject> rootList,
			Map<DataTransferObject, String> pathMap) {
		if (dto != null) {
			List<DataTransferObject> list = getParents(dto);
			pathMap.put(dto, "true");
			if (list == null || list.isEmpty()) {
				rootList.add(dto);
			} else {
				for (DataTransferObject e : list) {
					getRoots(e, rootList, pathMap);
				}
			}
		}
	}

	protected static Stack<DataTransferObject> getStack(List<DataTransferObject> rootList,
			Map<DataTransferObject, String> pathMap) {
		Stack<DataTransferObject> stack = new Stack<DataTransferObject>();

		populateStack(rootList, stack, new HashMap<DataTransferObject, String>(), pathMap);

		return stack;
	}

	private static void populateStack(List<DataTransferObject> list, Stack<DataTransferObject> stack,
			Map<DataTransferObject, String> map, Map<DataTransferObject, String> pathMap) {

		List<DataTransferObject> newList = new ArrayList<DataTransferObject>();

		for (DataTransferObject dto : list) {
			if (map.get(dto) == null) {
				newList.add(dto);
				map.put(dto, "true");

				List<DataTransferObject> parents = getParents(dto);

				if (parents != null) {
					for (DataTransferObject obj : parents) {

						if (map.get(obj) == null) {
							List<DataTransferObject> rootList = new ArrayList<DataTransferObject>();
							getRoots(obj, rootList, pathMap);
							populateStack(rootList, stack, map, pathMap);
						}

					}
				}

				stack.push(dto);
			}

		}

		List<DataTransferObject> allChildren = new ArrayList<DataTransferObject>();
		List<List<DataTransferObject>> childrenCol = new ArrayList<List<DataTransferObject>>();
		for (DataTransferObject dto : newList) {
			List<List<DataTransferObject>> children = getChildren(dto);

			if (children != null && !children.isEmpty()) {

				for (List<DataTransferObject> l : children) {
					// Issue when both objects end up equalling....again
					if (!l.isEmpty() && childrenCol.indexOf(l) == -1) {
						childrenCol.add(l);
						allChildren.addAll(l);
					}
				}
			}

		}

		if (!allChildren.isEmpty()) {
			populateStack(allChildren, stack, map, pathMap);
		}
	}

	public static RNetAbstractEntity toEntity(Class entityClass, DataTransferObject dto) {
		Map<Object, Object> map = new HashMap<Object, Object>();

		return toEntity(entityClass, dto, map);
	}

	public static RNetAbstractEntity toEntity(Class entityClass, DataTransferObject dto, Map<Object, Object> map) {
		Map<DataTransferObject, String> pathMap = new HashMap<DataTransferObject, String>();

		performCopy(entityClass, getStack(DtoToEntity.getRoots(dto, pathMap), pathMap), map, pathMap);

		return (RNetAbstractEntity) map.get(dto);
	}

	private static void performCopy(Class entityClass, Stack<DataTransferObject> stack, Map<Object, Object> map,
			Map<DataTransferObject, String> pathMap) {
		while (!stack.isEmpty()) {
			DataTransferObject dto = stack.pop();
			pathMap.remove(dto);

			try {
				String entityName = StringUtils.replace(dto.getClass().getName(), "DTO", "Entity");
				entityName = StringUtils.replace(entityName, "dto", "entity");
				Class newEntity = Class.forName(entityName);
				if (map.get(dto) == null) {
					assignAllChildren(newEntity, dto, map, stack);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		Iterator<DataTransferObject> iterator = pathMap.keySet().iterator();

		while (iterator.hasNext()) {
			assignAllParents(entityClass, iterator.next(), map);
		}
	}

	private static void assignAllChildren(Class entityClass, DataTransferObject dto, Map<Object, Object> map,
			Stack<DataTransferObject> stack) {
		// DtlDto dto = toSimpleDto(map);
		if (dto != null) {
			RNetAbstractEntity entity = RNetAbstractEntity.getSimpleEntity(entityClass, dto, map);

			for (List<DataTransferObject> c : getChildren(dto)) {
				for (DataTransferObject d : c) {
					Object o = map.get(d);
					if (o != null) {
						entity.addChildren(entity, (RNetAbstractEntity) o);
					} else {
						if (stack.contains(d)) {
							o = DtoToEntity.toEntity(stack.get(stack.indexOf(d)).getClass(), d, map);
							if (o == null) {
								throw new RNetUnexpectedException("Copy missed some child entry");
							}
							entity.addChildren(entity, (RNetAbstractEntity) o);
							map.put(d, o);
						} else {
							throw new RNetUnexpectedException("Copy missed some child entry");
						}
					}
				}
			}
		}
	}

	private static void assignAllParents(Class entityClass, DataTransferObject dto, Map<Object, Object> map) {
		if (dto != null) {
			RNetAbstractEntity entity = RNetAbstractEntity.getSimpleEntity(entityClass, dto, map);

			for (DataTransferObject h : getParents(dto)) {
				if (h != null) {
					RNetAbstractEntity o = (RNetAbstractEntity) map.get(h);
					if (o != null) {
						entity.setHeader(entity, o);
					} else {
						entity.setHeader(entity, toEntity(entity.getHeaderEntityClass(h), h, map));
					}
				}
			}
		}
	}

	private static List<List<DataTransferObject>> getChildren(DataTransferObject dto) {
		if (dto == null) {
			return null;
		}
		List<List<DataTransferObject>> children = new ArrayList<List<DataTransferObject>>();

		try {
			for (Method method : dto.getClass().getDeclaredMethods()) {
				if (method.getAnnotation(Child.class) != null) {
					List<DataTransferObject> child = (List<DataTransferObject>) method.invoke(dto);
					if (child != null) {
						children.add(child);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return children;
	}

	private static List<DataTransferObject> getParents(DataTransferObject dto) {
		if (dto == null) {
			return null;
		}

		List<DataTransferObject> parents = new ArrayList<DataTransferObject>();

		try {
			for (Method method : dto.getClass().getDeclaredMethods()) {
				if (method.getAnnotation(Parent.class) != null) {
					DataTransferObject parent = (DataTransferObject) method.invoke(dto);
					if (parent != null) {
						parents.add(parent);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return parents;
	}
}
