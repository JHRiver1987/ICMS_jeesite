/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hardware.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.hardware.service.HardwareService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
import com.thinkgem.jeesite.modules.hardware.entity.DeviceHardware;
import com.thinkgem.jeesite.modules.hardware.service.DeviceHardwareService;

import java.util.Date;
import java.util.List;

/**
 * 计算机硬件信息Controller
 * @author 姜赫
 * @version 2020-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hardware/deviceHardware")
public class DeviceHardwareController extends BaseController {

	@Autowired
	private HardwareService hardwareService;

	@Autowired
	private DeviceHardwareService deviceHardwareService;
	
	@ModelAttribute
	public DeviceHardware get(@RequestParam(required=false) String id) {
		DeviceHardware entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deviceHardwareService.get(id);
		}
		if (entity == null){
			entity = new DeviceHardware();
		}
		return entity;
	}
	
	@RequiresPermissions("hardware:deviceHardware:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeviceHardware deviceHardware, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeviceHardware> page = deviceHardwareService.findPage(new Page<DeviceHardware>(request, response), deviceHardware); 
		model.addAttribute("page", page);
		return "modules/hardware/deviceHardwareList";
	}

	@RequiresPermissions("hardware:deviceHardware:view")
	@RequestMapping(value = "form")
	public String form(DeviceHardware deviceHardware, Model model) {
		model.addAttribute("deviceHardware", deviceHardware);
		return "modules/hardware/deviceHardwareForm";
	}

	@RequiresPermissions("hardware:deviceHardware:edit")
	@RequestMapping(value = "save")
	public String save(DeviceHardware deviceHardware, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deviceHardware)){
			return form(deviceHardware, model);
		}
		deviceHardwareService.save(deviceHardware);
		addMessage(redirectAttributes, "保存硬件信息成功");
		return "redirect:"+Global.getAdminPath()+"/hardware/deviceHardware/?repage";
	}
	
	@RequiresPermissions("hardware:deviceHardware:edit")
	@RequestMapping(value = "delete")
	public String delete(DeviceHardware deviceHardware, RedirectAttributes redirectAttributes) {
		deviceHardwareService.delete(deviceHardware);
		addMessage(redirectAttributes, "删除硬件信息成功");
		return "redirect:"+Global.getAdminPath()+"/hardware/deviceHardware/?repage";
	}

	@RequiresPermissions("hardware:deviceHardware:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(DeviceHardware deviceHardware,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "硬件信息数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<DeviceHardware> page = hardwareService.findDeviceHardware(new Page<DeviceHardware>(request, response, -1), deviceHardware);
			//new ExportExcel("硬件信息", DeviceHardware.class).setDataList(page.getList()).write(response, fileName).dispose();
			List<DeviceHardware> deviceHardwareList = hardwareService.findDeviceHardware(deviceHardware);
			new ExportExcel("硬件信息", DeviceHardware.class).setDataList(deviceHardwareList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出硬件信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/hardware/deviceHardware/?repage";
	}


	/**
	 * 导入硬件信息
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hardware:deviceHardware:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/hardware/deviceHardware/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DeviceHardware> list = ei.getDataList(DeviceHardware.class);
			for (DeviceHardware deviceHardware : list){
				try{
					//if (!"".equals(deviceHardware.getResponsPeople()))
					//{
						deviceHardware.setCreatDate(new Date());
						BeanValidators.validateWithException(validator, deviceHardware);
						hardwareService.saveDeviceHardware(deviceHardware);
						successNum++;
					//}else{
					//	failureMsg.append("<br/>责任人不可为空！ ");
					//	failureNum++;
					//}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>责任人为 "+deviceHardware.getResponsPeople()+" 的硬件信息导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>责任人为 "+deviceHardware.getResponsPeople()+ " 的硬件信息导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入硬件信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/hardware/deviceHardware/?repage";
	}

	/**
	 * 下载导入硬件信息模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hardware:deviceHardware:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "硬件信息导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(UserUtils.getUser());
			new ExportExcel("硬件信息", DeviceHardware.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/hardware/deviceHardware/?repage";
	}

}