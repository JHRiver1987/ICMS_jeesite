/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.monitor.entity.DeviceMonitor;
import com.thinkgem.jeesite.modules.monitor.dao.DeviceMonitorDao;

/**
 * 视频监控系统Service
 * @author 姜赫
 * @version 2020-07-22
 */
@Service
@Transactional(readOnly = true)
public class DeviceMonitorService extends CrudService<DeviceMonitorDao, DeviceMonitor> {

	@Autowired
	private DeviceMonitorDao deviceMonitorDao;

	public DeviceMonitor get(String id) {
		return super.get(id);
	}
	
	public List<DeviceMonitor> findList(DeviceMonitor deviceMonitor) {
		return super.findList(deviceMonitor);
	}
	
	public Page<DeviceMonitor> findPage(Page<DeviceMonitor> page, DeviceMonitor deviceMonitor) {
		deviceMonitor.getSqlMap().put("dsf", dataScopeFilter(deviceMonitor.getCurrentUser(),"o","a"));
		deviceMonitor.setPage(page);
		page.setList(deviceMonitorDao.findList(deviceMonitor));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(DeviceMonitor deviceMonitor) {
		super.save(deviceMonitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeviceMonitor deviceMonitor) {
		super.delete(deviceMonitor);
	}
	
}