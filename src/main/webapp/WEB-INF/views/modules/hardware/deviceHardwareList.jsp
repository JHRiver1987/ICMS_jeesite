<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>硬件信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出硬件信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/hardware/deviceHardware/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/hardware/deviceHardware/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/hardware/deviceHardware/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hardware/deviceHardware/">硬件信息列表</a></li>
		<shiro:hasPermission name="hardware:deviceHardware:edit"><li><a href="${ctx}/hardware/deviceHardware/form">硬件信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deviceHardware" action="${ctx}/hardware/deviceHardware/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位名称：</label>
				<form:input path="unitName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>部门名称：</label>
				<form:input path="deptName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>设备用途：</label>
				<form:input path="isServer" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>IP地址：</label>
				<form:input path="addrIp" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>计算机来源：</label>
				<form:select path="incomeSource" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('income_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计算机用途：</label>
				<form:input path="userPurpose" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>操作系统：</label>
				<form:input path="oprationSystem" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>操作系统是否正版：</label>
				<form:select path="isOsGenuine" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>数据库：</label>
				<form:input path="dataBase" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>数据库是否正版：</label>
				<form:select path="isDbGenuine" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>安装的杀毒软件：</label>
				<form:input path="avSoftware" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备管理编号</th>
				<th>修改时间</th>
				<th>单位名称</th>
				<th>部门名称</th>
				<th>计算机名</th>
				<th>设备用途</th>
				<th>IP地址</th>
				<shiro:hasPermission name="hardware:deviceHardware:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deviceHardware">
			<tr>
				<td><a href="${ctx}/hardware/deviceHardware/form?id=${deviceHardware.id}">
						${deviceHardware.id}
				</a></td>
				<td>
					<fmt:formatDate value="${deviceHardware.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${deviceHardware.unitName}
				</td>
				<td>
					${deviceHardware.deptName}
				</td>
				<td>
					${deviceHardware.computerName}
				</td>
				<td>
					${deviceHardware.isServer}
				</td>
				<td>
					${deviceHardware.addrIp}
				</td>
				<shiro:hasPermission name="hardware:deviceHardware:edit"><td>
    				<a href="${ctx}/hardware/deviceHardware/form?id=${deviceHardware.id}">修改</a>
					<a href="${ctx}/hardware/deviceHardware/delete?id=${deviceHardware.id}" onclick="return confirmx('确认要删除该硬件信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>