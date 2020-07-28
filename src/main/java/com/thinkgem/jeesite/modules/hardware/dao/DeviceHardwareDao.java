/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hardware.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hardware.entity.DeviceHardware;

/**
 * 计算机硬件信息DAO接口
 * @author 姜赫
 * @version 2020-07-14
 */
@MyBatisDao
public interface DeviceHardwareDao extends CrudDao<DeviceHardware> {
	
}