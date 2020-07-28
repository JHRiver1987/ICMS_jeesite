/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.monitor.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.hardware.entity.DeviceHardware;
import com.thinkgem.jeesite.modules.monitor.service.MonitorService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.monitor.entity.DeviceMonitor;
import com.thinkgem.jeesite.modules.monitor.service.DeviceMonitorService;

import java.util.Date;
import java.util.List;

/**
 * 视频监控系统Controller
 * @author 姜赫
 * @version 2020-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/monitor/deviceMonitor")
public class DeviceMonitorController extends BaseController {

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private DeviceMonitorService deviceMonitorService;
	
	@ModelAttribute
	public DeviceMonitor get(@RequestParam(required=false) String id) {
		DeviceMonitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deviceMonitorService.get(id);
		}
		if (entity == null){
			entity = new DeviceMonitor();
		}
		return entity;
	}
	
	@RequiresPermissions("monitor:deviceMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeviceMonitor deviceMonitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeviceMonitor> page = deviceMonitorService.findPage(new Page<DeviceMonitor>(request, response), deviceMonitor); 
		model.addAttribute("page", page);
		return "modules/monitor/deviceMonitorList";
	}

	@RequiresPermissions("monitor:deviceMonitor:view")
	@RequestMapping(value = "form")
	public String form(DeviceMonitor deviceMonitor, Model model) {
		model.addAttribute("deviceMonitor", deviceMonitor);
		return "modules/monitor/deviceMonitorForm";
	}

	@RequiresPermissions("monitor:deviceMonitor:edit")
	@RequestMapping(value = "save")
	public String save(DeviceMonitor deviceMonitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deviceMonitor)){
			return form(deviceMonitor, model);
		}
		deviceMonitorService.save(deviceMonitor);
		addMessage(redirectAttributes, "保存视频监控系统成功");
		return "redirect:"+Global.getAdminPath()+"/monitor/deviceMonitor/?repage";
	}
	
	@RequiresPermissions("monitor:deviceMonitor:edit")
	@RequestMapping(value = "delete")
	public String delete(DeviceMonitor deviceMonitor, RedirectAttributes redirectAttributes) {
		deviceMonitorService.delete(deviceMonitor);
		addMessage(redirectAttributes, "删除视频监控系统成功");
		return "redirect:"+Global.getAdminPath()+"/monitor/deviceMonitor/?repage";
	}

	@RequiresPermissions("monitor:deviceMonitor:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(DeviceMonitor deviceMonitor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "视频监控系统数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<DeviceMonitor> page = monitorService.findDeviceMonitor(new Page<DeviceMonitor>(request, response, -1), deviceMonitor);
			new ExportExcel("视频监控系统信息", DeviceMonitor.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出视频监控系统信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/deviceMonitor/?repage";
	}

	/**
	 * 导入视频监控系统信息
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("monitor:deviceMonitor:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/monitor/deviceMonitor/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DeviceMonitor> list = ei.getDataList(DeviceMonitor.class);
			for (DeviceMonitor deviceMonitor : list){
				try{
					if (!"".equals(deviceMonitor.getUnitName()))
					{
					deviceMonitor.setCreatDate(new Date());
					BeanValidators.validateWithException(validator, deviceMonitor);
					monitorService.saveDeviceMonitor(deviceMonitor);
					successNum++;
					}else{
						failureMsg.append("<br/>单位名称不可为空！ ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>单位名称为 "+deviceMonitor.getUnitName()+" 的视频监控系统信息导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>单位名称为 "+deviceMonitor.getUnitName()+ " 的视频监控系统信息导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入视频监控系统信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/deviceMonitor/?repage";
	}

	/**
	 * 下载导入视频监控系统信息模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("monitor:deviceMonitor:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "视频监控系统信息导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(UserUtils.getUser());
			new ExportExcel("视频监控系统信息", DeviceMonitor.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/deviceMonitor/?repage";
	}

}