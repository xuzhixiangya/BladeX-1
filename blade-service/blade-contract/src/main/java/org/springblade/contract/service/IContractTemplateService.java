package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractTemplateEntity;

/**
 * 范本管理 服务类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:37
 */
public interface IContractTemplateService extends BaseService<ContractTemplateEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template);

	/**
	 * 修改范本状态
	 * @param templateStatus,ids
	 * @return
	 */
	boolean updateTemplateStatus(String templateStatus,Long id);

	/**
	 * 重写查询，返回附件到VO
	 * @param id
	 * @return
	 */
	ContractTemplateResponseVO getById(Long id);
}