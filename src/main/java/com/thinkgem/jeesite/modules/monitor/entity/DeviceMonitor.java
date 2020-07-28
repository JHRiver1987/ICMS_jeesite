/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.monitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 视频监控系统Entity
 * @author 姜赫
 * @version 2020-07-22
 */
public class DeviceMonitor extends DataEntity<DeviceMonitor> {
	
	private static final long serialVersionUID = 1L;
	private Date creatDate;		// 创建时间
	private String unitName;		// 单位名称
	private String status;		// 现况/需求
	private String deptName;		// 作业区名称
	private String hostStyle;		// 主机平台类型
	private String hostBrand;		// 主机平台品牌
	private String hostModel;		// 主机平台型号
	private Integer hostNum;		// 主机平台数量
	private String camStyle;		// 监控摄像机类型
	private String camSignal;		// 接入类型
	private String camBrand;		// 监控摄像机品牌
	private String camModel;		// 监控摄像机型号
	private Integer camNum;		// 监控摄像机数量
	private String year;		// 安装年份
	
	public DeviceMonitor() {
		super();
	}

	public DeviceMonitor(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	@Length(min=1, max=100, message="单位名称长度必须介于 1 和 100 之间")
	@ExcelField(title="单位名称", align=2, sort=1)
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	@Length(min=0, max=100, message="现况/需求长度必须介于 0 和 100 之间")
	@ExcelField(title="现况/需求", align=2, sort=10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="作业区名称长度必须介于 0 和 100 之间")
	@ExcelField(title="作业区名称", align=2, sort=20)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=100, message="主机平台类型长度必须介于 0 和 100 之间")
	@ExcelField(title="主机平台类型", align=2, sort=25)
	public String getHostStyle() {
		return hostStyle;
	}

	public void setHostStyle(String hostStyle) {
		this.hostStyle = hostStyle;
	}
	
	@Length(min=0, max=100, message="主机平台品牌长度必须介于 0 和 100 之间")
	@ExcelField(title="主机平台品牌", align=2, sort=30)
	public String getHostBrand() {
		return hostBrand;
	}

	public void setHostBrand(String hostBrand) {
		this.hostBrand = hostBrand;
	}
	
	@Length(min=0, max=100, message="主机平台型号长度必须介于 0 和 100 之间")
	@ExcelField(title="主机平台型号", align=2, sort=40)
	public String getHostModel() {
		return hostModel;
	}

	public void setHostModel(String hostModel) {
		this.hostModel = hostModel;
	}

	@ExcelField(title="主机平台数量", align=2, sort=50)
	public Integer getHostNum() {
		return hostNum;
	}

	public void setHostNum(Integer hostNum) {
		this.hostNum = hostNum;
	}
	
	@Length(min=0, max=100, message="监控摄像机类型长度必须介于 0 和 100 之间")
	@ExcelField(title="监控摄像机类型", align=2, sort=60)
	public String getCamStyle() {
		return camStyle;
	}

	public void setCamStyle(String camStyle) {
		this.camStyle = camStyle;
	}
	
	@Length(min=0, max=100, message="接入类型长度必须介于 0 和 100 之间")
	@ExcelField(title="接入类型", align=2, sort=70)
	public String getCamSignal() {
		return camSignal;
	}

	public void setCamSignal(String camSignal) {
		this.camSignal = camSignal;
	}
	
	@Length(min=0, max=100, message="监控摄像机品牌长度必须介于 0 和 100 之间")
	@ExcelField(title="监控摄像机品牌", align=2, sort=80)
	public String getCamBrand() {
		return camBrand;
	}

	public void setCamBrand(String camBrand) {
		this.camBrand = camBrand;
	}
	
	@Length(min=0, max=100, message="监控摄像机型号长度必须介于 0 和 100 之间")
	@ExcelField(title="监控摄像机型号", align=2, sort=90)
	public String getCamModel() {
		return camModel;
	}

	public void setCamModel(String camModel) {
		this.camModel = camModel;
	}

	@ExcelField(title="监控摄像机数量", align=2, sort=100)
	public Integer getCamNum() {
		return camNum;
	}

	public void setCamNum(Integer camNum) {
		this.camNum = camNum;
	}
	
	@Length(min=0, max=16, message="安装年份长度必须介于 0 和 16 之间")
	@ExcelField(title="安装年份", align=2, sort=110)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}