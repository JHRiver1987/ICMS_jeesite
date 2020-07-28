/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hardware.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计算机硬件信息Entity
 * @author 姜赫
 * @version 2020-07-14
 */
public class DeviceHardware extends DataEntity<DeviceHardware> {
	
	private static final long serialVersionUID = 1L;
	private Date creatDate;		// 创建时间
	private String unitName;		// 单位名称
	private String deptName;		// 部门名称
	private String posName;		// 岗位名称
	private String computerName;		// 计算机名
	private String workTeam;		// 工作组
	private String responsPeople;		// 责任人
	private String isServer;		// 设备用途
	private String computerModel;		// 计算机型号
	private String addrIp;		// IP地址
	private String addrMac;		// MAC地址
	private String computerSerialno;		// 计算机序列号
	private String incomeSource;		// 计算机来源
	private String productDate;		// 计算机生产日期
	private String userPurpose;		// 计算机用途
	private String addrPosition;		// 计算机入网地址
	private String oprationSystem;		// 操作系统
	private String isOsGenuine;		// 操作系统是否正版
	private String dataBase;		// 数据库
	private String isDbGenuine;		// 数据库是否正版
	private String avSoftware;		// 安装的杀毒软件
	private String paidGenuineSoftwares;		// 已购买的正版软件
	private String needGenuineSoftwares;		// 正版软件需求
	private String spareParam1;		// spare_param_1
	private String spareParam2;		// spare_param_2
	private String spareParam3;		// spare_param_3
	private String spareParam4;		// spare_param_4
	private String spareParam5;		// spare_param_5
	private String spareParam6;		// spare_param_6
	private String spareParam7;		// spare_param_7
	private String spareParam8;		// spare_param_8
	private String spareParam9;		// spare_param_9
	private String spareParam10;		// spare_param_10
	
	public DeviceHardware() {
		super();
	}

	public DeviceHardware(String id){
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
	
	@Length(min=0, max=100, message="部门名称长度必须介于 0 和 100 之间")
	@ExcelField(title="部门名称", align=2, sort=10)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=100, message="岗位名称长度必须介于 0 和 100 之间")
	@ExcelField(title="岗位名称", align=2, sort=20)
	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}
	
	@Length(min=0, max=100, message="计算机名长度必须介于 0 和 100 之间")
	@ExcelField(title="计算机名", align=2, sort=25)
	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	
	@Length(min=0, max=100, message="工作组长度必须介于 0 和 100 之间")
	@ExcelField(title="工作组", align=2, sort=30)
	public String getWorkTeam() {
		return workTeam;
	}

	public void setWorkTeam(String workTeam) {
		this.workTeam = workTeam;
	}
	
	@Length(min=0, max=100, message="责任人长度必须介于 0 和 100 之间")
	@ExcelField(title="责任人", align=2, sort=40)
	public String getResponsPeople() {
		return responsPeople;
	}

	public void setResponsPeople(String responsPeople) {
		this.responsPeople = responsPeople;
	}
	
	@Length(min=0, max=10, message="设备用途长度必须介于 0 和 10 之间")
	@ExcelField(title="设备用途", align=2, sort=50)
	public String getIsServer() {
		return isServer;
	}

	public void setIsServer(String isServer) {
		this.isServer = isServer;
	}
	
	@Length(min=0, max=100, message="计算机型号长度必须介于 0 和 100 之间")
	@ExcelField(title="计算机型号", align=2, sort=60)
	public String getComputerModel() {
		return computerModel;
	}

	public void setComputerModel(String computerModel) {
		this.computerModel = computerModel;
	}
	
	@Length(min=0, max=45, message="IP地址长度必须介于 0 和 45 之间")
	@ExcelField(title="IP地址", align=2, sort=70)
	public String getAddrIp() {
		return addrIp;
	}

	public void setAddrIp(String addrIp) {
		this.addrIp = addrIp;
	}
	
	@Length(min=0, max=45, message="MAC地址长度必须介于 0 和 45 之间")
	@ExcelField(title="MAC地址", align=2, sort=80)
	public String getAddrMac() {
		return addrMac;
	}

	public void setAddrMac(String addrMac) {
		this.addrMac = addrMac;
	}
	
	@Length(min=0, max=200, message="计算机序列号长度必须介于 0 和 200 之间")
	@ExcelField(title="计算机序列号", align=2, sort=90)
	public String getComputerSerialno() {
		return computerSerialno;
	}

	public void setComputerSerialno(String computerSerialno) {
		this.computerSerialno = computerSerialno;
	}
	
	@Length(min=0, max=45, message="计算机来源长度必须介于 0 和 45 之间")
	@ExcelField(title="计算机来源", align=2, sort=100, dictType="income_source")
	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}
	
	@Length(min=0, max=100, message="计算机生产日期长度必须介于 0 和 100 之间")
	@ExcelField(title="计算机生产日期", align=2, sort=110)
	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	
	@Length(min=0, max=100, message="计算机用途长度必须介于 0 和 100 之间")
	@ExcelField(title="计算机用途", align=2, sort=120)
	public String getUserPurpose() {
		return userPurpose;
	}

	public void setUserPurpose(String userPurpose) {
		this.userPurpose = userPurpose;
	}
	
	@Length(min=0, max=200, message="计算机入网地址长度必须介于 0 和 200 之间")
	@ExcelField(title="计算机入网地址", align=2, sort=130)
	public String getAddrPosition() {
		return addrPosition;
	}

	public void setAddrPosition(String addrPosition) {
		this.addrPosition = addrPosition;
	}
	
	@Length(min=0, max=200, message="操作系统长度必须介于 0 和 200 之间")
	@ExcelField(title="操作系统", align=2, sort=140)
	public String getOprationSystem() {
		return oprationSystem;
	}

	public void setOprationSystem(String oprationSystem) {
		this.oprationSystem = oprationSystem;
	}
	
	@Length(min=0, max=10, message="操作系统是否正版长度必须介于 0 和 10 之间")
	@ExcelField(title="操作系统是否正版", align=2, sort=150, dictType="yes_no")
	public String getIsOsGenuine() {
		return isOsGenuine;
	}

	public void setIsOsGenuine(String isOsGenuine) {
		this.isOsGenuine = isOsGenuine;
	}
	
	@Length(min=0, max=200, message="数据库长度必须介于 0 和 200 之间")
	@ExcelField(title="数据库", align=2, sort=160)
	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	
	@Length(min=0, max=10, message="数据库是否正版长度必须介于 0 和 10 之间")
	@ExcelField(title="数据库是否正版", align=2, sort=170)
	public String getIsDbGenuine() {
		return isDbGenuine;
	}

	public void setIsDbGenuine(String isDbGenuine) {
		this.isDbGenuine = isDbGenuine;
	}
	
	@Length(min=0, max=200, message="安装的杀毒软件长度必须介于 0 和 200 之间")
	@ExcelField(title="安装的杀毒软件", align=2, sort=180)
	public String getAvSoftware() {
		return avSoftware;
	}

	public void setAvSoftware(String avSoftware) {
		this.avSoftware = avSoftware;
	}
	
	@Length(min=0, max=255, message="已购买的正版软件长度必须介于 0 和 255 之间")
	@ExcelField(title="已购买的正版软件", align=2, sort=190)
	public String getPaidGenuineSoftwares() {
		return paidGenuineSoftwares;
	}

	public void setPaidGenuineSoftwares(String paidGenuineSoftwares) {
		this.paidGenuineSoftwares = paidGenuineSoftwares;
	}
	
	@Length(min=0, max=255, message="正版软件需求长度必须介于 0 和 255 之间")
	@ExcelField(title="正版软件需求", align=2, sort=200)
	public String getNeedGenuineSoftwares() {
		return needGenuineSoftwares;
	}

	public void setNeedGenuineSoftwares(String needGenuineSoftwares) {
		this.needGenuineSoftwares = needGenuineSoftwares;
	}
	
	@Length(min=0, max=255, message="spare_param_1长度必须介于 0 和 255 之间")
	public String getSpareParam1() {
		return spareParam1;
	}

	public void setSpareParam1(String spareParam1) {
		this.spareParam1 = spareParam1;
	}
	
	@Length(min=0, max=255, message="spare_param_2长度必须介于 0 和 255 之间")
	public String getSpareParam2() {
		return spareParam2;
	}

	public void setSpareParam2(String spareParam2) {
		this.spareParam2 = spareParam2;
	}
	
	@Length(min=0, max=255, message="spare_param_3长度必须介于 0 和 255 之间")
	public String getSpareParam3() {
		return spareParam3;
	}

	public void setSpareParam3(String spareParam3) {
		this.spareParam3 = spareParam3;
	}
	
	@Length(min=0, max=255, message="spare_param_4长度必须介于 0 和 255 之间")
	public String getSpareParam4() {
		return spareParam4;
	}

	public void setSpareParam4(String spareParam4) {
		this.spareParam4 = spareParam4;
	}
	
	@Length(min=0, max=255, message="spare_param_5长度必须介于 0 和 255 之间")
	public String getSpareParam5() {
		return spareParam5;
	}

	public void setSpareParam5(String spareParam5) {
		this.spareParam5 = spareParam5;
	}
	
	@Length(min=0, max=255, message="spare_param_6长度必须介于 0 和 255 之间")
	public String getSpareParam6() {
		return spareParam6;
	}

	public void setSpareParam6(String spareParam6) {
		this.spareParam6 = spareParam6;
	}
	
	@Length(min=0, max=255, message="spare_param_7长度必须介于 0 和 255 之间")
	public String getSpareParam7() {
		return spareParam7;
	}

	public void setSpareParam7(String spareParam7) {
		this.spareParam7 = spareParam7;
	}
	
	@Length(min=0, max=255, message="spare_param_8长度必须介于 0 和 255 之间")
	public String getSpareParam8() {
		return spareParam8;
	}

	public void setSpareParam8(String spareParam8) {
		this.spareParam8 = spareParam8;
	}
	
	@Length(min=0, max=255, message="spare_param_9长度必须介于 0 和 255 之间")
	public String getSpareParam9() {
		return spareParam9;
	}

	public void setSpareParam9(String spareParam9) {
		this.spareParam9 = spareParam9;
	}
	
	@Length(min=0, max=255, message="spare_param_10长度必须介于 0 和 255 之间")
	public String getSpareParam10() {
		return spareParam10;
	}

	public void setSpareParam10(String spareParam10) {
		this.spareParam10 = spareParam10;
	}
	
}