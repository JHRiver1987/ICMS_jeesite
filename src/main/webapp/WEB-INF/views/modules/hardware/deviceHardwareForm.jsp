<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>硬件信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hardware/deviceHardware/">硬件信息列表</a></li>
		<li class="active"><a href="${ctx}/hardware/deviceHardware/form?id=${deviceHardware.id}">硬件信息<shiro:hasPermission name="hardware:deviceHardware:edit">${not empty deviceHardware.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hardware:deviceHardware:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="deviceHardware" action="${ctx}/hardware/deviceHardware/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="creatDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${deviceHardware.creatDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">单位名称：</label>
			<div class="controls">
				<form:input path="unitName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门名称：</label>
			<div class="controls">
				<form:input path="deptName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">岗位名称：</label>
			<div class="controls">
				<form:input path="posName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机名：</label>
			<div class="controls">
				<form:input path="computerName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作组：</label>
			<div class="controls">
				<form:input path="workTeam" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任人：</label>
			<div class="controls">
				<form:input path="responsPeople" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备用途：</label>
			<div class="controls">
				<form:input path="isServer" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机型号：</label>
			<div class="controls">
				<form:input path="computerModel" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IP地址：</label>
			<div class="controls">
				<form:input path="addrIp" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MAC地址：</label>
			<div class="controls">
				<form:input path="addrMac" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机序列号：</label>
			<div class="controls">
				<form:input path="computerSerialno" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机来源：</label>
			<div class="controls">
				<form:select path="incomeSource" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('income_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机生产日期：</label>
			<div class="controls">
				<input name="productDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${deviceHardware.productDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机用途：</label>
			<div class="controls">
				<form:input path="userPurpose" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算机入网地址：</label>
			<div class="controls">
				<form:input path="addrPosition" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作系统：</label>
			<div class="controls">
				<form:input path="oprationSystem" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作系统是否正版：</label>
			<div class="controls">
				<form:select path="isOsGenuine" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库：</label>
			<div class="controls">
				<form:input path="dataBase" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库是否正版：</label>
			<div class="controls">
				<form:select path="isDbGenuine" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装的杀毒软件：</label>
			<div class="controls">
				<form:input path="avSoftware" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已购买的正版软件：</label>
			<div class="controls">
				<form:input path="paidGenuineSoftwares" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正版软件需求：</label>
			<div class="controls">
				<form:input path="needGenuineSoftwares" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
        <!--
		<div class="control-group">
			<label class="control-label">spare_param_1：</label>
			<div class="controls">
				<form:input path="spareParam1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_2：</label>
			<div class="controls">
				<form:input path="spareParam2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_3：</label>
			<div class="controls">
				<form:input path="spareParam3" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_4：</label>
			<div class="controls">
				<form:input path="spareParam4" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_5：</label>
			<div class="controls">
				<form:input path="spareParam5" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_6：</label>
			<div class="controls">
				<form:input path="spareParam6" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_7：</label>
			<div class="controls">
				<form:input path="spareParam7" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_8：</label>
			<div class="controls">
				<form:input path="spareParam8" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_9：</label>
			<div class="controls">
				<form:input path="spareParam9" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">spare_param_10：</label>
			<div class="controls">
				<form:input path="spareParam10" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
        -->
        <div class="control-group">
            <label class="control-label">备注：</label>
            <div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
            </div>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="hardware:deviceHardware:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>