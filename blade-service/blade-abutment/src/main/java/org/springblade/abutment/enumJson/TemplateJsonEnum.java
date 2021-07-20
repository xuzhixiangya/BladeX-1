package org.springblade.abutment.enumJson;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.entity.CglRawMaterials1Entity;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
import org.springblade.system.entity.TemplateFieldJsonEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author xhbbo
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum TemplateJsonEnum {

	//原物料-买卖合同
	MMHT_10("MMHT_10") {
		@Override
		public String setScheduler() {
			return "cglRawMaterials1List";
		}

		@Override
		public Object setScheduler(String type) {
			List<LinkedHashMap<Object, Object>> objects = new ArrayList<>();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(type, TemplateFieldJsonEntity.class);
			templateFieldList.forEach(teL -> {
				List<CglRawMaterials1ResponseVO> CglRawMaterials1;
				if ("cglRawMaterials1List".equals(teL.getFieldName())) {
					CglRawMaterials1 = JSON.parseArray(teL.getTableData(), CglRawMaterials1ResponseVO.class);
					CglRawMaterials1.forEach(cg -> {
						try {
							LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
							for (Field field : cg.getClass().getDeclaredFields()) {
								field.setAccessible(true);
								String fieldName = field.getName();
								String value;
								if (field.get(cg) == null) {
									continue;
								} else {
									value = field.get(cg).toString();
								}
								map.put(fieldName, value);
							}
							map.remove("cglNumber");
							map.remove("createUserName");
							map.remove("isDeleted");
							map.remove("id");
							map.remove("createDeptName");
							map.remove("updateUserName");
							map.remove("updateUser");
							map.remove("updateTime");
							map.remove("createDept");
							map.remove("createTime");
							map.remove("createUser");
							map.remove("status");
							map.remove("contractId");
							map.remove("serialVersionUID");
							objects.add(map);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					});
				}
			});
			return objects;
		}

		@Override
		public List<LinkedHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			if (tag) {
				List<LinkedHashMap<Object, Object>> objects = new ArrayList<>();
				LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
				for (Field field : CglRawMaterials1Entity.class.getDeclaredFields()) {
					if (field.isAnnotationPresent(ApiModelProperty.class)) {
						/**
						 * 获取字段名
						 */
						ApiModelProperty declaredAnnotation = field.getDeclaredAnnotation(ApiModelProperty.class);
						String column = declaredAnnotation.value();
						map.put(field.getName(), column);
					}
				}
				map.remove("cglNumber");
				map.remove("createUserName");
				map.remove("isDeleted");
				map.remove("id");
				map.remove("createDeptName");
				map.remove("updateUserName");
				map.remove("updateUser");
				map.remove("updateTime");
				map.remove("createDept");
				map.remove("createTime");
				map.remove("createUser");
				map.remove("status");
				map.remove("contractId");
				map.remove("serialVersionUID");
				objects.add(map);
				return objects;
			}
			return null;
		}
	},
	//买卖合同（五金低耗类）
	BCXY_09("BCXY_09") {
		@Override
		public String setScheduler() {
			return "cglRawMaterials1List";
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<LinkedHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//买卖合同（行销品）
	MMHT_07("MMHT_07") {
		@Override
		public String setScheduler() {
			return "cglCategorySalesContracts1List";
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<LinkedHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	};

	public abstract String setScheduler();

	public abstract Object setScheduler(String type);

	public abstract List<LinkedHashMap<Object, Object>> setScheduler(String type, Boolean tag);

	@Getter
	public String type;

	/**
	 * 通过轮询来获得相应的方法
	 */
	public static TemplateJsonEnum fromValue(String type) {
		return Stream.of(TemplateJsonEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}
}