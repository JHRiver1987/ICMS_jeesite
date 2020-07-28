/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.monitor.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.monitor.entity.DeviceMonitor;

/**
 * 视频监控系统DAO接口
 * @author 姜赫
 * @version 2020-07-22
 */
@MyBatisDao
public interface DeviceMonitorDao extends CrudDao<DeviceMonitor> {
	
}