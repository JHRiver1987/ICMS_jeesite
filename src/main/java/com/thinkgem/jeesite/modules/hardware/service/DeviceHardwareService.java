/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hardware.service;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hardware.entity.DeviceHardware;
import com.thinkgem.jeesite.modules.hardware.dao.DeviceHardwareDao;

/**
 * 计算机硬件信息Service
 * @author 姜赫
 * @version 2020-07-14
 */
@Service
@Transactional(readOnly = true)
public class DeviceHardwareService extends CrudService<DeviceHardwareDao, DeviceHardware> {

	@Autowired
	private DeviceHardwareDao deviceHardwareDao;

	public DeviceHardware get(String id) {
		return super.get(id);
	}
	
	public List<DeviceHardware> findList(DeviceHardware deviceHardware) {
		return super.findList(deviceHardware);
	}
	
	public Page<DeviceHardware> findPage(Page<DeviceHardware> page, DeviceHardware deviceHardware) {
		//deviceHardware.getSqlMap().put("dsf",dataScopeFilter(deviceHardware.getCurrentUser(),"o","a"));
		//return super.findPage(page, deviceHardware);
		deviceHardware.getSqlMap().put("dsf",dataScopeFilter(deviceHardware.getCurrentUser(),"o","a"));
		deviceHardware.setPage(page);
		page.setList(deviceHardwareDao.findList(deviceHardware));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(DeviceHardware deviceHardware) {
		super.save(deviceHardware);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeviceHardware deviceHardware) {
		super.delete(deviceHardware);
	}
	
}