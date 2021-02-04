package com.rediron.platform.domain.rnet;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tomax.api.DataTransferObject;

public abstract class RNetAbstractEntity<E extends RNetAbstractEntity<E, D>, D> implements Serializable,
		PersistableObject<E> {
	private static final Logger logger = LoggerFactory.getLogger(RNetAbstractEntity.class);

	public D toDTO() {
		Map<Object, Object> map = new HashMap<Object, Object>();

		return toDTO(map);
	}

	public D toDTO(Map<Object, Object> map) {
		Map<RNetAbstractEntity, String> pathMap = new HashMap<RNetAbstractEntity, String>();

		performCopy(getStack(getRoots(pathMap), pathMap), map, pathMap);

		return (D) map.get(this);
	}

	private static void getRoots(RNetAbstractEntity entity, List<RNetAbstractEntity> rootList,
			Map<RNetAbstractEntity, String> pathMap) {
		if (entity != null) {

			// We may need to fix this if an illegitimate child has an illegitimate child
			boolean firstItemAdded = false;
			if (pathMap.isEmpty()) {
				rootList.add(entity);
				firstItemAdded = true;
			}
			List<RNetAbstractEntity> list = null;
			try {
				list = entity.getParents();
				pathMap.put(entity, "true");
				if (list == null || list.isEmpty()) {
					if (!firstItemAdded) {
						rootList.add(entity);
					}
				} else {
					for (RNetAbstractEntity e : list) {
						getRoots(e, rootList, pathMap);
					}
				}
			} catch (LazyInitializationException e) {
				return;
			}
		}

	}

	protected List<RNetAbstractEntity> getRoots(Map<RNetAbstractEntity, String> pathMap) {
		List<RNetAbstractEntity> list = new ArrayList<RNetAbstractEntity>();

		getRoots(this, list, pathMap);

		return list;
	}

	protected static Stack<RNetAbstractEntity> getStack(List<RNetAbstractEntity> rootList,
			Map<RNetAbstractEntity, String> pathMap) {
		Stack<RNetAbstractEntity> stack = new Stack<RNetAbstractEntity>();

		populateStack(rootList, stack, new HashMap<RNetAbstractEntity, String>(), pathMap);

		return stack;
	}

	private static void populateStack(List<RNetAbstractEntity> list, Stack<RNetAbstractEntity> stack,
			Map<RNetAbstractEntity, String> map, Map<RNetAbstractEntity, String> pathMap) {

		List<RNetAbstractEntity> newList = new ArrayList<RNetAbstractEntity>();

		for (RNetAbstractEntity e : list) {
			if (map.get(e) == null) {
				newList.add(e);
				map.put(e, "true");

				try {
					List<RNetAbstractEntity> parents = e.getParents();

					if (parents != null) {
						for (RNetAbstractEntity obj : parents) {

							if (map.get(obj) == null) {
								List<RNetAbstractEntity> rootList = new ArrayList<RNetAbstractEntity>();
								// Map<RNetAbstractEntity, String> pathMap = new HashMap<RNetAbstractEntity, String>();
								getRoots(obj, rootList, pathMap);
								populateStack(rootList, stack, map, pathMap);
							}

						}
					}
				} catch (LazyInitializationException e1) {
					continue;
				} finally {
					stack.push(e);
				}

			}

		}

		List<RNetAbstractEntity> allChildren = new ArrayList<RNetAbstractEntity>();
		List<Set<RNetAbstractEntity>> childrenCol = new ArrayList<Set<RNetAbstractEntity>>();
		for (RNetAbstractEntity e : newList) {
			List<Set<RNetAbstractEntity>> children = e.getChildren();

			if (children != null && !children.isEmpty()) {

				for (Set<RNetAbstractEntity> l : children) {
					if (childrenCol.indexOf(l) == -1) {
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

	private static void performCopy(Stack<RNetAbstractEntity> stack, Map<Object, Object> map,
			Map<RNetAbstractEntity, String> pathMap) {
		int stackPopCount = 0;
		while (!stack.isEmpty()) {
			RNetAbstractEntity entity = stack.pop();
			stackPopCount++;
			pathMap.remove(entity);
			if (map.get(entity) == null) {
				//logger.info("calling assignAllChildren for " + entity.getClass().getName());
				entity.assignAllChildren(map, stack);
				/* RNET-3702, RNET-3669 - commented out problematic code.
					Gords: Only assign all Parents for the first item on the stack.
					Otherwise performance will degrade exponentially. */
				if (stackPopCount == 1) {
					try {
						//logger.info("assigning all parents");
						entity.assignAllParents(map);
					} catch (LazyInitializationException e3) {
						continue;
					}
				}
				//*/
			}
		}
		//logger.info("Total number of DTOs inside is:  " + stackPopCount);
		Iterator<RNetAbstractEntity> iterator = pathMap.keySet().iterator();

		int skippedEntityCount = 0;
		while (iterator.hasNext()) {
			RNetAbstractEntity skippedEntity = iterator.next();
			skippedEntityCount++;
			skippedEntity.assignAllChildren(map, stack);
			skippedEntity.assignAllParents(map);
		}
//		logger.info("Total of " + skippedEntityCount + " entities.");
	}

	public static <E extends RNetAbstractEntity<E, D>, D> E getSimpleEntity(Class<E> clazz, DataTransferObject dto,
			Map<Object, Object> map) {
		if (dto == null) {
			return null;
		}

		E entity = (E) map.get(dto);

		if (entity != null) {
			return entity;
		}

		try {
			entity = clazz.newInstance();
			entity = ((RNetAbstractEntity<E, D>) entity).toSimpleEntity(dto, map);

			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void addChildren(DataTransferObject dto, DataTransferObject child) {
		StringBuilder methodName = new StringBuilder("add");
		methodName.append(StringUtils.capitalize(child.getClass().getSimpleName()));
		methodName.replace(methodName.lastIndexOf("DTO"), methodName.length(), "");
		if (StringUtils.endsWith(methodName.toString(), "s") && !StringUtils.endsWith(methodName.toString(), "ss")) {
			methodName.replace(methodName.length() - 1, methodName.length(), "");
		}

		try {
			Method getChild = dto.getClass().getMethod(methodName.toString(), child.getClass());
			getChild.invoke(dto, child);
		} catch (NoSuchMethodException e) {
			for (Method method : dto.getClass().getMethods()) {
				if (method.getName().startsWith("add") && method.getParameterTypes().length == 1
						&& method.getParameterTypes()[0] == child.getClass()) {
					try {
						method.invoke(dto, child);
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addChildren(RNetAbstractEntity parent, RNetAbstractEntity child) {
		StringBuilder methodName = new StringBuilder("add");
		methodName.append(StringUtils.capitalize(child.getClass().getSimpleName()));
		methodName.replace(methodName.lastIndexOf("Entity"), methodName.length(), "");
		if (StringUtils.endsWith(methodName.toString(), "s") && !StringUtils.endsWith(methodName.toString(), "ss")) {
			methodName.replace(methodName.length() - 1, methodName.length(), "");
		}

		try {
			Method getChild = parent.getClass().getMethod(methodName.toString(), child.getClass());
			getChild.invoke(parent, child);
		} catch (NoSuchMethodException e) {
			for (Method method : parent.getClass().getMethods()) {
				if (method.getName().startsWith("add") && method.getParameterTypes().length == 1
						&& method.getParameterTypes()[0] == child.getClass()) {
					try {
						method.invoke(parent, child);
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setHeader(DataTransferObject dto, DataTransferObject hdr) {
		try {
			StringBuilder methodName = new StringBuilder("set");
			methodName.append(StringUtils.capitalize(hdr.getClass().getSimpleName()));
			if (methodName.indexOf("Full") > 0) {
				methodName.replace(methodName.lastIndexOf("Full"), methodName.length(), "");
			} else {
				methodName.replace(methodName.lastIndexOf("DTO"), methodName.length(), "");
			}

			Method setHdr = dto.getClass().getMethod(methodName.toString(), hdr.getClass());
			setHdr.invoke(dto, hdr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setHeader(DataTransferObject dto, RNetAbstractEntity hdr) {
		if (hdr != null && dto != null) {
			try {
				DataTransferObject hdrDTO = (DataTransferObject) hdr.toDTO();

				StringBuilder methodName = new StringBuilder("set");
				methodName.append(StringUtils.capitalize(hdr.getClass().getSimpleName()));
				if (methodName.indexOf("Full") > 0) {
					methodName.replace(methodName.lastIndexOf("Full"), methodName.length(), "");
				} else {
					methodName.replace(methodName.lastIndexOf("Entity"), methodName.length(), "");
				}

				Method setHdr = dto.getClass().getMethod(methodName.toString(), hdrDTO.getClass());
				setHdr.invoke(dto, hdrDTO);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void setHeader(RNetAbstractEntity child, RNetAbstractEntity hdr) {
		if (hdr != null && child != null) {
			try {
				StringBuilder methodName = new StringBuilder("set");
				methodName.append(StringUtils.capitalize(hdr.getClass().getSimpleName()));
				if (methodName.indexOf("Full") > 0) {
					methodName.replace(methodName.lastIndexOf("Full"), methodName.length(), "");
				} else {
					methodName.replace(methodName.lastIndexOf("Entity"), methodName.length(), "");
				}

				Method setHdr = child.getClass().getMethod(methodName.toString(), hdr.getClass());
				setHdr.invoke(child, hdr);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static Class getHeaderEntityClass(DataTransferObject hdr) {
		if (hdr == null) {
			return null;
		}
		String packageName = StringUtils.replace(hdr.getClass().getPackage().getName(), "dto", "entity");
		String className = StringUtils.replace(hdr.getClass().getName(), "DTO", "Entity");

		try {
			return Class.forName(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	abstract protected void assignAllChildren(Map<Object, Object> map, Stack<Object> stack);

	abstract protected void assignAllParents(Map<Object, Object> map);

	abstract protected D toSimpleDto(Map<Object, Object> map);

	abstract protected E toSimpleEntity(DataTransferObject dto, Map<Object, Object> map);

	abstract protected List<RNetAbstractEntity> getParents();

	abstract protected List<Set<? extends RNetAbstractEntity>> getChildren();
}