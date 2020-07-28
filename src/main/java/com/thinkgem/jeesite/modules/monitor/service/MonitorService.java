package com.thinkgem.jeesite.modules.monitor.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.monitor.dao.DeviceMonitorDao;
import com.thinkgem.jeesite.modules.monitor.entity.DeviceMonitor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MonitorService extends BaseService implements InitializingBean {

    @Autowired
    private DeviceMonitorDao deviceMonitorDao;

    public Page<DeviceMonitor> findDeviceMonitor(Page<DeviceMonitor> page,DeviceMonitor deviceMonitor){
        deviceMonitor.getSqlMap().put("dsf",dataScopeFilter(deviceMonitor.getCurrentUser(),"o","a"));
        deviceMonitor.setPage(page);
        page.setList(deviceMonitorDao.findList(deviceMonitor));
        return page;
    }

    public List<DeviceMonitor> findDeviceMonitor(DeviceMonitor deviceMonitor){
        deviceMonitor.getSqlMap().put("dsf",dataScopeFilter(deviceMonitor.getCurrentUser(),"o","a"));
        List<DeviceMonitor> list = deviceMonitorDao.findList(deviceMonitor);
        return list;
    }

    @Transactional(readOnly = false)
    public void saveDeviceMonitor(DeviceMonitor deviceMonitor){
        if(StringUtils.isBlank(deviceMonitor.getId())){
            deviceMonitor.preInsert();
            deviceMonitorDao.insert(deviceMonitor);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
