<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频监控系统管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出视频监控系统信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/monitor/deviceMonitor/export");
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
		<form id="importForm" action="${ctx}/monitor/deviceMonitor/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/monitor/deviceMonitor/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/monitor/deviceMonitor/">视频监控系统列表</a></li>
		<shiro:hasPermission name="monitor:deviceMonitor:edit"><li><a href="${ctx}/monitor/deviceMonitor/form">视频监控系统添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deviceMonitor" action="${ctx}/monitor/deviceMonitor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位名称：</label>
				<form:input path="unitName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>现况/需求：</label>
				<form:input path="status" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>主机平台品牌：</label>
				<form:input path="hostBrand" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>接入类型：</label>
				<form:input path="camSignal" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>监控摄像机品牌：</label>
				<form:input path="camBrand" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>安装年份：</label>
				<form:input path="year" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>编号</th>
				<th>修改时间</th>
				<th>单位名称</th>
				<th>作业区名称</th>
				<th>主机平台类型</th>
				<th>主机平台品牌</th>
				<th>监控摄像机类型</th>
				<th>接入类型</th>
				<th>安装年份</th>
				<shiro:hasPermission name="monitor:deviceMonitor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deviceMonitor">
			<tr>
				<td><a href="${ctx}/monitor/deviceMonitor/form?id=${deviceMonitor.id}">
						${deviceMonitor.id}
				</a></td>
				<td><a href="${ctx}/monitor/deviceMonitor/form?id=${deviceMonitor.id}">
					<fmt:formatDate value="${deviceMonitor.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${deviceMonitor.unitName}
				</td>
				<td>
						${deviceMonitor.deptName}
				</td>
				<td>
						${deviceMonitor.hostStyle}
				</td>
				<td>
						${deviceMonitor.hostBrand}
				</td>
				<td>
						${deviceMonitor.camStyle}
				</td>
				<td>
						${deviceMonitor.camSignal}
				</td>
				<td>
						${deviceMonitor.year}
				</td>
				<shiro:hasPermission name="monitor:deviceMonitor:edit"><td>
    				<a href="${ctx}/monitor/deviceMonitor/form?id=${deviceMonitor.id}">修改</a>
					<a href="${ctx}/monitor/deviceMonitor/delete?id=${deviceMonitor.id}" onclick="return confirmx('确认要删除该视频监控系统吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>