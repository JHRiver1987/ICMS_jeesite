package com.thinkgem.jeesite.modules.hardware.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hardware.dao.DeviceHardwareDao;
import com.thinkgem.jeesite.modules.hardware.entity.DeviceHardware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HardwareService extends BaseService implements InitializingBean {
    @Autowired
    private DeviceHardwareDao deviceHardwareDao;


    public Page<DeviceHardware> findDeviceHardware(Page<DeviceHardware> page,DeviceHardware deviceHardware){
        deviceHardware.getSqlMap().put("dsf",dataScopeFilter(deviceHardware.getCurrentUser(),"o","a"));
        deviceHardware.setPage(page);
        page.setList(deviceHardwareDao.findList(deviceHardware));
        return page;
    }


    /**
     * 无分页查询硬件信息列表
     * @param deviceHardware
     * @return
     */
    public List<DeviceHardware> findDeviceHardware(DeviceHardware deviceHardware){
        deviceHardware.getSqlMap().put("dsf",dataScopeFilter(deviceHardware.getCurrentUser(),"o","a"));
        List<DeviceHardware> list = deviceHardwareDao.findList(deviceHardware);
        return list;
    }

    @Transactional(readOnly = false)
    public void saveDeviceHardware(DeviceHardware deviceHardware){
        if (StringUtils.isBlank(deviceHardware.getId())){
            deviceHardware.preInsert();
            deviceHardwareDao.insert(deviceHardware);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
